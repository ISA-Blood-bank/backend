package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.exceptions.OverlappingAppointmentException;
import com.bloodbank.BloodBank.model.*;
import com.bloodbank.BloodBank.model.dto.AppointmentDto;
import com.bloodbank.BloodBank.model.dto.RecommendDto;
import com.bloodbank.BloodBank.repository.*;
import com.bloodbank.BloodBank.security.auth.TokenBasedAuthentication;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) //??
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ScheduledAppointmentRepository scheduledAppointmentRepository;

    @Autowired
    private AppointmentReportRepository appointmentReportRepository;

    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    @Autowired
    private MedicalStaffRepository medicalStaffRepository;
    @Autowired
    private RegisteredUserRepository userRepository;

    @Autowired
    private RegisteredUserService registeredUserService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private BloodCenterRepository bloodCenterRepository;

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Appointment scheduleAppointment(Appointment appointment) throws MessagingException, ObjectOptimisticLockingFailureException {
            //Appointment appointment = appointmentRepository.findById(appointmentId).orElseGet(null);
            if(appointment == null){
                return appointment;
            }
            RegistredUser user;
            try{
                TokenBasedAuthentication authentication = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
                user = (RegistredUser) authentication.getPrincipal();
            }catch (final Exception e){
                user = registeredUserService.findOne(1);
            }



            boolean canScheduleReports = true;
            List<AppointmentReport> userReports = new ArrayList<>();
            for (AppointmentReport ar: appointmentReportRepository.findAll()) {
                if(ar.getAppointment().getUser().getId() == user.getId() && ar.isCanGiveBlood()){
                    userReports.add(ar);
                }
            }


            if(!userReports.isEmpty()){
                AppointmentReport lastReport = this.findLastReport(userReports);
                canScheduleReports = this.IsBefore6Months(lastReport.getAppointment().getAppointment().getStart());
            }

            boolean canScheduleQuestionaire = false;
            for(Questionnaire q : questionnaireRepository.findAll()){
                if(q.getRegistredUser().getId() == user.getId()){
                    canScheduleQuestionaire = true;
                }
            }

            if(canScheduleReports && canScheduleQuestionaire && user.getPenalties() < 3 && this.firstScheduling(user.getId(), appointment.getId())) {
                appointment.setAvailable(false);
                ScheduledAppointment newScheduledAppointment = new ScheduledAppointment(-1, appointment, user, false, false);
                scheduledAppointmentRepository.save(newScheduledAppointment);
                emailService.sendAppointmentScheduledMail("tasakrgovic@gmail.com", "Uspesno je zakazan termin!");

                    return save(appointment);

            }
        return null;
    }

    private boolean IsBefore6Months(LocalDateTime appointmentDate){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowMinus6Months = now.minusMonths(6);
        if(appointmentDate.isBefore(nowMinus6Months)){
            return true;
        }
        return false;
    }

    private AppointmentReport findLastReport(List<AppointmentReport> userReports){
        AppointmentReport lastReport = userReports.get(0);
        for(AppointmentReport ar: userReports){
            if(ar.getAppointment().getAppointment().getStart().isAfter(lastReport.getAppointment().getAppointment().getStart())){
                lastReport = ar;
            }
        }
        return lastReport;
    }

    public List<Appointment> findAllSortedAndAvailable(int page, int size, String sortList, String order){
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortList));
        if(order.equals("ASC")){
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortList));
        }else {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortList));
        }
        return appointmentRepository.findAllSortedAndAvailable(pageable);
    }

    @Transactional(readOnly = false)
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);

    }
    @Transactional(readOnly = false)
    public Appointment createNewAvailableAppointment(AppointmentDto appointment){
        MedicalStaff ms = medicalStaffRepository.findByRegUserId(appointment.getMedicalStaffId());
        BloodCenter bc = ms.getBloodCenter();
        RegistredUser ru = userRepository.getById(appointment.getMedicalStaffId());
        Appointment appointment1 = new Appointment(appointment.getId(), appointment.getStart(),appointment.getDuration(),true,bc,ru);
        if(isOverLaping(appointment1)){
            throw new OverlappingAppointmentException("Appointment is overlapping with another one, choose different time.");
        }
        return appointmentRepository.save(appointment1);
    }
    public List<Appointment> getAvailableAppointments(RecommendDto recommendDto){
        List<Appointment> available = new ArrayList<Appointment>();
        List<Appointment> appointments = appointmentRepository.findAll();
        for(Appointment appointment : appointments){
            if(appointment.isAvailable()){
                if(recommendDto.getStart().isEqual(appointment.getStart())){
                    available.add(appointment);
                }
            }
        }
        return available;
    }

    @Transactional(readOnly = false)
    //zakazi izabrani preporuceni termin(ili vec postojeci slobodan ili napravi novi pa zakazi)
    public Appointment scheduleRecommendedAppointment(RecommendDto dto, int bloodcenter_id) throws MessagingException {
        int id_appointment = checkIfFreeAppointmentExists(dto.getStart(), bloodcenter_id);
        if( id_appointment != -1){
            Appointment appointmen = getById(id_appointment);
            return scheduleAppointment(appointmen);
        }
        List<MedicalStaff> med_staff_from_bc = medicalStaffRepository.findByBloodCenterId(bloodcenter_id);
        RegistredUser medicalStaff = userRepository.getById(med_staff_from_bc.get(0).getId());
        Appointment newAppointment = new Appointment(-1, dto.getStart(), 1, true, bloodCenterRepository.getById(bloodcenter_id), medicalStaff);
        Appointment savedAppointment = appointmentRepository.save(newAppointment);
        emailService.sendAppointmentScheduledMail("tasakrgovic@gmail.com", "Uspesno je zakazan termin!");

        return scheduleAppointment(savedAppointment);
    }

    //provera da li izabrani bloodcenter vec ima slobodan termin u to vreme koji se moze zakazati
    public int checkIfFreeAppointmentExists(LocalDateTime date, int bloodcenter_id){
        List<Appointment> appointments = appointmentRepository.findByBloodCenterId(bloodcenter_id);
        for(Appointment appointment : appointments){
            if(appointment.getStart().isEqual(date)){
                return appointment.getId(); // postoji slobodan appointment u zadatom terminu
            }
        }
        return -1; //pravi se novi appointment objekat
    }

    private boolean firstScheduling(int userId, int appointmentId){
        for (ScheduledAppointment sa:scheduledAppointmentRepository.findAllByUserIdCanceled(userId)) {
            if(sa.getAppointment().getId() == appointmentId){
                return false;
            }
        }
        return true;
    }
    public Appointment getById(int id){
        Optional<Appointment> app = appointmentRepository.findById(id);
        if(app.isPresent()){
            return app.get();
        }
        return null;
    }

    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Appointment newPredefinedAppointment(Appointment appointment){
        if(isOverLaping(appointment)){
            throw new OverlappingAppointmentException("Appointment is overlapping with another one, choose different time.");
        }
        return save(appointment);
    }
    public boolean isOverLaping(Appointment appointment) {
        List<Appointment> appointments = appointmentRepository.findByBloodCenterId(appointment.getBloodCenter().getId());

        LocalDateTime newAppointmentStart = appointment.getStart();
        LocalDateTime newAppointmentEnd = newAppointmentStart.plusHours((long) appointment.getDuration());

        for (Appointment existingAppointment : appointments) {
            LocalDateTime existingAppointmentStart = existingAppointment.getStart();
            LocalDateTime existingAppointmentEnd = existingAppointmentStart.plusHours((long) existingAppointment.getDuration());

            if (newAppointmentStart.isEqual(existingAppointmentStart) || (newAppointmentStart.isAfter(existingAppointmentStart) && newAppointmentStart.isBefore(existingAppointmentEnd))
                    || (newAppointmentEnd.isAfter(existingAppointmentStart) && newAppointmentEnd.isBefore(existingAppointmentEnd))) {
                return true; // Overlapping appointment found
            }
        }

        return false; // No overlapping appointment found
    }
}

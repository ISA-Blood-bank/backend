package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.exceptions.SameMedicalStaffException;
import com.bloodbank.BloodBank.model.*;
import com.bloodbank.BloodBank.model.dto.AppointmentDto;
import com.bloodbank.BloodBank.model.dto.RecommendDto;
import com.bloodbank.BloodBank.repository.*;
import com.bloodbank.BloodBank.security.auth.TokenBasedAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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


    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    @Transactional(readOnly = false)
    public Appointment scheduleAppointment(Integer appointmentId){
            Appointment appointment = appointmentRepository.findById(appointmentId).orElseGet(null);
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

            if(canScheduleReports && canScheduleQuestionaire && user.getPenalties() < 3 && this.firstScheduling(user.getId(), appointmentId)) {
                appointment.setAvailable(false);
                ScheduledAppointment newScheduledAppointment = new ScheduledAppointment(-1, appointment, user, false, false);
                scheduledAppointmentRepository.save(newScheduledAppointment);
                return appointmentRepository.save(appointment);
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

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Appointment save(Appointment appointment) {
        if(isOverlapingAnMedicalStaff(appointment)){
            throw new SameMedicalStaffException("Same medical staff cannot be in two or more overlapping appointments at the same time!");
        }

        return appointmentRepository.save(appointment);

    }

    @Transactional(readOnly = false)
    public Appointment createNewAppointment(AppointmentDto appointment){
        MedicalStaff ms = medicalStaffRepository.findById(appointment.getMedicalStaffId()).orElseGet(null);
        BloodCenter bc = ms.getBloodCenter();
        //RegistredUser ru = userRepository.getById(appointment.getMedicalStaffId());
        Appointment appointment1 = new Appointment(appointment.getId(), appointment.getStart(),appointment.getDuration(),true,bc,ms);
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

    private boolean firstScheduling(int userId, int appointmentId){
        for (ScheduledAppointment sa:scheduledAppointmentRepository.findAllByUserIdCanceled(userId)) {
            if(sa.getAppointment().getId() == appointmentId){
                return false;
            }
        }
        return true;
    }

    public boolean isOverLaping(Appointment appointment) {
        List<Appointment> appointments = appointmentRepository.findByBloodCenter_Id(appointment.getBloodCenter().getId());

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

    public List<Appointment> getOverlappingAppointments(Appointment appointment){
        List<Appointment> appointments = appointmentRepository.findByBloodCenter_Id(appointment.getBloodCenter().getId());
        List<Appointment> overlaping = new ArrayList<>();

        LocalDateTime newAppointmentStart = appointment.getStart();
        LocalDateTime newAppointmentEnd = newAppointmentStart.plusHours((long) appointment.getDuration());

        for (Appointment existingAppointment : appointments) {
            LocalDateTime existingAppointmentStart = existingAppointment.getStart();
            LocalDateTime existingAppointmentEnd = existingAppointmentStart.plusHours((long) existingAppointment.getDuration());

            if (newAppointmentStart.isEqual(existingAppointmentStart) || (newAppointmentStart.isAfter(existingAppointmentStart) && newAppointmentStart.isBefore(existingAppointmentEnd))
                    || (newAppointmentEnd.isAfter(existingAppointmentStart) && newAppointmentEnd.isBefore(existingAppointmentEnd))) {
                overlaping.add(existingAppointment);
            }
        }

        return overlaping;
    }

    public boolean isOverlapingAnMedicalStaff(Appointment appointment){
        List<Appointment> overlapping = getOverlappingAppointments(appointment);
        MedicalStaff msToAdd = appointment.getMedicalStaff();

        if(overlapping.isEmpty()){
            return false;
        }

        for(Appointment existing: overlapping){
            MedicalStaff m = existing.getMedicalStaff();
            if (m.equals(msToAdd)){
                return true;
            }
        }

        return false;
    }

}

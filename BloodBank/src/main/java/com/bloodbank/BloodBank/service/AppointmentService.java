package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.*;
import com.bloodbank.BloodBank.model.dto.AppointmentDto;
import com.bloodbank.BloodBank.model.dto.CalendarAppointmentDto;
import com.bloodbank.BloodBank.model.dto.CalendarFreeAppointmentDto;
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

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private EmailService emailService;

    @Autowired
    private BloodCenterRepository bloodCenterRepository;

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
        //appointment1.setId(9);
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

            return scheduleAppointment(id_appointment);
        }
        List<MedicalStaff> med_staff_from_bc = medicalStaffRepository.findByBloodCenterId(bloodcenter_id);
        RegistredUser medicalStaff = userRepository.getById(med_staff_from_bc.get(0).getId());
        Appointment newAppointment = new Appointment(-1, dto.getStart(), 1, true, bloodCenterRepository.getById(bloodcenter_id), medicalStaff);
        Appointment savedAppointment = appointmentRepository.save(newAppointment);
        emailService.sendAppointmentScheduledMail("tasakrgovic@gmail.com", "Uspesno je zakazan termin!");

        return scheduleAppointment(savedAppointment.getId());
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

    private LocalDateTime makeEndDate(float duration, LocalDateTime start){
        LocalDateTime endDate = start.plusMinutes( Math.round(duration));
        return endDate;
    }
    public List<CalendarFreeAppointmentDto> findByBloodCenterId (int idBloodCentra ){
        List<Appointment> appointments = appointmentRepository.findByBloodCenterId(idBloodCentra);
        List<CalendarFreeAppointmentDto> calendarFreeAppointmentDtos =  new ArrayList<>();
        ScheduledAppointment scheduledAppointment =  new ScheduledAppointment();
        CalendarFreeAppointmentDto finalDto = new CalendarFreeAppointmentDto();
        for (Appointment i : appointments){
            LocalDateTime endDate = makeEndDate(i.getDuration(),i.getStart());
            if (i.isAvailable()){
                CalendarFreeAppointmentDto calendarAppointmentDto= new CalendarFreeAppointmentDto(i.getId(),i.getStart(),endDate,"","",i.getMedicalStaff().getName(),i.getMedicalStaff().getSurname(),i.getDuration());
                finalDto =calendarAppointmentDto;
            }
            else{
                scheduledAppointment = this.scheduledAppointmentRepository.findByAppointmentId(i.getId());
                CalendarFreeAppointmentDto calendarAppointmentDto= new CalendarFreeAppointmentDto(i.getId(),i.getStart(),endDate,scheduledAppointment.getUser().getName(),scheduledAppointment.getUser().getSurname(),i.getMedicalStaff().getName(),i.getMedicalStaff().getSurname(),i.getDuration());
                finalDto =calendarAppointmentDto;
            }
            calendarFreeAppointmentDtos.add(finalDto);
        }
        return calendarFreeAppointmentDtos;
    }
}

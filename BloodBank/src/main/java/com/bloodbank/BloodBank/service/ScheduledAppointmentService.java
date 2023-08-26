package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.*;
import com.bloodbank.BloodBank.model.dto.AdditionalInfoDto;
import com.bloodbank.BloodBank.model.dto.RejectionReasonDto;
import com.bloodbank.BloodBank.model.dto.ScheduledAppointmentDto;
import com.bloodbank.BloodBank.model.dto.ScheduledDisplayDto;
import com.bloodbank.BloodBank.repository.*;
import com.bloodbank.BloodBank.security.auth.TokenBasedAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduledAppointmentService {
    @Autowired
    private ScheduledAppointmentRepository scheduledAppointmentRepository;

    @Autowired
    private RegisteredUserService registeredUserService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private BloodCenterSevice bloodCenterSevice;

    @Autowired
    private VisitsRepository visitsRepository;

    @Autowired
    private BloodRepository bloodRepository;

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private MedicalStaffService medicalStaffService;

    @Autowired
    private AdditionalInfoRepository additionalInfoRepository;

    @Autowired
    private  AppointmentService appointmentService;


    public List<ScheduledAppointment> findAllByUserId(){
        TokenBasedAuthentication authentication = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
        RegistredUser user = (RegistredUser) authentication.getPrincipal();
        int userId = user.getId();
        List<ScheduledAppointment> all = scheduledAppointmentRepository.findAllByUserId(userId);
        List<ScheduledAppointment> future = new ArrayList<>();
        for(ScheduledAppointment sa : all){
            if(sa.getAppointment().getStart().isAfter(LocalDateTime.now())){
                future.add(sa);
            }
        }
        return future;
    }

    public ScheduledAppointment findById(Integer id){
        return  scheduledAppointmentRepository.findById(id).orElseGet(null);
    }

    public ScheduledAppointment cancelAppointment(int id){
        ScheduledAppointment canceled = scheduledAppointmentRepository.findById(id).orElseGet(null);
        if(canceled == null){
            return canceled;
        }

        if(IsBefore24Hours(canceled.getAppointment().getStart())){
            canceled.setCanceled(true);
            Appointment availableAgain = appointmentRepository.findById(canceled.getAppointment().getId()).orElseGet(null);
            availableAgain.setAvailable(true);
            appointmentRepository.save(availableAgain);
            return scheduledAppointmentRepository.save(canceled);
        }
        return null;
    }

    private boolean IsBefore24Hours(LocalDateTime appointmentStart){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plus24Hours = now.plusHours(24);
        if(plus24Hours.isBefore(appointmentStart)){
            return true;
        }
        return false;
    }

    public ScheduledAppointment patientNotCome(ScheduledAppointmentDto scheduledAppointmentDto){
        registeredUserService.givePenalty(scheduledAppointmentDto.getRegistredUserId());
        Appointment appointment = appointmentRepository.findById(scheduledAppointmentDto.getAppointmentId()).orElseGet(null);
        ScheduledAppointment sch = new ScheduledAppointment(
                scheduledAppointmentDto.getId(),
                appointment,
                registeredUserService.findOne(scheduledAppointmentDto.getRegistredUserId()),
                scheduledAppointmentDto.isPassed(),
                scheduledAppointmentDto.isCanceled()
        );

        scheduledAppointmentRepository.save(sch);
        return sch;
    }

    public Visits addVisitsForUser(ScheduledAppointment dto){
        Visits visit = new Visits(
                dto.getUser(),
                dto.getAppointment().getBloodCenter()
        );

        visitsRepository.save(visit);
        return visit;
    }

    public AdditionalInformation saveAdditionalInfo(AdditionalInfoDto additionalInfoDto){
        AdditionalInformation info =  new AdditionalInformation(
                additionalInfoDto.getId(),
                questionnaireRepository.findById(additionalInfoDto.getQuestionaireId()).orElseGet(null),
                additionalInfoDto.getBloodType(),
                medicalStaffService.findById(additionalInfoDto.getMedicalStaffId()),
                additionalInfoDto.isBakarSulfat(),
                additionalInfoDto.isNormalLevel(),
                additionalInfoDto.isHighLevel(),
                additionalInfoDto.getHemoglobinometar(),
                additionalInfoDto.getValue(),
                additionalInfoDto.isLungs(),
                additionalInfoDto.isHeart(),
                additionalInfoDto.getTA(),
                additionalInfoDto.getTT(),
                additionalInfoDto.getTT(),
                additionalInfoDto.getBagType(),
                additionalInfoDto.getReasonForRejection(),
                additionalInfoDto.getReasonForAbort(),
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                additionalInfoDto.isAccepted()
        );

        additionalInfoRepository.save(info);

        return info;
    }
    public List<ScheduledDisplayDto> getByBloodCenterId(Integer bloodCenterId){
        List<ScheduledAppointment> scheduledAppointments =
                scheduledAppointmentRepository.findAllNonCanceledAndBloodCenterId(bloodCenterId);

        List<ScheduledDisplayDto> displayDtos = new ArrayList<ScheduledDisplayDto>();

        for(ScheduledAppointment s: scheduledAppointments){
            displayDtos.add(new ScheduledDisplayDto(
                    s.getId(),
                    s.getUser().getName(),
                    s.getUser().getSurname(),
                    s.getUser().getEmail(),
                    s.getAppointment().getStart(),
                    s.getAppointment().getDuration(),
                    s.isCanceled(),
                    s.isPassed(),
                    s.getUser().getId(),
                    s.getAppointment().getId()
            ));
        }

        return displayDtos;
    }

    public ScheduledAppointment setPassed(ScheduledAppointment scheduledAppointment){
        scheduledAppointment.setPassed(true);
        scheduledAppointment = scheduledAppointmentRepository.save(scheduledAppointment);

        return scheduledAppointment;
    }



}
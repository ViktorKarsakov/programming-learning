package kkkvd.operator.operatorkvd.service;

import kkkvd.operator.operatorkvd.dto.CaseSummaryDto;
import kkkvd.operator.operatorkvd.dto.PatientDetailResponse;
import kkkvd.operator.operatorkvd.dto.RefDto;
import kkkvd.operator.operatorkvd.entities.DetectionCase;
import kkkvd.operator.operatorkvd.entities.Doctor;
import kkkvd.operator.operatorkvd.entities.NamedDictionary;
import kkkvd.operator.operatorkvd.entities.Patient;
import kkkvd.operator.operatorkvd.repositories.DetectionCaseRepository;
import kkkvd.operator.operatorkvd.repositories.PatientRepository;
import kkkvd.operator.operatorkvd.util.DoctorNameFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final DetectionCaseRepository detectionCaseRepository;

    @Transactional(readOnly = true)
    public PatientDetailResponse getPatientWithCases(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пациент не найден"));
        List<DetectionCase> cases = detectionCaseRepository.findByPatientIdOrderByDiagnosisDateDesc(patientId);

        return PatientDetailResponse.builder()
                .id(patient.getId())
                .lastName(patient.getLastName())
                .firstName(patient.getFirstName())
                .middleName(patient.getMiddleName())
                .birthDate(patient.getBirthDate())
                .address(patient.getAddress())
                .gender(toRef(patient.getGender()))
                .cases(cases.stream().map(this::toCaseSummaryDto).toList())
                .build();
    }

    private CaseSummaryDto toCaseSummaryDto(DetectionCase detectionCase) {
        return CaseSummaryDto.builder()
                .id(detectionCase.getId())
                .diagnosisDate(detectionCase.getDiagnosisDate())
                .createdAt(detectionCase.getCreatedAt())
                .diagnosis(toRef(detectionCase.getDiagnosis()))
                .doctor(toDoctorRef(detectionCase.getDoctor()))
                .place(toRef(detectionCase.getPlace()))
                .profile(toRef(detectionCase.getProfile()))
                .inspection(toRef(detectionCase.getInspection()))
                .transfer(toRef(detectionCase.getTransfer()))
                .state(toRef(detectionCase.getState()))
                .citizenCategory(toRef(detectionCase.getCitizenCategory()))
                .citizenType(toRef(detectionCase.getCitizenType()))
                .socialGroup(toRef(detectionCase.getSocialGroup()))
                .isContact(detectionCase.getIsContact())
                .build();
    }

    private RefDto toRef(NamedDictionary dictionary) {
        if (dictionary == null) {
            return null;
        }
        return RefDto.builder()
                .id(dictionary.getId())
                .name(dictionary.getName())
                .build();
    }

    private RefDto toDoctorRef(Doctor doctor) {
        if (doctor == null) {
            return null;
        }

        return RefDto.builder()
                .id(doctor.getId())
                .name(DoctorNameFormatter.formatShort(doctor))
                .build();
    }
}

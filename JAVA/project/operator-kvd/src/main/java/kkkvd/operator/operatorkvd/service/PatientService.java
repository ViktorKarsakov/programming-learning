package kkkvd.operator.operatorkvd.service;

import kkkvd.operator.operatorkvd.dto.*;
import kkkvd.operator.operatorkvd.entities.*;
import kkkvd.operator.operatorkvd.mapper.RefDtoMapper;
import kkkvd.operator.operatorkvd.repositories.*;
import kkkvd.operator.operatorkvd.util.StringUtils;
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
    private final GenderRepository genderRepository;
    private final DetectionCaseService detectionCaseService;

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
                .gender(RefDtoMapper.toRef(patient.getGender()))
                .cases(cases.stream().map(this::toCaseSummaryDto).toList())
                .build();
    }

    @Transactional
    public PatientDetailResponse updatePatient(Long patientId, UpdatePatientRequest request) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пациент не найден"));

        patient.setLastName(request.getLastName());
        patient.setFirstName(request.getFirstName());
        patient.setMiddleName(StringUtils.normalizeBlankToNull(request.getMiddleName()));
        patient.setBirthDate(request.getBirthDate());
        patient.setAddress(request.getAddress());

        if (request.getGenderId() != null) {
            Gender gender = genderRepository.findById(request.getGenderId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пол не найден"));
            patient.setGender(gender);
        }

        patientRepository.save(patient);

        return getPatientWithCases(patientId);
    }

    @Transactional
    public void deletePatient(Long patientId) {
        long count = detectionCaseRepository.countByPatientId(patientId);
        if (count > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Нельзя удалить пациента: есть случаи заболевания");
        }

        if (!patientRepository.existsById(patientId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пациент не найден");
        }

        patientRepository.deleteById(patientId);
    }

    @Transactional
    public DetectionCaseResponse addCaseToPatient(Long patientId, CreateCaseForPatientRequest request) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пациент не найден"));

        return detectionCaseService.createForExistingPatient(patient, request);
    }


    private CaseSummaryDto toCaseSummaryDto(DetectionCase detectionCase) {
        return CaseSummaryDto.builder()
                .id(detectionCase.getId())
                .diagnosisDate(detectionCase.getDiagnosisDate())
                .createdAt(detectionCase.getCreatedAt())
                .diagnosis(RefDtoMapper.toRef(detectionCase.getDiagnosis()))
                .doctor(RefDtoMapper.toDoctorRef(detectionCase.getDoctor()))
                .place(RefDtoMapper.toRef(detectionCase.getPlace()))
                .profile(RefDtoMapper.toRef(detectionCase.getProfile()))
                .inspection(RefDtoMapper.toRef(detectionCase.getInspection()))
                .transfer(RefDtoMapper.toRef(detectionCase.getTransfer()))
                .state(RefDtoMapper.toRef(detectionCase.getState()))
                .citizenCategory(RefDtoMapper.toRef(detectionCase.getCitizenCategory()))
                .citizenType(RefDtoMapper.toRef(detectionCase.getCitizenType()))
                .socialGroup(RefDtoMapper.toRef(detectionCase.getSocialGroup()))
                .isContact(detectionCase.getIsContact())
                .build();
    }
}

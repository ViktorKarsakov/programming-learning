package kkkvd.operator.operatorkvd.service;

import kkkvd.operator.operatorkvd.dto.*;
import kkkvd.operator.operatorkvd.entities.*;
import kkkvd.operator.operatorkvd.repositories.*;
import kkkvd.operator.operatorkvd.specification.DetectionCaseSpecification;
import kkkvd.operator.operatorkvd.util.DoctorNameFormatter;
import kkkvd.operator.operatorkvd.util.NameUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DetectionCaseService {

    private final PatientRepository patientRepository;
    private final DetectionCaseRepository detectionCaseRepository;
    private final DetectionCaseLabTestRepository detectionCaseLabTestRepository;

    private final GenderRepository genderRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final DoctorRepository doctorRepository;
    private final PlaceRepository placeRepository;
    private final ProfileRepository profileRepository;
    private final InspectionRepository inspectionRepository;
    private final TransferRepository transferRepository;
    private final StateRepository stateRepository;
    private final CitizenCategoryRepository citizenCategoryRepository;
    private final CitizenTypeRepository citizenTypeRepository;
    private final SocialGroupRepository socialGroupRepository;
    private final LaboratoryTestTypeRepository laboratoryTestTypeRepository;

    @Transactional
    public DetectionCase create(CreateDetectionCaseRequest request) {
        Patient patient = findOrCreatePatient(request);
        CreateCaseForPatientRequest caseRequest = new CreateCaseForPatientRequest();
        caseRequest.setDiagnosisId(request.getDiagnosisId());
        caseRequest.setDiagnosisDate(request.getDiagnosisDate());
        caseRequest.setDoctorId(request.getDoctorId());
        caseRequest.setPlaceId(request.getPlaceId());
        caseRequest.setProfileId(request.getProfileId());
        caseRequest.setInspectionId(request.getInspectionId());
        caseRequest.setTransferId(request.getTransferId());
        caseRequest.setStateId(request.getStateId());
        caseRequest.setCitizenCategoryId(request.getCitizenCategoryId());
        caseRequest.setCitizenTypeId(request.getCitizenTypeId());
        caseRequest.setSocialGroupId(request.getSocialGroupId());
        caseRequest.setIsContact(request.getIsContact());
        caseRequest.setLabTestIds(request.getLabTestIds());

        return createCaseInternal(patient, caseRequest);
    }

    @Transactional
    public DetectionCaseResponse createForExistingPatient(Patient patient, CreateCaseForPatientRequest request) {
        DetectionCase savedCase = createCaseInternal(patient, request);
        return toResponse(savedCase);
    }

    private DetectionCase createCaseInternal(Patient patient, CreateCaseForPatientRequest request) {
        DetectionCase detectionCase = new DetectionCase();
        detectionCase.setPatient(patient);

        detectionCase.setDiagnosis(diagnosisRepository.findById(request.getDiagnosisId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Диагноз не найден")));

        detectionCase.setDiagnosisDate(request.getDiagnosisDate());

        detectionCase.setDoctor(doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Доктор не найден")));

        detectionCase.setPlace(placeRepository.findById(request.getPlaceId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Место не найдено")));

        detectionCase.setProfile(profileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Профиль не найден")));

        detectionCase.setInspection(inspectionRepository.findById(request.getInspectionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Осмотр не найден")));

        detectionCase.setTransfer(transferRepository.findById(request.getTransferId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пути передачи не найдены")));

        detectionCase.setState(stateRepository.findById(request.getStateId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Район не найден")));

        detectionCase.setCitizenCategory(citizenCategoryRepository.findById(request.getCitizenCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Категория гражданина не найдена")));

        detectionCase.setCitizenType(citizenTypeRepository.findById(request.getCitizenTypeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Тип гражданина не найден")));

        detectionCase.setSocialGroup(socialGroupRepository.findById(request.getSocialGroupId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Социальная группа не найдена")));

        detectionCase.setIsContact(request.getIsContact() != null ? request.getIsContact() : false);

        DetectionCase savedCase = detectionCaseRepository.save(detectionCase);

        rewriteLabTests(savedCase, request.getLabTestIds());

        return savedCase;
    }

    private void rewriteLabTests(DetectionCase savedCase, Set<Long> labTestIds) {
        detectionCaseLabTestRepository.deleteByDetectionCaseId(savedCase.getId());

        if (labTestIds == null || labTestIds.isEmpty()) {
            return;
        }
        Set<Long> uniqueTests = new HashSet<>(labTestIds);
        List<LaboratoryTestType> types = laboratoryTestTypeRepository.findAllById(uniqueTests);

        List<DetectionCaseLabTest> labTests = types.stream()
                .map(type -> {
                    DetectionCaseLabTest labTest = new DetectionCaseLabTest();
                    labTest.setDetectionCase(savedCase);
                    labTest.setLaboratoryTestType(type);
                    return labTest;
                })
                .toList();

        detectionCaseLabTestRepository.saveAll(labTests);
    }

    private Patient findOrCreatePatient(CreateDetectionCaseRequest request) {
        String middle = request.getMiddleName();
        if (middle != null && middle.isBlank()){
            middle = null;
        }
        String finalMiddleName = middle;

        return patientRepository
                .findByFullNameAndBirthDate(
                        request.getLastName(),
                        request.getFirstName(),
                        finalMiddleName,
                        request.getBirthDate()
                )
                .orElseGet(() -> {
                    Patient patient = new Patient();
                    patient.setLastName(request.getLastName());
                    patient.setFirstName(request.getFirstName());
                    patient.setMiddleName(finalMiddleName);
                    patient.setBirthDate(request.getBirthDate());
                    patient.setAddress(request.getAddress());
                    patient.setGender(genderRepository.findById(request.getGenderId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пол не найден")));
                    return patientRepository.save(patient);
                });
    }

    public List<DetectionCase> getByPatientId(Long patientId) {
        return detectionCaseRepository.findByPatientIdOrderByDiagnosisDateDesc(patientId);
    }

    public Page<PatientSearchResult> search(PatientSearchRequest request) {
        Pageable  pageable = PageRequest.of(
                request.getPage() != null ? request.getPage() : 0,
                request.getSize() != null ? request.getSize() : 20
        );

        Page<DetectionCase> cases = detectionCaseRepository.findAll(DetectionCaseSpecification.withFilters(request), pageable);

        return cases.map(this::toSearchResult);
    }

    @Transactional
    public void deleteCase(Long detectionCaseId) {
        DetectionCase dc = detectionCaseRepository.findById(detectionCaseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Случай не найден"));
        detectionCaseLabTestRepository.deleteByDetectionCaseId(detectionCaseId);
        detectionCaseRepository.delete(dc);
    }

    private PatientSearchResult toSearchResult(DetectionCase dc) {
        Patient patient = dc.getPatient();
        return PatientSearchResult.builder()
                .patientId(patient.getId())
                .detectionCaseId(dc.getId())
                .lastName(patient.getLastName())
                .firstName(patient.getFirstName())
                .middleName(patient.getMiddleName())
                .genderName(patient.getGender() != null ? patient.getGender().getName() : null)
                .birthDate(patient.getBirthDate())
                .stateName(dc.getState() != null ? dc.getState().getName() : null)
                .diagnosisName(dc.getDiagnosis() != null ? dc.getDiagnosis().getName() : null)
                .diagnosisDate(dc.getDiagnosisDate())
                .doctorName(dc.getDoctor() != null ? DoctorNameFormatter.formatShort(dc.getDoctor()) : null)
                .createdAt(dc.getCreatedAt())
                .build();
    }


    public DetectionCaseResponse toResponse(DetectionCase detectionCase) {
        Patient patient = detectionCase.getPatient();

        return DetectionCaseResponse.builder()
                .id(detectionCase.getId())
                .patientId(patient != null ? patient.getId() : null)
                .patientFullName(NameUtils.buildFullName(patient))
                .diagnosisName(detectionCase.getDiagnosis() != null ? detectionCase.getDiagnosis().getName() : null)
                .diagnosisDate(detectionCase.getDiagnosisDate())
                .createdAt(detectionCase.getCreatedAt())
                .build();
    }
}

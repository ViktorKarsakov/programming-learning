package kkkvd.operator.operatorkvd.service;

import kkkvd.operator.operatorkvd.dto.CreateDetectionCaseRequest;
import kkkvd.operator.operatorkvd.entities.DetectionCase;
import kkkvd.operator.operatorkvd.entities.DetectionCaseLabTest;
import kkkvd.operator.operatorkvd.entities.LaboratoryTestType;
import kkkvd.operator.operatorkvd.entities.Patient;
import kkkvd.operator.operatorkvd.repositories.*;
import lombok.RequiredArgsConstructor;
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

        if (request.getLabTestIds() != null && !request.getLabTestIds().isEmpty()) {
            Set<Long> uniqueTests = new HashSet<>(request.getLabTestIds());
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

        return savedCase;
    }

    private Patient findOrCreatePatient(CreateDetectionCaseRequest request) {
        return patientRepository
                .findByFullNameAndBirthDate(
                        request.getLastName(),
                        request.getFirstName(),
                        request.getMiddleName(),
                        request.getBirthDate()
                )
                .orElseGet(() -> {
                    Patient patient = new Patient();
                    patient.setLastName(request.getLastName());
                    patient.setFirstName(request.getFirstName());
                    patient.setMiddleName(request.getMiddleName());
                    patient.setBirthDate(request.getBirthDate());
                    patient.setAddress(request.getAddress());
                    patient.setGender(genderRepository.findById(request.getGenderId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пол не найден")));
                    return patientRepository.save(patient);
                });
    }

    public List<DetectionCase> getByPatientId(Long patientId) {
        return detectionCaseRepository.findByPatientId(patientId);
    }
}

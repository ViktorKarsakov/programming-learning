package kkkvd.operator.operatorkvd.service;

import jakarta.transaction.Transactional;
import kkkvd.operator.operatorkvd.dto.CreateDetectionCaseRequest;
import kkkvd.operator.operatorkvd.entities.DetectionCase;
import kkkvd.operator.operatorkvd.entities.Patient;
import kkkvd.operator.operatorkvd.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null; //TODO: дописать
    }

    private Patient findOrCreatePatient(CreateDetectionCaseRequest request) {
        return patientRepository
                .findByLastNameAndFirstNameAndMiddleNameAndBirthDate(
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
                    .orElseThrow(() -> new RuntimeException("Пол не найден")));
                    return patientRepository.save(patient);
                });
    }

    public List<DetectionCase> getByPatientId(Long patientId) {
        return detectionCaseRepository.findByPatientId(patientId);
    }
}

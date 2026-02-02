package kkkvd.operator.operatorkvd.service;

import kkkvd.operator.operatorkvd.entities.*;
import kkkvd.operator.operatorkvd.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final BranchRepository branchRepository;
    private final CitizenCategoryRepository citizenCategoryRepository;
    private final CitizenTypeRepository citizenTypeRepository;
    private final DepartmentRepository departmentRepository;
    private final GenderRepository genderRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisGroupRepository diagnosisGroupRepository;
    private final DoctorRepository doctorRepository;
    private final StateRepository stateRepository;
    private final StateGroupRepository stateGroupRepository;
    private final PlaceRepository placeRepository;
    private final ProfileRepository profileRepository;
    private final InspectionRepository inspectionRepository;
    private final TransferRepository transferRepository;
    private final SocialGroupRepository socialGroupRepository;
    private final LaboratoryTestTypeRepository laboratoryTestTypeRepository;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public List<CitizenCategory> getAllCitizenCategories() {
        return citizenCategoryRepository.findAll();
    }

    public List<CitizenType> getAllCitizenTypes() {
        return citizenTypeRepository.findAll();
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<Gender> getAllGenders() {
        return genderRepository.findAll();
    }

    public List<Diagnosis> getAllDiagnoses() {
        return diagnosisRepository.findAllByOrderByNameAsc();
    }

    public List<DiagnosisGroup> getAllDiagnosisGroups() {
        return diagnosisGroupRepository.findAll();
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAllByOrderByLastNameAscFirstNameAsc();
    }

    public List<State> getAllStates() {
        return stateRepository.findAllByOrderByStateGroupIdAscNameAsc();
    }

    public List<StateGroup> getAllStateGroups() {
        return stateGroupRepository.findAll();
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public List<Inspection> getAllInspections() {
        return inspectionRepository.findAll();
    }

    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    public List<SocialGroup> getAllSocialGroups() {
        return socialGroupRepository.findAll();
    }

    public List<LaboratoryTestType> getAllLaboratoryTestTypes() {
        return laboratoryTestTypeRepository.findAll();
    }

    //created

    public Gender createGender(Gender gender) {
        return genderRepository.save(gender);
    }

    public Diagnosis createDiagnosis(Diagnosis diagnosis) {
        return diagnosisRepository.save(diagnosis);
    }

    public DiagnosisGroup createDiagnosisGroup(DiagnosisGroup diagnosisGroup) {
        return diagnosisGroupRepository.save(diagnosisGroup);
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public State createState(State state) {
        return stateRepository.save(state);
    }

    public StateGroup createStateGroup(StateGroup stateGroup) {
        return stateGroupRepository.save(stateGroup);
    }

    public Place createPlace(Place place) {
        return placeRepository.save(place);
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Inspection createInspection(Inspection inspection) {
        return inspectionRepository.save(inspection);
    }

    public Transfer createTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    public CitizenCategory createCitizenCategory(CitizenCategory citizenCategory) {
        return citizenCategoryRepository.save(citizenCategory);
    }

    public CitizenType createCitizenType(CitizenType citizenType) {
        return citizenTypeRepository.save(citizenType);
    }

    public SocialGroup createSocialGroup(SocialGroup socialGroup) {
        return socialGroupRepository.save(socialGroup);
    }

    public LaboratoryTestType createLaboratoryTestType(LaboratoryTestType labTestType) {
        return laboratoryTestTypeRepository.save(labTestType);
    }

    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }
}

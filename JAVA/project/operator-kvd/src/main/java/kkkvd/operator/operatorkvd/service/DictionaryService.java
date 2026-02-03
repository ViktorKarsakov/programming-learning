package kkkvd.operator.operatorkvd.service;

import kkkvd.operator.operatorkvd.entities.*;
import kkkvd.operator.operatorkvd.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    @Transactional
    public Gender updateGender(Long id, Gender gender) {
        Gender existing = genderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пол не найден"));
        existing.setName(gender.getName());
        return genderRepository.save(existing);
    }

    @Transactional
    public DiagnosisGroup updateDiagnosisGroup(Long id, DiagnosisGroup diagnosisGroup) {
        DiagnosisGroup existing = diagnosisGroupRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Группа диагнозов не найдена"));
        existing.setName(diagnosisGroup.getName());
        return diagnosisGroupRepository.save(existing);
    }

    @Transactional
    public Diagnosis updateDiagnosis(Long id, Diagnosis diagnosis) {
        Diagnosis existing = diagnosisRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Диагноз не найден"));
        existing.setName(diagnosis.getName());
        existing.setDiagnosisGroup(diagnosis.getDiagnosisGroup());
        return diagnosisRepository.save(existing);
    }

    @Transactional
    public Branch updateBranch(Long id, Branch branch) {
        Branch existing = branchRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Филиал не найден"));
        existing.setName(branch.getName());
        return branchRepository.save(existing);
    }

    @Transactional
    public Department updateDepartment(Long id, Department department) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Отделение не найдено"));
        existing.setName(department.getName());
        existing.setBranch(department.getBranch());
        return departmentRepository.save(existing);
    }

    @Transactional
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor existing = doctorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Врач не найден"));
        existing.setLastName(doctor.getLastName());
        existing.setFirstName(doctor.getFirstName());
        existing.setMiddleName(doctor.getMiddleName());
        existing.setDepartment(doctor.getDepartment());
        return doctorRepository.save(existing);
    }

    @Transactional
    public StateGroup updateStateGroup(Long id, StateGroup stateGroup) {
        StateGroup existing = stateGroupRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Группа районов не найдена"));
        existing.setName(stateGroup.getName());
        return stateGroupRepository.save(existing);
    }

    @Transactional
    public State updateState(Long id, State state) {
        State existing = stateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Район не найден"));
        existing.setName(state.getName());
        existing.setStateGroup(state.getStateGroup());
        return stateRepository.save(existing);
    }

    @Transactional
    public Place updatePlace(Long id, Place place) {
        Place existing = placeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Место выявления не найдено"));
        existing.setName(place.getName());
        return placeRepository.save(existing);
    }

    @Transactional
    public Profile updateProfile(Long id, Profile profile) {
        Profile existing = profileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Профиль не найден"));
        existing.setName(profile.getName());
        return profileRepository.save(existing);
    }

    //TODO: Доделать остальные update'ы и сделать delete
}

package kkkvd.operator.operatorkvd.controller;

import kkkvd.operator.operatorkvd.entities.*;
import kkkvd.operator.operatorkvd.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dictionaries")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DictionaryController {
    private final DictionaryService dictionaryService;

    //gender

    @GetMapping("/genders")
    public ResponseEntity<List<Gender>> getGenders() {
        return ResponseEntity.ok(dictionaryService.getAllGenders());
    }

    @PostMapping("/genders")
    public ResponseEntity<Gender> createGender(@RequestBody Gender gender) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createGender(gender));
    }

    @PutMapping("/genders/{id}")
    public ResponseEntity<Gender> updateGender(@PathVariable Long id, @RequestBody Gender gender) {
        return ResponseEntity.ok(dictionaryService.updateGender(id, gender));
    }

    @DeleteMapping("/genders/{id}")
    public ResponseEntity<Void> deleteGender(@PathVariable Long id) {
        dictionaryService.deleteGender(id);
        return ResponseEntity.noContent().build();
    }

    //Diagnosis

    @GetMapping("/diagnoses")
    public ResponseEntity<List<Diagnosis>> getDiagnosis() {
        return ResponseEntity.ok(dictionaryService.getAllDiagnoses());
    }

    @PostMapping("/diagnoses")
    public ResponseEntity<Diagnosis> createDiagnosis(@RequestBody Diagnosis diagnosis) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createDiagnosis(diagnosis));
    }

    @PutMapping("/diagnoses/{id}")
    public ResponseEntity<Diagnosis> updateDiagnosis(@PathVariable Long id, @RequestBody Diagnosis diagnosis) {
        return ResponseEntity.ok(dictionaryService.updateDiagnosis(id, diagnosis));
    }

    @DeleteMapping("/diagnoses/{id}")
    public ResponseEntity<Void> deleteDiagnosis(@PathVariable Long id) {
        dictionaryService.deleteDiagnosis(id);
        return ResponseEntity.noContent().build();
    }

    //DiagnosisGroups

    @GetMapping("/diagnosis-groups")
    public ResponseEntity<List<DiagnosisGroup>> getDiagnosisGroups() {
        return ResponseEntity.ok(dictionaryService.getAllDiagnosisGroups());
    }

    @PostMapping("/diagnosis-groups")
    public ResponseEntity<DiagnosisGroup> createDiagnosisGroup(@RequestBody DiagnosisGroup diagnosisGroup) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createDiagnosisGroup(diagnosisGroup));
    }

    @PutMapping("/diagnosis-groups/{id}")
    public ResponseEntity<DiagnosisGroup> updateDiagnosisGroup(@PathVariable Long id, @RequestBody DiagnosisGroup diagnosisGroup) {
        return ResponseEntity.ok(dictionaryService.updateDiagnosisGroup(id, diagnosisGroup));
    }

    @DeleteMapping("/diagnosis-groups/{id}")
    public ResponseEntity<Void> deleteDiagnosisGroup(@PathVariable Long id) {
        dictionaryService.deleteDiagnosisGroup(id);
        return ResponseEntity.noContent().build();
    }

    //Doctor

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getDoctors() {
        return  ResponseEntity.ok(dictionaryService.getAllDoctors());
    }

    @PostMapping("/doctors")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createDoctor(doctor));
    }

    @PutMapping("/doctors/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        return ResponseEntity.ok(dictionaryService.updateDoctor(id, doctor));
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        dictionaryService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    //State

    @GetMapping("/states")
    public ResponseEntity<List<State>> getStates() {
        return ResponseEntity.ok(dictionaryService.getAllStates());
    }

    @PostMapping("/states")
    public ResponseEntity<State> createState(@RequestBody State state) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createState(state));
    }

    @PutMapping("/states/{id}")
    public ResponseEntity<State> updateState(@PathVariable Long id, @RequestBody State state) {
        return ResponseEntity.ok(dictionaryService.updateState(id, state));
    }

    @DeleteMapping("/states/{id}")
    public ResponseEntity<Void> deleteState(@PathVariable Long id) {
        dictionaryService.deleteState(id);
        return ResponseEntity.noContent().build();
    }

    //StateGroup

    @GetMapping("/state-groups")
    public ResponseEntity<List<StateGroup>> getStateGroups() {
        return ResponseEntity.ok(dictionaryService.getAllStateGroups());
    }

    @PostMapping("/state-groups")
    public ResponseEntity<StateGroup> createStateGroup(@RequestBody StateGroup stateGroup) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createStateGroup(stateGroup));
    }

    @PutMapping("/state-groups/{id}")
    public ResponseEntity<StateGroup> updateStateGroup(@PathVariable Long id, @RequestBody StateGroup stateGroup) {
        return ResponseEntity.ok(dictionaryService.updateStateGroup(id, stateGroup));
    }

    @DeleteMapping("/state-groups/{id}")
    public ResponseEntity<Void> deleteStateGroup(@PathVariable Long id) {
        dictionaryService.deleteStateGroup(id);
        return ResponseEntity.noContent().build();
    }

    // Place

    @GetMapping("/places")
    public ResponseEntity<List<Place>> getPlaces() {
        return ResponseEntity.ok(dictionaryService.getAllPlaces());
    }

    @PostMapping("/places")
    public ResponseEntity<Place> createPlace(@RequestBody Place place) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createPlace(place));
    }

    @PutMapping("/places/{id}")
    public ResponseEntity<Place> updatePlace(@PathVariable Long id, @RequestBody Place place) {
        return ResponseEntity.ok(dictionaryService.updatePlace(id, place));
    }

    @DeleteMapping("/places/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        dictionaryService.deletePlace(id);
        return ResponseEntity.noContent().build();
    }

    //Profile

    @GetMapping("/profiles")
    public ResponseEntity<List<Profile>> getProfiles() {
        return ResponseEntity.ok(dictionaryService.getAllProfiles());
    }

    @PostMapping("/profiles")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createProfile(profile));
    }

    @PutMapping("/profiles/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
        return ResponseEntity.ok(dictionaryService.updateProfile(id, profile));
    }

    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        dictionaryService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }

    //Inspection

    @GetMapping("/inspections")
    public ResponseEntity<List<Inspection>> getInspections() {
        return ResponseEntity.ok(dictionaryService.getAllInspections());
    }

    @PostMapping("/inspections")
    public ResponseEntity<Inspection> createInspection(@RequestBody Inspection inspection) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createInspection(inspection));
    }

    @PutMapping("/inspections/{id}")
    public ResponseEntity<Inspection> updateInspection(@PathVariable Long id, @RequestBody Inspection inspection) {
        return ResponseEntity.ok(dictionaryService.updateInspection(id, inspection));
    }

    @DeleteMapping("/inspections/{id}")
    public ResponseEntity<Void> deleteInspection(@PathVariable Long id) {
        dictionaryService.deleteInspection(id);
        return ResponseEntity.noContent().build();
    }

    //Transfer

    @GetMapping("/transfers")
    public ResponseEntity<List<Transfer>> getTransfers() {
        return ResponseEntity.ok(dictionaryService.getAllTransfers());
    }

    @PostMapping("/transfers")
    public ResponseEntity<Transfer> createTransfer(@RequestBody Transfer transfer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createTransfer(transfer));
    }

    @PutMapping("/transfers/{id}")
    public ResponseEntity<Transfer> updateTransfer(@PathVariable Long id, @RequestBody Transfer transfer) {
        return ResponseEntity.ok(dictionaryService.updateTransfer(id, transfer));
    }

    @DeleteMapping("/transfers/{id}")
    public ResponseEntity<Void> deleteTransfer(@PathVariable Long id) {
        dictionaryService.deleteTransfer(id);
        return ResponseEntity.noContent().build();
    }

    //CitizenCategory

    @GetMapping("/citizen-categories")
    public ResponseEntity<List<CitizenCategory>> getCitizenCategories() {
        return ResponseEntity.ok(dictionaryService.getAllCitizenCategories());
    }

    @PostMapping("/citizen-categories")
    public ResponseEntity<CitizenCategory> createCitizenCategory(@RequestBody CitizenCategory citizenCategory) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createCitizenCategory(citizenCategory));
    }

    @PutMapping("/citizen-categories/{id}")
    public ResponseEntity<CitizenCategory> updateCitizenCategory(@PathVariable Long id, @RequestBody CitizenCategory citizenCategory) {
        return ResponseEntity.ok(dictionaryService.updateCitizenCategory(id, citizenCategory));
    }

    @DeleteMapping("/citizen-categories/{id}")
    public ResponseEntity<Void> deleteCitizenCategory(@PathVariable Long id) {
        dictionaryService.deleteCitizenCategory(id);
        return ResponseEntity.noContent().build();
    }

    //CitizenType

    @GetMapping("/citizen-types")
    public ResponseEntity<List<CitizenType>> getCitizenTypes() {
        return ResponseEntity.ok(dictionaryService.getAllCitizenTypes());
    }

    @PostMapping("/citizen-types")
    public ResponseEntity<CitizenType> createCitizenType(@RequestBody CitizenType citizenType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createCitizenType(citizenType));
    }

    @PutMapping("/citizen-types/{id}")
    public ResponseEntity<CitizenType> updateCitizenType(@PathVariable Long id, @RequestBody CitizenType citizenType) {
        return ResponseEntity.ok(dictionaryService.updateCitizenType(id, citizenType));
    }

    @DeleteMapping("/citizen-types/{id}")
    public ResponseEntity<Void> deleteCitizenType(@PathVariable Long id) {
        dictionaryService.deleteCitizenType(id);
        return ResponseEntity.noContent().build();
    }

    //SocialGroup

    @GetMapping("/social-groups")
    public ResponseEntity<List<SocialGroup>> getSocialGroups() {
        return ResponseEntity.ok(dictionaryService.getAllSocialGroups());
    }

    @PostMapping("/social-groups")
    public ResponseEntity<SocialGroup> createSocialGroup(@RequestBody SocialGroup socialGroup) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createSocialGroup(socialGroup));
    }

    @PutMapping("/social-groups/{id}")
    public ResponseEntity<SocialGroup> updateSocialGroup(@PathVariable Long id, @RequestBody SocialGroup socialGroup) {
        return ResponseEntity.ok(dictionaryService.updateSocialGroup(id, socialGroup));
    }

    @DeleteMapping("/social-groups/{id}")
    public ResponseEntity<Void> deleteSocialGroup(@PathVariable Long id) {
        dictionaryService.deleteSocialGroup(id);
        return ResponseEntity.noContent().build();
    }

    //LaboratoryTestType

    @GetMapping("/lab-test-types")
    public ResponseEntity<List<LaboratoryTestType>> getLaboratoryTestTypes() {
        return ResponseEntity.ok(dictionaryService.getAllLaboratoryTestTypes());
    }

    @PostMapping("/lab-test-types")
    public ResponseEntity<LaboratoryTestType> createLaboratoryTestType(@RequestBody LaboratoryTestType laboratoryTestType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createLaboratoryTestType(laboratoryTestType));
    }

    @PutMapping("/lab-test-types/{id}")
    public ResponseEntity<LaboratoryTestType> updateLaboratoryTestType(@PathVariable Long id, @RequestBody LaboratoryTestType laboratoryTestType) {
        return ResponseEntity.ok(dictionaryService.updateLaboratoryTestType(id, laboratoryTestType));
    }

    @DeleteMapping("/lab-test-types/{id}")
    public ResponseEntity<Void> deleteLaboratoryTestType(@PathVariable Long id) {
        dictionaryService.deleteLaboratoryTestType(id);
        return ResponseEntity.noContent().build();
    }

    // Branch

    @GetMapping("/branches")
    public ResponseEntity<List<Branch>> getBranches() {
        return ResponseEntity.ok(dictionaryService.getAllBranches());
    }

    @PostMapping("/branches")
    public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createBranch(branch));
    }

    @PutMapping("/branches/{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable Long id, @RequestBody Branch branch) {
        return ResponseEntity.ok(dictionaryService.updateBranch(id, branch));
    }

    @DeleteMapping("/branches/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        dictionaryService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }

    //Department

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getDepartments() {
        return ResponseEntity.ok(dictionaryService.getAllDepartments());
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createDepartment(department));
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        return ResponseEntity.ok(dictionaryService.updateDepartment(id, department));
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        dictionaryService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    //Population

    @GetMapping("/population")
    public ResponseEntity<List<Population>> getPopulations() {
        return ResponseEntity.ok(dictionaryService.getAllPopulations());
    }

    @PostMapping("/population")
    public ResponseEntity<Population> createPopulation(@RequestBody Population population) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createPopulation(population));
    }

    @PutMapping("/population/{id}")
    public ResponseEntity<Population> updatePopulation(@PathVariable Long id, @RequestBody Population population) {
        return ResponseEntity.ok(dictionaryService.updatePopulation(id, population));
    }

    @DeleteMapping("/population/{id}")
    public ResponseEntity<Void> deletePopulation(@PathVariable Long id) {
        dictionaryService.deletePopulation(id);
        return ResponseEntity.noContent().build();
    }
}

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

    //Diagnosis

    @GetMapping("/diagnoses")
    public ResponseEntity<List<Diagnosis>> getDiagnosis() {
        return ResponseEntity.ok(dictionaryService.getAllDiagnoses());
    }

    @PostMapping("/diagnoses")
    public ResponseEntity<Diagnosis> createDiagnosis(@RequestBody Diagnosis diagnosis) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createDiagnosis(diagnosis));
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

    //Doctor

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getDoctors() {
        return  ResponseEntity.ok(dictionaryService.getAllDoctors());
    }

    @PostMapping("/doctors")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createDoctor(doctor));
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

    //StateGroup

    @GetMapping("/state-groups")
    public ResponseEntity<List<StateGroup>> getStateGroups() {
        return ResponseEntity.ok(dictionaryService.getAllStateGroups());
    }

    @PostMapping("/state-groups")
    public ResponseEntity<StateGroup> createStateGroup(@RequestBody StateGroup stateGroup) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createStateGroup(stateGroup));
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

    //Profile

    @GetMapping("/profiles")
    public ResponseEntity<List<Profile>> getProfiles() {
        return ResponseEntity.ok(dictionaryService.getAllProfiles());
    }

    @PostMapping("/profiles")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createProfile(profile));
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

    //Transfer

    @GetMapping("/transfers")
    public ResponseEntity<List<Transfer>> getTransfers() {
        return ResponseEntity.ok(dictionaryService.getAllTransfers());
    }

    @PostMapping("/transfers")
    public ResponseEntity<Transfer> createTransfer(@RequestBody Transfer transfer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createTransfer(transfer));
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

    //CitizenType

    @GetMapping("/citizen-types")
    public ResponseEntity<List<CitizenType>> getCitizenTypes() {
        return ResponseEntity.ok(dictionaryService.getAllCitizenTypes());
    }

    @PostMapping("/citizen-types")
    public ResponseEntity<CitizenType> createCitizenType(@RequestBody CitizenType citizenType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createCitizenType(citizenType));
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

    //LaboratoryTestType

    @GetMapping("/lab-test-types")
    public ResponseEntity<List<LaboratoryTestType>> getLaboratoryTestTypes() {
        return ResponseEntity.ok(dictionaryService.getAllLaboratoryTestTypes());
    }

    @PostMapping("/lab-test-types")
    public ResponseEntity<LaboratoryTestType> createLaboratoryTestType(@RequestBody LaboratoryTestType laboratoryTestType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createLaboratoryTestType(laboratoryTestType));
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

    //Department

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getDepartments() {
        return ResponseEntity.ok(dictionaryService.getAllDepartments());
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionaryService.createDepartment(department));
    }
}

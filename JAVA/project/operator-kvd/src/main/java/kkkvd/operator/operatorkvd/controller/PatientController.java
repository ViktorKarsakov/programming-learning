package kkkvd.operator.operatorkvd.controller;

import jakarta.validation.Valid;
import kkkvd.operator.operatorkvd.dto.CreateCaseForPatientRequest;
import kkkvd.operator.operatorkvd.dto.DetectionCaseResponse;
import kkkvd.operator.operatorkvd.dto.PatientDetailResponse;
import kkkvd.operator.operatorkvd.dto.UpdatePatientRequest;
import kkkvd.operator.operatorkvd.entities.Patient;
import kkkvd.operator.operatorkvd.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetailResponse> getPatient(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientWithCases(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDetailResponse> updatePatient(@PathVariable Long id, @Valid @RequestBody UpdatePatientRequest request) {
        return ResponseEntity.ok(patientService.updatePatient(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/cases")
    public ResponseEntity<DetectionCaseResponse> addCaseToPatient(@PathVariable Long id, @RequestBody CreateCaseForPatientRequest request) {
        DetectionCaseResponse response = patientService.addCaseToPatient(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

package kkkvd.operator.operatorkvd.controller;

import kkkvd.operator.operatorkvd.dto.PatientDetailResponse;
import kkkvd.operator.operatorkvd.service.PatientService;
import lombok.RequiredArgsConstructor;
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
}

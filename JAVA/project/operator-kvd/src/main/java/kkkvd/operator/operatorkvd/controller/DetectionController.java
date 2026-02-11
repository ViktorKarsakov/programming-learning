package kkkvd.operator.operatorkvd.controller;

import jakarta.validation.Valid;
import kkkvd.operator.operatorkvd.dto.*;
import kkkvd.operator.operatorkvd.entities.DetectionCase;
import kkkvd.operator.operatorkvd.service.DetectionCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detection-cases")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DetectionController {
    private final DetectionCaseService detectionCaseService;

    @PostMapping
    public ResponseEntity<DetectionCaseResponse> create(@Valid @RequestBody CreateDetectionCaseRequest request) {
        DetectionCase saved = detectionCaseService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(detectionCaseService.toResponse(saved));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<PatientSearchResult>> search(@Valid @RequestBody PatientSearchRequest request) {
        Page<PatientSearchResult> results = detectionCaseService.search(request);
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<DetectionCaseResponse>> getByPatientId(@PathVariable("patientId") Long patientId) {
        List<DetectionCase> result = detectionCaseService.getByPatientId(patientId);
        List<DetectionCaseResponse> responses = result.stream()
                .map(detectionCaseService::toResponse)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaseDetailDto> getCaseById(@PathVariable Long id) {
        return ResponseEntity.ok(detectionCaseService.getCaseById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCase(@PathVariable Long id) {
        detectionCaseService.deleteCase(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaseDetailDto> updateCase(@PathVariable Long id, @RequestBody CreateCaseForPatientRequest request) {
        return ResponseEntity.ok(detectionCaseService.updateCase(id, request));
    }
}

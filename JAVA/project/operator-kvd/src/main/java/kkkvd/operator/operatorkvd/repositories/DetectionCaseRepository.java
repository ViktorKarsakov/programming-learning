package kkkvd.operator.operatorkvd.repositories;

import kkkvd.operator.operatorkvd.entities.DetectionCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DetectionCaseRepository extends JpaRepository<DetectionCase, Long>, JpaSpecificationExecutor<DetectionCase> {

    List<DetectionCase> findByPatientId(Long patientId);

    List<DetectionCase> findByDiagnosisId(Long diagnosisId);

    List<DetectionCase> findByStateId(Long stateId);

    List<DetectionCase> findByCreatedAtBetween(LocalDateTime from, LocalDateTime to);
}

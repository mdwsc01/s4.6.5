package com.example.session06.repository;

import com.example.session06.model.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    boolean existsByPatient_IdAndStatusNot(Long patientId,String status);
}

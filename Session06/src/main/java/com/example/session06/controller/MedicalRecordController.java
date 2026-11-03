package com.example.session06.controller;

import com.example.session06.model.dto.MedicalRecordRequest;
import com.example.session06.model.entity.MedicalRecord;
import com.example.session06.service.MedicalRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/medical-records")
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public ResponseEntity<?> createMedicalRecord(
            @Valid @RequestBody MedicalRecordRequest request) {

        try {
            MedicalRecord record =
                    medicalRecordService.createMedicalRecord(request);

            Map<String, Object> response = new LinkedHashMap<>();

            response.put("id", record.getId());
            response.put("doctorId", request.getDoctorId());
            response.put("patientId", request.getPatientId());
            response.put("diagnosis", record.getDiagnosis());
            response.put("status", record.getStatus());
            response.put("createdAt", record.getCreatedAt());

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);

        } catch (NoSuchElementException e) {

            Map<String, Object> error = new LinkedHashMap<>();
            error.put("message", e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error);

        } catch (IllegalArgumentException e) {

            Map<String, Object> error = new LinkedHashMap<>();
            error.put("message", e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(error);
        }
    }
}

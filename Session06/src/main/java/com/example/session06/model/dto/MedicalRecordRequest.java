package com.example.session06.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicalRecordRequest {
    @NotNull(message = "Doctor Id không được để trống")
    private Long doctorId;

    @NotNull(message = "Patient Id không được để trống")
    private Long patientId;

    @NotBlank(message = "Chuẩn đoán không được để trống")
    private String diagnosis;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public MedicalRecordRequest(Long doctorId, Long patientId, String diagnosis) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
    }

    public MedicalRecordRequest() {
    }
}

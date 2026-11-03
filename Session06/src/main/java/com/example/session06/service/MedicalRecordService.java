package com.example.session06.service;

import com.example.session06.model.dto.MedicalRecordRequest;
import com.example.session06.model.entity.Doctor;
import com.example.session06.model.entity.MedicalRecord;
import com.example.session06.model.entity.Patient;
import com.example.session06.repository.DoctorRepository;
import com.example.session06.repository.MedicalRecordRepository;
import com.example.session06.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public MedicalRecordService(
            MedicalRecordRepository medicalRecordRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    public MedicalRecord createMedicalRecord(MedicalRecordRequest request){
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(()-> new NoSuchElementException("Không tìm thấy bác sĩ với ID:"+request.getDoctorId()));

        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(()-> new NoSuchElementException("Không tìm thấy bệnh nhân với ID:"+request.getPatientId()));

        boolean hasProcessingRecord = medicalRecordRepository.existsByPatient_IdAndStatusNot(patient.getId(),"DONE");
        if(hasProcessingRecord){
            throw new IllegalArgumentException("Bệnh nhân này đang có hồ sơ điều trị chưa hoàn thành");
        }

        MedicalRecord medicalRecord = new MedicalRecord();

        medicalRecord.setPatient(patient);
        medicalRecord.setDoctor(doctor);
        medicalRecord.setDiagnosis(request.getDiagnosis());

        return medicalRecordRepository.save(medicalRecord);

    }


}

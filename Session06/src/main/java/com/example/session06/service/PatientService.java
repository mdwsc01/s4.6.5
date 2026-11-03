package com.example.session06.service;

import com.example.session06.model.dto.PaginationResponse;
import com.example.session06.model.entity.Patient;
import com.example.session06.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient) {
        patient.setId(null);
        return patientRepository.save(patient);
    }

    @Transactional
    public void deletePatient(Long id) {
        if(!patientRepository.existsById(id)) {
            throw new NoSuchElementException("Không tìm thấy bệnh nhân với id:"+id);
        }
        patientRepository.deleteById(id);
    }

    @Transactional
    public Patient updatePatient(Long id, Patient patient) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Không tìm thấy bệnh nhân với id:"+id));

        existingPatient.setPatientCode(patient.getPatientCode());
        existingPatient.setFullName(patient.getFullName());
        existingPatient.setPhone(patient.getPhone());
        existingPatient.setAddress(patient.getAddress());

        return patientRepository.save(existingPatient);
    }

    @Transactional(readOnly = true)
    public Page<Patient> findAllAndSearch(Pageable pageable,String patientName) {
        if(patientName == null || patientName.isBlank()) {
            return patientRepository.findAll(pageable);
        }
        return patientRepository.findByFullNameContainingIgnoreCase(patientName.trim(),pageable);
    }

    @Transactional(readOnly = true)
    public PaginationResponse searchPatients(String patientName,int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("fullName").ascending()
                .and(Sort.by("id").ascending()));

        Page<Patient> patientPage = findAllAndSearch(pageable, patientName);
        PaginationResponse response = new PaginationResponse();

        response.setData(patientPage.getContent());
        response.setTotalPage(patientPage.getTotalPages());
        response.setTotalElement(patientPage.getTotalElements());
        response.setCurrentPage(patientPage.getNumber());

        return response;

    }


}

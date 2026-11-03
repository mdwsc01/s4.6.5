package com.example.session06.service;

import com.example.session06.model.entity.Doctor;
import com.example.session06.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DoctorService {
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor addDoctor(Doctor doctor) {
        doctor.setId(null);
        return doctorRepository.save(doctor);
    }

    @Transactional
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new NoSuchElementException("Không tìm thấy bác sĩ với Id là: " + id);
        }
        doctorRepository.deleteById(id);
    }

    @Transactional
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy bác sĩ với Id: "+ id));

        existingDoctor.setDoctorCode(doctor.getDoctorCode());
        existingDoctor.setFullName(doctor.getFullName());
        existingDoctor.setSpecialization(doctor.getSpecialization());
        existingDoctor.setExperienceYears(doctor.getExperienceYears());

        return doctorRepository.save(existingDoctor);
    }

    @Transactional(readOnly = true)
    public List<Doctor> getAllDoctors() {
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return doctorRepository.findAll(sort);
    }
}

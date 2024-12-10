package com.hospitallentesboulevard.opticaadmin.business;

import com.hospitallentesboulevard.opticaadmin.dao.PatientRepository;
import com.hospitallentesboulevard.opticaadmin.dao.PatientViewRepository;
import com.hospitallentesboulevard.opticaadmin.model.Patient;
import com.hospitallentesboulevard.opticaadmin.payload.PatientView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientBusinessImpl implements PatientBusiness {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientViewRepository patientViewRepository;


    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public List<PatientView> findAllPatientView() {
        return patientViewRepository.findAll();
    }

    @Override
    public Patient findById(String id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient update(String id, Patient patient) {
        Optional<Patient> patientExists = patientRepository.findById(id);
        Patient patientUpdated = new Patient();
        if (patientExists.isPresent()) {
            patientExists.get().setName(patient.getName());
            patientExists.get().setLastName(patient.getLastName());
            patientExists.get().setGender(patient.getGender());
            patientExists.get().setPhoneNumber(patient.getPhoneNumber());
            patientExists.get().setEmail(patient.getEmail());
            patientUpdated = patientRepository.save(patientExists.get());
        }
        return patientUpdated;
    }

    @Override
    public void delete(String id) {
        patientRepository.deleteById(id);
    }
}

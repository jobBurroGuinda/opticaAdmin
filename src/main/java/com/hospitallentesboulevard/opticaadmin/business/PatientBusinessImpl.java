package com.hospitallentesboulevard.opticaadmin.business;

import com.hospitallentesboulevard.opticaadmin.dao.PatientRepository;
import com.hospitallentesboulevard.opticaadmin.dao.PatientViewRepository;
import com.hospitallentesboulevard.opticaadmin.dao.PrescriptionRepository;
import com.hospitallentesboulevard.opticaadmin.model.Patient;
import com.hospitallentesboulevard.opticaadmin.model.Prescription;
import com.hospitallentesboulevard.opticaadmin.payload.PatientView;
import com.hospitallentesboulevard.opticaadmin.payload.request.PatientWithPrescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientBusinessImpl implements PatientBusiness {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientViewRepository patientViewRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;


    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public List<PatientView> findAllPatientView() {
        return patientViewRepository.findAll();
    }

    @Override
    public List<PatientView> findAllPatientViewByPatient(String name) {
        List<PatientView> allPatients = patientViewRepository.findAll();
        // Filter the patients based on the name or last name containing the specified substring
        List<PatientView> patientsFiltered = allPatients.stream()
                .filter(
                        patient ->
                                patient.getName().toLowerCase().contains(name.toLowerCase()) ||
                                patient.getLastName().toLowerCase().contains(name.toLowerCase()) ||
                                patient.getObservations().toLowerCase().contains(name.toLowerCase())
                )
                .toList();
        return patientsFiltered;
    }

    @Override
    public Patient findById(String id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Patient save(PatientWithPrescription patientWithPrescription) {
        Patient patient = Patient.builder()
                .id(UUID.randomUUID().toString())
                .name(patientWithPrescription.getName())
                .lastName(patientWithPrescription.getLastName())
                .gender(patientWithPrescription.getGender())
                .phoneNumber(patientWithPrescription.getPhoneNumber())
                .email(patientWithPrescription.getEmail())
                .build();
        patientRepository.save(patient);
        boolean isPatientHasPrescription = !patientWithPrescription.getRightEye().isEmpty() ||
                !patientWithPrescription.getLeftEye().isEmpty() ||
                !patientWithPrescription.getAdd().isEmpty() ||
                !patientWithPrescription.getObservations().isEmpty();
        if (isPatientHasPrescription) {
            Prescription prescription = Prescription.builder()
                    .id(UUID.randomUUID().toString())
                    .rightEye(patientWithPrescription.getRightEye())
                    .leftEye(patientWithPrescription.getLeftEye())
                    .add(patientWithPrescription.getAdd())
                    .observations(patientWithPrescription.getObservations())
                    .patient(patient)
                    .build();
            prescriptionRepository.save(prescription);
        }
        return patient;
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

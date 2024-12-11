package com.hospitallentesboulevard.opticaadmin.business;

import com.hospitallentesboulevard.opticaadmin.dao.PatientRepository;
import com.hospitallentesboulevard.opticaadmin.model.Patient;
import com.hospitallentesboulevard.opticaadmin.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.hospitallentesboulevard.opticaadmin.dao.PrescriptionRepository;

import javax.transaction.Transactional;

@Service
public class PrescriptionBusinessImpl implements PrescriptionBusiness {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public List<Prescription> findAll(String idPatient) {
        List<Prescription> prescriptions = prescriptionRepository.findAllByPatientOrOrder(idPatient);
        if (!prescriptions.isEmpty()) {
            return prescriptions;
        }
        return List.of();
    }

    @Override
    public Prescription findById(String id) {
        return prescriptionRepository.findById(id).orElse(null);
    }

    @Override
    public Prescription save(Prescription prescription) {
        Patient patient = Patient.builder().id(prescription.getPatient().getId()).build();
        prescription.setId(UUID.randomUUID().toString());
        prescription.setPatient(patient);
        Date date = new Date();
        prescription.setDateCreation(date);
        prescription.setDateLastUpdate(date);
        return prescriptionRepository.save(prescription);
    }

    @Override
    @Transactional
    public Prescription update(String id, Prescription prescription) {
        Optional<Prescription> prescriptionExists = prescriptionRepository.findById(id);
        Prescription prescriptionUpdated = new Prescription();
        if (prescriptionExists.isPresent()) {
            prescriptionExists.get().setRightEye(prescription.getRightEye());
            prescriptionExists.get().setLeftEye(prescription.getLeftEye());
            prescriptionExists.get().setAdd(prescription.getAdd());
            prescriptionExists.get().setObservations(prescription.getObservations());
            prescriptionExists.get().setDateLastUpdate(new Date());
            Patient patient = Patient.builder().id(prescription.getPatient().getId()).build();
            prescriptionExists.get().setPatient(patient);
            prescriptionUpdated = prescriptionRepository.save(prescriptionExists.get());
        }
        return prescriptionUpdated;
    }

    @Override
    public void delete(String id) {
        prescriptionRepository.deleteById(id);
    }
}

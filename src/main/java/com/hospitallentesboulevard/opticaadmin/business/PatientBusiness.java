package com.hospitallentesboulevard.opticaadmin.business;

import com.hospitallentesboulevard.opticaadmin.model.Patient;
import com.hospitallentesboulevard.opticaadmin.payload.PatientView;

import java.util.List;

public interface PatientBusiness {

    List<Patient> findAll();
    List<PatientView> findAllPatientView();
    Patient findById(String id);
    Patient save(Patient patient);
    Patient update(String id, Patient patient);
    void delete(String id);

}

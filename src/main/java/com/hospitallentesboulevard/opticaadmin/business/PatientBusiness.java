package com.hospitallentesboulevard.opticaadmin.business;

import com.hospitallentesboulevard.opticaadmin.model.Patient;
import com.hospitallentesboulevard.opticaadmin.payload.PatientView;
import com.hospitallentesboulevard.opticaadmin.payload.request.PatientWithPrescription;

import java.util.List;

public interface PatientBusiness {

    List<Patient> findAll();
    List<PatientView> findAllPatientView();
    List<PatientView> findAllPatientViewByPatient(String name);
    Patient findById(String id);
    Patient save(PatientWithPrescription patientWithPrescription);
    Patient update(String id, Patient patient);
    void delete(String id);

}

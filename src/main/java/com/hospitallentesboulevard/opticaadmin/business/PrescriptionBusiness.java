package com.hospitallentesboulevard.opticaadmin.business;

import com.hospitallentesboulevard.opticaadmin.model.Prescription;

import java.util.List;

public interface PrescriptionBusiness {

    List<Prescription> findAll(String idPatient);
    Prescription findById(String id);
    Prescription save(Prescription prescription);
    Prescription update(String id, Prescription prescription);
    void delete(String id);

}

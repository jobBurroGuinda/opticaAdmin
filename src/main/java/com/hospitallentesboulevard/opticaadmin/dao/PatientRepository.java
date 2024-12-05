package com.hospitallentesboulevard.opticaadmin.dao;

import com.hospitallentesboulevard.opticaadmin.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

}

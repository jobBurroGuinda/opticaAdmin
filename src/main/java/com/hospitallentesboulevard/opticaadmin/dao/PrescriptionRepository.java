package com.hospitallentesboulevard.opticaadmin.dao;

import com.hospitallentesboulevard.opticaadmin.model.Patient;
import com.hospitallentesboulevard.opticaadmin.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, String> {

    @Query(value = "SELECT * FROM prescription WHERE patient_id=:patient_id ORDER BY date_creation, date_last_update ASC", nativeQuery = true)
    List<Prescription> findAllByPatientOrOrder(@Param("patient_id") String patientId);

}

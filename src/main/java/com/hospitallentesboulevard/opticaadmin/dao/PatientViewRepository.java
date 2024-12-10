package com.hospitallentesboulevard.opticaadmin.dao;

import com.hospitallentesboulevard.opticaadmin.payload.PatientView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientViewRepository extends JpaRepository<PatientView, String> {
}

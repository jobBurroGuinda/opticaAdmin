package com.hospitallentesboulevard.opticaadmin.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Subselect("SELECT " +
        "p.id AS id, " +
        "p.name AS name, p.last_name AS last_name, " +
        "g.id AS prescription_id, " +
        "g.right_eye AS right_eye, " +
        "g.left_eye AS left_eye, " +
        "g.add AS add, " +
        "g.observations AS observations " +
        "FROM " +
        "patient AS p " +
        "INNER JOIN (" +
        "SELECT g.*, " +
        "ROW_NUMBER() OVER (PARTITION BY g.patient_id ORDER BY g.date_creation DESC) AS rn " +
        "FROM prescription AS g " +
        ") AS g ON p.id = g.patient_id AND g.rn = 1")
public class PatientView {
    @Id
    private String id;
    private String name;
    private String lastName;
    private String prescriptionId;
    private String rightEye;
    private String leftEye;
    private String add;
    private String observations;
}
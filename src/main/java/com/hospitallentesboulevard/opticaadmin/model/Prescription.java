package com.hospitallentesboulevard.opticaadmin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {

    @Id
    private String id;

    @Column(length = 30)
    private String rightEye = "";

    @Column(length = 30)
    private String leftEye = "";

    @Column(length = 30)
    private String add = "";

    private Date date = new Date();

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

}

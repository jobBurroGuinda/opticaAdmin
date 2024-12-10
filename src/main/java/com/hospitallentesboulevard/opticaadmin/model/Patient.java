package com.hospitallentesboulevard.opticaadmin.model;

import com.hospitallentesboulevard.opticaadmin.utils.GenderConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    private String id;

    @Column(length = 50)
    private String name = "";

    @Column(length = 50)
    private String lastName = "";

    private char gender = GenderConstants.NOT_SPECIFIC;

    @Column(length = 13)
    private String phoneNumber = "";

    @Column(length = 50)
    private String email = "";

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Prescription> prescriptions = new HashSet<>();

    public String getFullName() {
        return this.name + " " + this.lastName;
    }

}

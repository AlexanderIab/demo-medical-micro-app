package org.iablonski.medical.license.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Entity
@Table(name = "licenses")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class License extends RepresentationModel<License> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "license_id", nullable = false)
    private UUID licenseId;
    @Column(name = "organisation_id", nullable = false)
    private UUID organisationId;
    @Column(name = "license_type", nullable = false)
    private String licenseType;
    @Column(name = "service_type", nullable = false)
    private String serviceType;

    private String trace;

    public License licenseWithTrace(String trace) {
        this.setTrace(trace);
        return this;
    }
}

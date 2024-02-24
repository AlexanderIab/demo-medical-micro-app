package org.iablonski.medical.organisation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Entity
@Table(name = "organisations")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Organisation extends RepresentationModel<Organisation> {
    @Id
    @Column(name = "organisation_id", nullable = false)
    private UUID organisationId;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "email", nullable = false)
    String email;
    @Column(name = "phone", nullable = false)
    String phone;
}
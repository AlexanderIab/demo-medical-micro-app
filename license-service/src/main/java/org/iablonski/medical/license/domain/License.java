package org.iablonski.medical.license.domain;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Data
public class License extends RepresentationModel<License> {
    private UUID id;
    private String licenseId;
    private String title;
    private String organizationId;
    private String productName;
    private String licenseType;
}

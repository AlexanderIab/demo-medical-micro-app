package org.iablonski.medical.license.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class License {
    private UUID id;
    private String licenseId;
    private String title;
    private String organizationId;
    private String productName;
    private String licenseType;
}

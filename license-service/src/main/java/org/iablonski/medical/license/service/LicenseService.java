package org.iablonski.medical.license.service;

import org.iablonski.medical.license.domain.License;

public interface LicenseService {

    License getLicense(String licenseId, String organizationId);
    String createLicense(License license, String organizationId);
    String updateLicense(License license, String organizationId);
    String deleteLicense(String licenseId, String organizationId);
}

package org.iablonski.medical.license.service;

import org.iablonski.medical.license.domain.License;

import java.util.Locale;

public interface LicenseService {

    License getLicense(String licenseId, String organizationId);
    String createLicense(License license, String organizationId, Locale locale);
    String updateLicense(License license, String organizationId);
    String deleteLicense(String licenseId, String organizationId, Locale locale);
}

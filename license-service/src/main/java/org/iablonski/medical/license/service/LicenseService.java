package org.iablonski.medical.license.service;

import org.iablonski.medical.license.domain.License;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

public interface LicenseService {

    License getLicenseByLicenseIdAndOrganisationId(UUID licenseId, UUID organisationId, Locale locale);
    String createLicense(License license, Locale locale);
    String updateLicense(License license, Locale locale);
    String deleteLicense(UUID licenseId, Locale locale);
//    List<License> getListOfLicensesByOrganisationId(UUID organisationId);
}

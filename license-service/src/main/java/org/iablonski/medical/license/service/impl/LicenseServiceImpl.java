package org.iablonski.medical.license.service.impl;

import org.iablonski.medical.license.domain.License;
import org.iablonski.medical.license.service.LicenseService;
import org.springframework.stereotype.Service;

@Service
public class LicenseServiceImpl implements LicenseService {
    @Override
    public License getLicense(String licenseId, String organizationId) {
        return null;
    }

    @Override
    public String createLicense(License license, String organizationId) {
        return null;
    }

    @Override
    public String updateLicense(License license, String organizationId) {
        return null;
    }

    @Override
    public String deleteLicense(String licenseId, String organizationId) {
        return null;
    }
}

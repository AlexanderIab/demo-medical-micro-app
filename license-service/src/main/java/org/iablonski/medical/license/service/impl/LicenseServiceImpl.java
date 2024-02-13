package org.iablonski.medical.license.service.impl;

import org.iablonski.medical.license.domain.License;
import org.iablonski.medical.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

@Service
public class LicenseServiceImpl implements LicenseService {

    MessageSource messageSource;

    @Autowired
    public LicenseServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public License getLicense(String licenseId, String organizationId) {
        License license = new License();
        license.setId(UUID.randomUUID());
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setTitle("Medical research");
        license.setProductName("Some lab");
        license.setLicenseType("part");
        return license;
    }

    @Override
    public String createLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(
                    messageSource.getMessage(
                            "license.create.message", null, locale), license);
        }
        return responseMessage;
    }

    @Override
    public String updateLicense(License license, String organizationId) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messageSource.getMessage(
                            "license.update.message", null, null), license);
        }
        return responseMessage;
    }

    @Override
    public String deleteLicense(String licenseId, String organizationId, Locale locale) {
        String responseMessage = null;
        responseMessage = String.format(messageSource.getMessage(
                "license.delete.message", null, locale), licenseId, organizationId);
        return responseMessage;
    }
}

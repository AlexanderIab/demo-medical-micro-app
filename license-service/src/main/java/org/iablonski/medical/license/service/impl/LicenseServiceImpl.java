package org.iablonski.medical.license.service.impl;

import org.iablonski.medical.license.configuration.LicensePropConfig;
import org.iablonski.medical.license.domain.License;
import org.iablonski.medical.license.repository.LicenseRepo;
import org.iablonski.medical.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
public class LicenseServiceImpl implements LicenseService {

    private final MessageSource messageSource;
    private final LicenseRepo licenseRepo;
    private final LicensePropConfig config;

    @Autowired
    public LicenseServiceImpl(MessageSource messageSource, LicenseRepo licenseRepo, LicensePropConfig config) {
        this.messageSource = messageSource;
        this.licenseRepo = licenseRepo;
        this.config = config;
    }

    @Override
    public License getLicenseByLicenseIdAndOrganisationId(UUID licenseId, UUID organisationId, Locale locale) {
        License license = licenseRepo.findByLicenseIdAndOrganisationId(licenseId, organisationId)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(messageSource.getMessage(
                                "license.search.error.message", null, locale),
                                licenseId, organisationId)));
        return license.licenseWithTrace(config.getProperty());
    }

    @Override
    public String createLicense(License license,  Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setTrace(config.getProperty());
            licenseRepo.save(license);
            responseMessage = String.format(
                    messageSource.getMessage(
                            "license.create.message", null, locale), license);
        }
        return responseMessage;
    }

    @Override
    public String updateLicense(License license, Locale locale) {
        String responseMessage = null;
        boolean licenseExists = licenseRepo.existsById(license.getLicenseId());
        if (licenseExists) {
            license.setOrganisationId(license.getOrganisationId());
            license.setLicenseType(license.getLicenseType());
            license.setServiceType(license.getServiceType());
            license.setTrace(config.getProperty());
            responseMessage = String.format(messageSource.getMessage(
                    "license.update.message", null, locale), license);
        }
        return responseMessage;
    }

    @Override
    public String deleteLicense(UUID licenseId, Locale locale) {
        licenseRepo.deleteById(licenseId);
        return String.format(messageSource.getMessage(
                "license.delete.message", null, locale), licenseId);
    }
}

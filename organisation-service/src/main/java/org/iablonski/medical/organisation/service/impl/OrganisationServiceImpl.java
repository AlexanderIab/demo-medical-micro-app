package org.iablonski.medical.organisation.service.impl;

import org.iablonski.medical.organisation.domain.Organisation;
import org.iablonski.medical.organisation.repository.OrganisationRepo;
import org.iablonski.medical.organisation.service.OrganisationService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
public class OrganisationServiceImpl implements OrganisationService {

    private final MessageSource messageSource;
    private final OrganisationRepo organisationRepo;

    public OrganisationServiceImpl(MessageSource messageSource, OrganisationRepo organisationRepo) {
        this.messageSource = messageSource;
        this.organisationRepo = organisationRepo;
    }

    @Override
    public Organisation getOrganisationByOrganisationId(UUID organisationId, Locale locale) {
        return organisationRepo.findById(organisationId)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(messageSource.getMessage(
                                        "organisation.search.error.message", null, locale), organisationId)));
    }

    @Override
    public String createOrganisation(Organisation organisation, Locale locale) {
        String responseMessage = null;
        if (organisation != null) {
            organisationRepo.save(organisation);
            responseMessage = String.format(
                    messageSource.getMessage(
                            "organisation.create.message", null, locale), organisation);
        }
        return responseMessage;
    }

    @Override
    public String updateOrganisation(Organisation organisation, Locale locale) {
        String responseMessage = null;
        boolean organisationExists = organisationRepo.existsById(organisation.getOrganisationId());
        if (organisationExists) {
            Organisation savingOrganisation = new Organisation();
            savingOrganisation.setName(organisation.getName());
            savingOrganisation.setEmail(organisation.getEmail());
            savingOrganisation.setPhone(organisation.getPhone());
            organisationRepo.save(savingOrganisation);
            responseMessage = String.format(messageSource.getMessage(
                    "organisation.update.message", null, locale), savingOrganisation);
        }
        return responseMessage;
    }

    @Override
    public String deleteOrganisation(UUID organisationId, Locale locale) {
        organisationRepo.deleteById(organisationId);
        return String.format(messageSource.getMessage(
                "organisation.delete.message", null, locale), organisationId);
    }
}
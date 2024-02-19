package org.iablonski.medical.organisation.service;

import org.iablonski.medical.organisation.domain.Organisation;

import java.util.Locale;
import java.util.UUID;

public interface OrganisationService {
    Organisation getOrganisationByOrganisationId(UUID organisationId, Locale locale);
    String createOrganisation(Organisation organisation, Locale locale);
    String updateOrganisation(Organisation organisation, Locale locale);
    String deleteOrganisation(UUID organisationId, Locale locale);
}

package org.iablonski.medical.organisation.controller;

import org.iablonski.medical.organisation.domain.Organisation;
import org.iablonski.medical.organisation.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value="v1/organization")
public class OrganisationController {

    private final OrganisationService organisationService;

    @Autowired
    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @PostMapping(value = "/id")
    public ResponseEntity<Organisation> getOrganisation(@RequestBody UUID organisationId,
                                                        @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        Organisation organisation = organisationService.getOrganisationByOrganisationId(organisationId, locale);
        organisation.add(linkTo(methodOn(OrganisationController.class)
                        .getOrganisation(organisationId, locale))
                        .withSelfRel(),
                linkTo(methodOn(OrganisationController.class)
                        .createOrganisation(organisation, locale))
                        .withRel("createOrganisation"),
                linkTo(methodOn(OrganisationController.class)
                        .updateOrganisation(organisation, locale))
                        .withRel("updateOrganisation"),
                linkTo(methodOn(OrganisationController.class)
                        .deleteOrganisation(organisation.getOrganisationId(), locale))
                        .withRel("deleteOrganisation"));
        return ResponseEntity.ok(organisation);
    }

    @PutMapping
    public ResponseEntity<String> updateOrganisation(@RequestBody Organisation organisation,
                                                     @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(organisationService.updateOrganisation(organisation, locale));
    }

    @PostMapping
    public ResponseEntity<String> createOrganisation(@RequestBody Organisation organization,
                                                     @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(organisationService.createOrganisation(organization, locale));
    }

    @DeleteMapping(value = "/{organisationId}")
    public ResponseEntity<String> deleteOrganisation(@PathVariable("organisationId") UUID organisationId,
                                                     @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(organisationService.deleteOrganisation(organisationId, locale));
    }
}

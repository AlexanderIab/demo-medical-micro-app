package org.iablonski.medical.license.controller;

import org.iablonski.medical.license.domain.License;
import org.iablonski.medical.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
// OrganisationId -> different clients that use the service
@RequestMapping("v1/organisation/{organisationId}/license")
public class LicenseController {
    private final LicenseService licenseService;

    @Autowired
    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @PostMapping("/id")
    public ResponseEntity<License> getLicense(@PathVariable("organisationId") UUID organisationId,
                                              @RequestBody UUID licenseId,
                                              @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        License license = licenseService.getLicenseByLicenseIdAndOrganisationId(licenseId, organisationId, locale);
        license.add(linkTo(methodOn(LicenseController.class)
                        .getLicense(organisationId, license.getLicenseId(), locale))
                        .withSelfRel(),
                linkTo(methodOn(LicenseController.class)
                        .createLicense(license, organisationId, locale))
                        .withRel("createLicense"),
                linkTo(methodOn(LicenseController.class)
                        .updateLicense(organisationId, license, locale))
                        .withRel("updateLicense"),
                linkTo(methodOn(LicenseController.class)
                        .deleteLicense(license.getLicenseId(), organisationId, locale))
                        .withRel("deleteLicense"));
        return ResponseEntity.ok(license);
    }

    //TODO:Message Response extends HATEOAS after WebFlux
    @PutMapping
    public ResponseEntity<String> updateLicense(@PathVariable("organisationId") UUID organisationId,
                                                @RequestBody License license,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) {

        return ResponseEntity.ok(licenseService.updateLicense(license, locale));
    }

    //TODO:Message Response extends HATEOAS after WebFlux
    @PostMapping
    public ResponseEntity<String> createLicense(@RequestBody License request,
                                                @PathVariable("organisationId") UUID organisationId,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(licenseService.createLicense(request, locale));
    }

    //TODO:Message Response extends HATEOAS after WebFlux
    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable("licenseId") UUID licenseId,
                                                @PathVariable("organisationId") UUID organisationId,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, locale));
    }
}
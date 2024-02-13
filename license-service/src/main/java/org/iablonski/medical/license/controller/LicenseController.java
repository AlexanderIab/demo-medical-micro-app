package org.iablonski.medical.license.controller;

import org.iablonski.medical.license.domain.License;
import org.iablonski.medical.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

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

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable("organisationId") String organisationId,
                                              @PathVariable("licenseId") String licenseId) {
        License license = licenseService.getLicense(licenseId, organisationId);
        license.add(linkTo(methodOn(LicenseController.class)
                        .getLicense(organisationId, license.getLicenseId()))
                        .withSelfRel(),
                linkTo(methodOn(LicenseController.class)
                        .createLicense(license, organisationId, null))
                        .withRel("createLicense"),
                linkTo(methodOn(LicenseController.class)
                        .updateLicense(organisationId, license))
                        .withRel("updateLicense"),
                linkTo(methodOn(LicenseController.class)
                        .deleteLicense(license.getLicenseId(), organisationId, null))
                        .withRel("deleteLicense"));
        return ResponseEntity.ok(license);
    }
    //TODO:Message Response extends HATEOAS after WebFlux
    @PutMapping
    public ResponseEntity<String> updateLicense(@PathVariable("organisationId") String organizationId,
                                                @RequestBody License request) {

        return ResponseEntity.ok(licenseService.updateLicense(request, organizationId));
    }
    //TODO:Message Response extends HATEOAS after WebFlux
    @PostMapping
    public ResponseEntity<String> createLicense(@RequestBody License request,
                                                @PathVariable("organisationId") String organizationId,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(licenseService.createLicense(request, organizationId, locale));
    }
    //TODO:Message Response extends HATEOAS after WebFlux
    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable("licenseId") String licenseId,
                                                @PathVariable("organisationId") String organizationId,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId, locale));
    }
}

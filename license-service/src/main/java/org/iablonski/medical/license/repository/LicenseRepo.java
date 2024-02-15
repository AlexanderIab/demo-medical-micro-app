package org.iablonski.medical.license.repository;

import org.iablonski.medical.license.domain.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LicenseRepo extends CrudRepository<License, UUID> {

    List<License> findByOrganisationId(UUID organisationId);
    Optional<License> findByLicenseIdAndOrganisationId(UUID licenseId, UUID organisationId);
}

package org.iablonski.medical.organisation.repository;

import org.iablonski.medical.organisation.domain.Organisation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganisationRepo extends CrudRepository<Organisation, UUID> {

}

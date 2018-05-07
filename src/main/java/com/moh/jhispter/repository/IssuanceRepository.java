package com.moh.jhispter.repository;

import com.moh.jhispter.domain.Issuance;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Issuance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IssuanceRepository extends JpaRepository<Issuance, Long> {

}

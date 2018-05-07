package com.moh.jhispter.repository;

import com.moh.jhispter.domain.Issuer;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Issuer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IssuerRepository extends JpaRepository<Issuer, Long> {

}

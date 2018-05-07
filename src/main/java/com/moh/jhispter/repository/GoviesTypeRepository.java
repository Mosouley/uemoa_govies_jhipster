package com.moh.jhispter.repository;

import com.moh.jhispter.domain.GoviesType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the GoviesType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GoviesTypeRepository extends JpaRepository<GoviesType, Long> {

}

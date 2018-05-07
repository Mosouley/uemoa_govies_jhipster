package com.moh.jhispter.service;

import com.moh.jhispter.domain.GoviesType;
import java.util.List;

/**
 * Service Interface for managing GoviesType.
 */
public interface GoviesTypeService {

    /**
     * Save a goviesType.
     *
     * @param goviesType the entity to save
     * @return the persisted entity
     */
    GoviesType save(GoviesType goviesType);

    /**
     * Get all the goviesTypes.
     *
     * @return the list of entities
     */
    List<GoviesType> findAll();

    /**
     * Get the "id" goviesType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    GoviesType findOne(Long id);

    /**
     * Delete the "id" goviesType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

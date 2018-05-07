package com.moh.jhispter.service;

import com.moh.jhispter.domain.Issuer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Issuer.
 */
public interface IssuerService {

    /**
     * Save a issuer.
     *
     * @param issuer the entity to save
     * @return the persisted entity
     */
    Issuer save(Issuer issuer);

    /**
     * Get all the issuers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Issuer> findAll(Pageable pageable);

    /**
     * Get the "id" issuer.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Issuer findOne(Long id);

    /**
     * Delete the "id" issuer.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

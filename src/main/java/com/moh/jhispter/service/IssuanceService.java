package com.moh.jhispter.service;

import com.moh.jhispter.domain.Issuance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing Issuance.
 */
public interface IssuanceService {

    /**
     * Save a issuance.
     *
     * @param issuance the entity to save
     * @return the persisted entity
     */
    Issuance save(Issuance issuance);

    /**
     * Get all the issuances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Issuance> findAll(Pageable pageable);

    /**
     * Get the "id" issuance.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Issuance findOne(Long id);

    /**
     * Delete the "id" issuance.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Issuance uploadMinutes(Long id, MultipartFile file);
}

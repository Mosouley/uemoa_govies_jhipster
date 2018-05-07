package com.moh.jhispter.service.impl;

import com.moh.jhispter.service.IssuerService;
import com.moh.jhispter.domain.Issuer;
import com.moh.jhispter.repository.IssuerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Issuer.
 */
@Service
@Transactional
public class IssuerServiceImpl implements IssuerService {

    private final Logger log = LoggerFactory.getLogger(IssuerServiceImpl.class);

    private final IssuerRepository issuerRepository;

    public IssuerServiceImpl(IssuerRepository issuerRepository) {
        this.issuerRepository = issuerRepository;
    }

    /**
     * Save a issuer.
     *
     * @param issuer the entity to save
     * @return the persisted entity
     */
    @Override
    public Issuer save(Issuer issuer) {
        log.debug("Request to save Issuer : {}", issuer);
        return issuerRepository.save(issuer);
    }

    /**
     * Get all the issuers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Issuer> findAll(Pageable pageable) {
        log.debug("Request to get all Issuers");
        return issuerRepository.findAll(pageable);
    }

    /**
     * Get one issuer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Issuer findOne(Long id) {
        log.debug("Request to get Issuer : {}", id);
        return issuerRepository.findOne(id);
    }

    /**
     * Delete the issuer by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Issuer : {}", id);
        issuerRepository.delete(id);
    }

}

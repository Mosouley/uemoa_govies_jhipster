package com.moh.jhispter.service.impl;

import com.moh.jhispter.service.GoviesTypeService;
import com.moh.jhispter.domain.GoviesType;
import com.moh.jhispter.repository.GoviesTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing GoviesType.
 */
@Service
@Transactional
public class GoviesTypeServiceImpl implements GoviesTypeService {

    private final Logger log = LoggerFactory.getLogger(GoviesTypeServiceImpl.class);

    private final GoviesTypeRepository goviesTypeRepository;

    public GoviesTypeServiceImpl(GoviesTypeRepository goviesTypeRepository) {
        this.goviesTypeRepository = goviesTypeRepository;
    }

    /**
     * Save a goviesType.
     *
     * @param goviesType the entity to save
     * @return the persisted entity
     */
    @Override
    public GoviesType save(GoviesType goviesType) {
        log.debug("Request to save GoviesType : {}", goviesType);
        return goviesTypeRepository.save(goviesType);
    }

    /**
     * Get all the goviesTypes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<GoviesType> findAll() {
        log.debug("Request to get all GoviesTypes");
        return goviesTypeRepository.findAll();
    }

    /**
     * Get one goviesType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public GoviesType findOne(Long id) {
        log.debug("Request to get GoviesType : {}", id);
        return goviesTypeRepository.findOne(id);
    }

    /**
     * Delete the goviesType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GoviesType : {}", id);
        goviesTypeRepository.delete(id);
    }
}

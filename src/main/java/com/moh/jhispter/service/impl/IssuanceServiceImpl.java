package com.moh.jhispter.service.impl;

import com.moh.jhispter.service.IssuanceService;
import com.moh.jhispter.domain.Issuance;
import com.moh.jhispter.repository.IssuanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Service Implementation for managing Issuance.
 */
@Service
@Transactional
public class IssuanceServiceImpl implements IssuanceService {

    private final Logger log = LoggerFactory.getLogger(IssuanceServiceImpl.class);

    private final IssuanceRepository issuanceRepository;


    private static final String UPLOADED_FOLDER = "~/MODEV/projetPerso/goviesuemoa/data/"; //C://temp//";

    public IssuanceServiceImpl(IssuanceRepository issuanceRepository) {
        this.issuanceRepository = issuanceRepository;
    }

    /**
     * Save a issuance.
     *
     * @param issuance the entity to save
     * @return the persisted entity
     */
    @Override
    public Issuance save(Issuance issuance) {
        log.debug("Request to save Issuance : {}", issuance);
        return issuanceRepository.save(issuance);
    }

    /**
     * Get all the issuances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Issuance> findAll(Pageable pageable) {
        log.debug("Request to get all Issuances");
        return issuanceRepository.findAll(pageable);
    }

    /**
     * Get one issuance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Issuance findOne(Long id) {
        log.debug("Request to get Issuance : {}", id);
        return issuanceRepository.findOne(id);
    }

    /**
     * Delete the issuance by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Issuance : {}", id);
        issuanceRepository.delete(id);
    }

    @Override
    public Issuance uploadMinutes(Long id, MultipartFile file) {


            log.debug("Uploading avatar");
            Issuance issuance = issuanceRepository.findOne(id);
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
                issuance.setMinutesIssuance(UPLOADED_FOLDER + file.getOriginalFilename());
                issuanceRepository.save(issuance);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  issuance;   //participantMapper.toDto(participant);

    }


}

package com.moh.jhispter.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.moh.jhispter.domain.GoviesType;
import com.moh.jhispter.service.GoviesTypeService;
import com.moh.jhispter.web.rest.errors.BadRequestAlertException;
import com.moh.jhispter.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing GoviesType.
 */
@RestController
@RequestMapping("/api")
public class GoviesTypeResource {

    private final Logger log = LoggerFactory.getLogger(GoviesTypeResource.class);

    private static final String ENTITY_NAME = "goviesType";

    private final GoviesTypeService goviesTypeService;

    public GoviesTypeResource(GoviesTypeService goviesTypeService) {
        this.goviesTypeService = goviesTypeService;
    }

    /**
     * POST  /govies-types : Create a new goviesType.
     *
     * @param goviesType the goviesType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new goviesType, or with status 400 (Bad Request) if the goviesType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/govies-types")
    @Timed
    public ResponseEntity<GoviesType> createGoviesType(@Valid @RequestBody GoviesType goviesType) throws URISyntaxException {
        log.debug("REST request to save GoviesType : {}", goviesType);
        if (goviesType.getId() != null) {
            throw new BadRequestAlertException("A new goviesType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GoviesType result = goviesTypeService.save(goviesType);
        return ResponseEntity.created(new URI("/api/govies-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /govies-types : Updates an existing goviesType.
     *
     * @param goviesType the goviesType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated goviesType,
     * or with status 400 (Bad Request) if the goviesType is not valid,
     * or with status 500 (Internal Server Error) if the goviesType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/govies-types")
    @Timed
    public ResponseEntity<GoviesType> updateGoviesType(@Valid @RequestBody GoviesType goviesType) throws URISyntaxException {
        log.debug("REST request to update GoviesType : {}", goviesType);
        if (goviesType.getId() == null) {
            return createGoviesType(goviesType);
        }
        GoviesType result = goviesTypeService.save(goviesType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, goviesType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /govies-types : get all the goviesTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of goviesTypes in body
     */
    @GetMapping("/govies-types")
    @Timed
    public List<GoviesType> getAllGoviesTypes() {
        log.debug("REST request to get all GoviesTypes");
        return goviesTypeService.findAll();
        }

    /**
     * GET  /govies-types/:id : get the "id" goviesType.
     *
     * @param id the id of the goviesType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the goviesType, or with status 404 (Not Found)
     */
    @GetMapping("/govies-types/{id}")
    @Timed
    public ResponseEntity<GoviesType> getGoviesType(@PathVariable Long id) {
        log.debug("REST request to get GoviesType : {}", id);
        GoviesType goviesType = goviesTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(goviesType));
    }

    /**
     * DELETE  /govies-types/:id : delete the "id" goviesType.
     *
     * @param id the id of the goviesType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/govies-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteGoviesType(@PathVariable Long id) {
        log.debug("REST request to delete GoviesType : {}", id);
        goviesTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

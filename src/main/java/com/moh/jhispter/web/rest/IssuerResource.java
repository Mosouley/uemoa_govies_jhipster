package com.moh.jhispter.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.moh.jhispter.domain.Issuer;
import com.moh.jhispter.service.IssuerService;
import com.moh.jhispter.web.rest.errors.BadRequestAlertException;
import com.moh.jhispter.web.rest.util.HeaderUtil;
import com.moh.jhispter.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Issuer.
 */
@RestController
@RequestMapping("/api")
public class IssuerResource {

    private final Logger log = LoggerFactory.getLogger(IssuerResource.class);

    private static final String ENTITY_NAME = "issuer";

    private final IssuerService issuerService;

    public IssuerResource(IssuerService issuerService) {
        this.issuerService = issuerService;
    }

    /**
     * POST  /issuers : Create a new issuer.
     *
     * @param issuer the issuer to create
     * @return the ResponseEntity with status 201 (Created) and with body the new issuer, or with status 400 (Bad Request) if the issuer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/issuers")
    @Timed
    public ResponseEntity<Issuer> createIssuer(@Valid @RequestBody Issuer issuer) throws URISyntaxException {
        log.debug("REST request to save Issuer : {}", issuer);
        if (issuer.getId() != null) {
            throw new BadRequestAlertException("A new issuer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Issuer result = issuerService.save(issuer);
        return ResponseEntity.created(new URI("/api/issuers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /issuers : Updates an existing issuer.
     *
     * @param issuer the issuer to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated issuer,
     * or with status 400 (Bad Request) if the issuer is not valid,
     * or with status 500 (Internal Server Error) if the issuer couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/issuers")
    @Timed
    public ResponseEntity<Issuer> updateIssuer(@Valid @RequestBody Issuer issuer) throws URISyntaxException {
        log.debug("REST request to update Issuer : {}", issuer);
        if (issuer.getId() == null) {
            return createIssuer(issuer);
        }
        Issuer result = issuerService.save(issuer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, issuer.getId().toString()))
            .body(result);
    }

    /**
     * GET  /issuers : get all the issuers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of issuers in body
     */
    @GetMapping("/issuers")
    @Timed
    public ResponseEntity<List<Issuer>> getAllIssuers(Pageable pageable) {
        log.debug("REST request to get a page of Issuers");
        Page<Issuer> page = issuerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/issuers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /issuers/:id : get the "id" issuer.
     *
     * @param id the id of the issuer to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the issuer, or with status 404 (Not Found)
     */
    @GetMapping("/issuers/{id}")
    @Timed
    public ResponseEntity<Issuer> getIssuer(@PathVariable Long id) {
        log.debug("REST request to get Issuer : {}", id);
        Issuer issuer = issuerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(issuer));
    }

    /**
     * DELETE  /issuers/:id : delete the "id" issuer.
     *
     * @param id the id of the issuer to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/issuers/{id}")
    @Timed
    public ResponseEntity<Void> deleteIssuer(@PathVariable Long id) {
        log.debug("REST request to delete Issuer : {}", id);
        issuerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

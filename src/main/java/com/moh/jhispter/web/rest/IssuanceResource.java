package com.moh.jhispter.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.moh.jhispter.domain.Issuance;
import com.moh.jhispter.service.IssuanceService;
import com.moh.jhispter.web.rest.errors.BadRequestAlertException;
import com.moh.jhispter.web.rest.util.HeaderUtil;
import com.moh.jhispter.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Issuance.
 */
@RestController
@RequestMapping("/api")
public class IssuanceResource {

    private final Logger log = LoggerFactory.getLogger(IssuanceResource.class);

    private static final String ENTITY_NAME = "issuance";

    private final IssuanceService issuanceService;



//    @Value(value = "${dir.minutesIssuance}")
//    private String rootDirectory;

    public IssuanceResource(IssuanceService issuanceService) {
        this.issuanceService = issuanceService;
    }

    /**
     * POST  /issuances : Create a new issuance.
     *
     * @param issuance the issuance to create
     * @return the ResponseEntity with status 201 (Created) and with body the new issuance, or with status 400 (Bad Request) if the issuance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(name = "/issuances",produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Issuance> createIssuance(@Valid @RequestBody Issuance issuance,@RequestParam(value = "minutes") MultipartFile file) throws URISyntaxException {
        log.debug("REST request to save Issuance : {}", issuance);
        if (issuance.getId() != null) {
            throw new BadRequestAlertException("A new issuance cannot already have an ID", ENTITY_NAME, "idexists");
        }

        //Set the minutes of the issuance as a MultipartFile
        //only if the is indicated
        if(!file.isEmpty()) issuance.setMinutesIssuance(file.getOriginalFilename());

//    log.debug("the file to be uploaded  : {}", file.getOriginalFilename());
//
        Issuance result = issuanceService.save(issuance);
//        if(!file.isEmpty()) {
//            issuance.setMinutesIssuance(file.getOriginalFilename());
////			file.transferTo(new File(imageDir+file.getOriginalFilename()));
//            try {
//                file.transferTo(new File(rootDirectory+file.getOriginalFilename()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return ResponseEntity.created(new URI("/api/issuances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /issuances : Updates an existing issuance.
     *
     * @param issuance the issuance to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated issuance,
     * or with status 400 (Bad Request) if the issuance is not valid,
     * or with status 500 (Internal Server Error) if the issuance couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/issuances")
    @Timed
    public ResponseEntity<Issuance> updateIssuance(@Valid @RequestBody Issuance issuance,@RequestParam(value = "minutes") MultipartFile file) throws URISyntaxException {
        log.debug("REST request to update Issuance : {}", issuance);
        if (issuance.getId() == null) return createIssuance(issuance,file);
        Issuance result = issuanceService.save(issuance);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, issuance.getId().toString()))
            .body(result);
    }

    /**
     * GET  /issuances : get all the issuances.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of issuances in body
     */
    @GetMapping("/issuances")
    @Timed
    public ResponseEntity<List<Issuance>> getAllIssuances(Pageable pageable) {
        log.debug("REST request to get a page of Issuances");
        Page<Issuance> page = issuanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/issuances");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /issuances/:id : get the "id" issuance.
     *
     * @param id the id of the issuance to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the issuance, or with status 404 (Not Found)
     */
    @GetMapping("/issuances/{id}")
    @Timed
    public ResponseEntity<Issuance> getIssuance(@PathVariable Long id) {
        log.debug("REST request to get Issuance : {}", id);
        Issuance issuance = issuanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(issuance));
    }

    /**
     * DELETE  /issuances/:id : delete the "id" issuance.
     *
     * @param id the id of the issuance to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/issuances/{id}")
    @Timed
    public ResponseEntity<Void> deleteIssuance(@PathVariable Long id) {
        log.debug("REST request to delete Issuance : {}", id);
        issuanceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

//    @PostMapping("/issuances/{id}/minutes")
//    @Timed
//    public ResponseEntity<Issuance> uploadMinutes(@PathVariable Long id, @RequestParam("minutes") MultipartFile file)  {
//
//        log.debug("in file upload process !!!");
//        Issuance issuance = issuanceService.uploadMinutes(id, file);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, issuance.getCodeIsin()))
//            .body(issuance);
//    }

//    /**
//     * New Resource to handle file updload for the minutes of each issuance
//     *
//     */
//    @GetMapping("/issuances/fileUpload/{id}")
//    @Timed
//    public ResponseEntity<String> saveIssuanceMinutesFile(@RequestParam(value = "minutes") MultipartFile file,@PathVariable Long id, HttpServletRequest request) {
////        String rootDirectory = "<specify your path to save file>/";
//        log.debug("REST request to load a file as issuance minute : {}", file.getOriginalFilename());
//        Issuance issuance = issuanceService.findOne(id);
//
////        if(!file.isEmpty()) {issuance.setMinutesIssuance(file.getOriginalFilename());}
////        goviesIssueService.saveOrUpdate(issue);
////        if(!file.isEmpty()) {
////            issue.setMinutesIssuance(file.getOriginalFilename());
//////			file.transferTo(new File(imageDir+file.getOriginalFilename()));
////            file.transferTo(new File(imageDir+issue.getId()));
////        }
//        try {
//            file.transferTo(new File(rootDirectory  + file.getOriginalFilename()));
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}

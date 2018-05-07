package com.moh.jhispter.web.rest;

import com.moh.jhispter.JhispterApp;

import com.moh.jhispter.domain.GoviesType;
import com.moh.jhispter.repository.GoviesTypeRepository;
import com.moh.jhispter.service.GoviesTypeService;
import com.moh.jhispter.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.moh.jhispter.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.moh.jhispter.domain.enumeration.ConventionBase;
/**
 * Test class for the GoviesTypeResource REST controller.
 *
 * @see GoviesTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhispterApp.class)
public class GoviesTypeResourceIntTest {

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    private static final ConventionBase DEFAULT_CONVENTION_BASE = ConventionBase.REEL_30;
    private static final ConventionBase UPDATED_CONVENTION_BASE = ConventionBase.REEL_365;

    @Autowired
    private GoviesTypeRepository goviesTypeRepository;

    @Autowired
    private GoviesTypeService goviesTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGoviesTypeMockMvc;

    private GoviesType goviesType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GoviesTypeResource goviesTypeResource = new GoviesTypeResource(goviesTypeService);
        this.restGoviesTypeMockMvc = MockMvcBuilders.standaloneSetup(goviesTypeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GoviesType createEntity(EntityManager em) {
        GoviesType goviesType = new GoviesType()
            .fullName(DEFAULT_FULL_NAME)
            .shortName(DEFAULT_SHORT_NAME)
            .conventionBase(DEFAULT_CONVENTION_BASE);
        return goviesType;
    }

    @Before
    public void initTest() {
        goviesType = createEntity(em);
    }

    @Test
    @Transactional
    public void createGoviesType() throws Exception {
        int databaseSizeBeforeCreate = goviesTypeRepository.findAll().size();

        // Create the GoviesType
        restGoviesTypeMockMvc.perform(post("/api/govies-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(goviesType)))
            .andExpect(status().isCreated());

        // Validate the GoviesType in the database
        List<GoviesType> goviesTypeList = goviesTypeRepository.findAll();
        assertThat(goviesTypeList).hasSize(databaseSizeBeforeCreate + 1);
        GoviesType testGoviesType = goviesTypeList.get(goviesTypeList.size() - 1);
        assertThat(testGoviesType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testGoviesType.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
        assertThat(testGoviesType.getConventionBase()).isEqualTo(DEFAULT_CONVENTION_BASE);
    }

    @Test
    @Transactional
    public void createGoviesTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = goviesTypeRepository.findAll().size();

        // Create the GoviesType with an existing ID
        goviesType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGoviesTypeMockMvc.perform(post("/api/govies-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(goviesType)))
            .andExpect(status().isBadRequest());

        // Validate the GoviesType in the database
        List<GoviesType> goviesTypeList = goviesTypeRepository.findAll();
        assertThat(goviesTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkShortNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = goviesTypeRepository.findAll().size();
        // set the field null
        goviesType.setShortName(null);

        // Create the GoviesType, which fails.

        restGoviesTypeMockMvc.perform(post("/api/govies-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(goviesType)))
            .andExpect(status().isBadRequest());

        List<GoviesType> goviesTypeList = goviesTypeRepository.findAll();
        assertThat(goviesTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGoviesTypes() throws Exception {
        // Initialize the database
        goviesTypeRepository.saveAndFlush(goviesType);

        // Get all the goviesTypeList
        restGoviesTypeMockMvc.perform(get("/api/govies-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(goviesType.getId().intValue())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())))
            .andExpect(jsonPath("$.[*].conventionBase").value(hasItem(DEFAULT_CONVENTION_BASE.toString())));
    }

    @Test
    @Transactional
    public void getGoviesType() throws Exception {
        // Initialize the database
        goviesTypeRepository.saveAndFlush(goviesType);

        // Get the goviesType
        restGoviesTypeMockMvc.perform(get("/api/govies-types/{id}", goviesType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(goviesType.getId().intValue()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()))
            .andExpect(jsonPath("$.conventionBase").value(DEFAULT_CONVENTION_BASE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGoviesType() throws Exception {
        // Get the goviesType
        restGoviesTypeMockMvc.perform(get("/api/govies-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGoviesType() throws Exception {
        // Initialize the database
        goviesTypeService.save(goviesType);

        int databaseSizeBeforeUpdate = goviesTypeRepository.findAll().size();

        // Update the goviesType
        GoviesType updatedGoviesType = goviesTypeRepository.findOne(goviesType.getId());
        // Disconnect from session so that the updates on updatedGoviesType are not directly saved in db
        em.detach(updatedGoviesType);
        updatedGoviesType
            .fullName(UPDATED_FULL_NAME)
            .shortName(UPDATED_SHORT_NAME)
            .conventionBase(UPDATED_CONVENTION_BASE);

        restGoviesTypeMockMvc.perform(put("/api/govies-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGoviesType)))
            .andExpect(status().isOk());

        // Validate the GoviesType in the database
        List<GoviesType> goviesTypeList = goviesTypeRepository.findAll();
        assertThat(goviesTypeList).hasSize(databaseSizeBeforeUpdate);
        GoviesType testGoviesType = goviesTypeList.get(goviesTypeList.size() - 1);
        assertThat(testGoviesType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testGoviesType.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
        assertThat(testGoviesType.getConventionBase()).isEqualTo(UPDATED_CONVENTION_BASE);
    }

    @Test
    @Transactional
    public void updateNonExistingGoviesType() throws Exception {
        int databaseSizeBeforeUpdate = goviesTypeRepository.findAll().size();

        // Create the GoviesType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGoviesTypeMockMvc.perform(put("/api/govies-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(goviesType)))
            .andExpect(status().isCreated());

        // Validate the GoviesType in the database
        List<GoviesType> goviesTypeList = goviesTypeRepository.findAll();
        assertThat(goviesTypeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteGoviesType() throws Exception {
        // Initialize the database
        goviesTypeService.save(goviesType);

        int databaseSizeBeforeDelete = goviesTypeRepository.findAll().size();

        // Get the goviesType
        restGoviesTypeMockMvc.perform(delete("/api/govies-types/{id}", goviesType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GoviesType> goviesTypeList = goviesTypeRepository.findAll();
        assertThat(goviesTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GoviesType.class);
        GoviesType goviesType1 = new GoviesType();
        goviesType1.setId(1L);
        GoviesType goviesType2 = new GoviesType();
        goviesType2.setId(goviesType1.getId());
        assertThat(goviesType1).isEqualTo(goviesType2);
        goviesType2.setId(2L);
        assertThat(goviesType1).isNotEqualTo(goviesType2);
        goviesType1.setId(null);
        assertThat(goviesType1).isNotEqualTo(goviesType2);
    }
}

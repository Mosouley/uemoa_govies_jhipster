package com.moh.jhispter.web.rest;

import com.moh.jhispter.JhispterApp;

import com.moh.jhispter.domain.Issuer;
import com.moh.jhispter.repository.IssuerRepository;
import com.moh.jhispter.service.IssuerService;
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

/**
 * Test class for the IssuerResource REST controller.
 *
 * @see IssuerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhispterApp.class)
public class IssuerResourceIntTest {

    private static final String DEFAULT_FLAG_URL = "AAAAAAAAAA";
    private static final String UPDATED_FLAG_URL = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    @Autowired
    private IssuerRepository issuerRepository;

    @Autowired
    private IssuerService issuerService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restIssuerMockMvc;

    private Issuer issuer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IssuerResource issuerResource = new IssuerResource(issuerService);
        this.restIssuerMockMvc = MockMvcBuilders.standaloneSetup(issuerResource)
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
    public static Issuer createEntity(EntityManager em) {
        Issuer issuer = new Issuer()
            .flagUrl(DEFAULT_FLAG_URL)
            .fullName(DEFAULT_FULL_NAME)
            .shortName(DEFAULT_SHORT_NAME);
        return issuer;
    }

    @Before
    public void initTest() {
        issuer = createEntity(em);
    }

    @Test
    @Transactional
    public void createIssuer() throws Exception {
        int databaseSizeBeforeCreate = issuerRepository.findAll().size();

        // Create the Issuer
        restIssuerMockMvc.perform(post("/api/issuers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(issuer)))
            .andExpect(status().isCreated());

        // Validate the Issuer in the database
        List<Issuer> issuerList = issuerRepository.findAll();
        assertThat(issuerList).hasSize(databaseSizeBeforeCreate + 1);
        Issuer testIssuer = issuerList.get(issuerList.size() - 1);
        assertThat(testIssuer.getFlagUrl()).isEqualTo(DEFAULT_FLAG_URL);
        assertThat(testIssuer.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testIssuer.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
    }

    @Test
    @Transactional
    public void createIssuerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = issuerRepository.findAll().size();

        // Create the Issuer with an existing ID
        issuer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIssuerMockMvc.perform(post("/api/issuers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(issuer)))
            .andExpect(status().isBadRequest());

        // Validate the Issuer in the database
        List<Issuer> issuerList = issuerRepository.findAll();
        assertThat(issuerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkShortNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = issuerRepository.findAll().size();
        // set the field null
        issuer.setShortName(null);

        // Create the Issuer, which fails.

        restIssuerMockMvc.perform(post("/api/issuers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(issuer)))
            .andExpect(status().isBadRequest());

        List<Issuer> issuerList = issuerRepository.findAll();
        assertThat(issuerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIssuers() throws Exception {
        // Initialize the database
        issuerRepository.saveAndFlush(issuer);

        // Get all the issuerList
        restIssuerMockMvc.perform(get("/api/issuers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(issuer.getId().intValue())))
            .andExpect(jsonPath("$.[*].flagUrl").value(hasItem(DEFAULT_FLAG_URL.toString())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getIssuer() throws Exception {
        // Initialize the database
        issuerRepository.saveAndFlush(issuer);

        // Get the issuer
        restIssuerMockMvc.perform(get("/api/issuers/{id}", issuer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(issuer.getId().intValue()))
            .andExpect(jsonPath("$.flagUrl").value(DEFAULT_FLAG_URL.toString()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingIssuer() throws Exception {
        // Get the issuer
        restIssuerMockMvc.perform(get("/api/issuers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIssuer() throws Exception {
        // Initialize the database
        issuerService.save(issuer);

        int databaseSizeBeforeUpdate = issuerRepository.findAll().size();

        // Update the issuer
        Issuer updatedIssuer = issuerRepository.findOne(issuer.getId());
        // Disconnect from session so that the updates on updatedIssuer are not directly saved in db
        em.detach(updatedIssuer);
        updatedIssuer
            .flagUrl(UPDATED_FLAG_URL)
            .fullName(UPDATED_FULL_NAME)
            .shortName(UPDATED_SHORT_NAME);

        restIssuerMockMvc.perform(put("/api/issuers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedIssuer)))
            .andExpect(status().isOk());

        // Validate the Issuer in the database
        List<Issuer> issuerList = issuerRepository.findAll();
        assertThat(issuerList).hasSize(databaseSizeBeforeUpdate);
        Issuer testIssuer = issuerList.get(issuerList.size() - 1);
        assertThat(testIssuer.getFlagUrl()).isEqualTo(UPDATED_FLAG_URL);
        assertThat(testIssuer.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testIssuer.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingIssuer() throws Exception {
        int databaseSizeBeforeUpdate = issuerRepository.findAll().size();

        // Create the Issuer

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restIssuerMockMvc.perform(put("/api/issuers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(issuer)))
            .andExpect(status().isCreated());

        // Validate the Issuer in the database
        List<Issuer> issuerList = issuerRepository.findAll();
        assertThat(issuerList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteIssuer() throws Exception {
        // Initialize the database
        issuerService.save(issuer);

        int databaseSizeBeforeDelete = issuerRepository.findAll().size();

        // Get the issuer
        restIssuerMockMvc.perform(delete("/api/issuers/{id}", issuer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Issuer> issuerList = issuerRepository.findAll();
        assertThat(issuerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Issuer.class);
        Issuer issuer1 = new Issuer();
        issuer1.setId(1L);
        Issuer issuer2 = new Issuer();
        issuer2.setId(issuer1.getId());
        assertThat(issuer1).isEqualTo(issuer2);
        issuer2.setId(2L);
        assertThat(issuer1).isNotEqualTo(issuer2);
        issuer1.setId(null);
        assertThat(issuer1).isNotEqualTo(issuer2);
    }
}

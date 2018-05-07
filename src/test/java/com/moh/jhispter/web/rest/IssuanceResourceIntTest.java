package com.moh.jhispter.web.rest;

import com.moh.jhispter.JhispterApp;

import com.moh.jhispter.domain.Issuance;
import com.moh.jhispter.repository.IssuanceRepository;
import com.moh.jhispter.service.IssuanceService;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.moh.jhispter.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.moh.jhispter.domain.enumeration.FrequenceCalcul;
import com.moh.jhispter.domain.enumeration.FrequenceCalcul;
import com.moh.jhispter.domain.enumeration.GoviesRepayMode;
/**
 * Test class for the IssuanceResource REST controller.
 *
 * @see IssuanceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhispterApp.class)
public class IssuanceResourceIntTest {

    private static final String DEFAULT_CODE_ISIN = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ISIN = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ISSUE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ISSUE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ISSUE_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_ISSUE_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_ISSUE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ISSUE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ISSUE_TRANCHE = "AAAAAAAAAA";
    private static final String UPDATED_ISSUE_TRANCHE = "BBBBBBBBBB";

    private static final Float DEFAULT_NOMINAL_VALUE = 1F;
    private static final Float UPDATED_NOMINAL_VALUE = 2F;

    private static final BigDecimal DEFAULT_AVERAGE_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_AVERAGE_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_COUPON_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_COUPON_RATE = new BigDecimal(2);

    private static final Float DEFAULT_MARGINAL_RATE = 1F;
    private static final Float UPDATED_MARGINAL_RATE = 2F;

    private static final LocalDate DEFAULT_VALUE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VALUE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_MATURITY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MATURITY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final FrequenceCalcul DEFAULT_INTEREST_PERIOD = FrequenceCalcul.ANNUAL;
    private static final FrequenceCalcul UPDATED_INTEREST_PERIOD = FrequenceCalcul.SEMIANNUAL;

    private static final FrequenceCalcul DEFAULT_REPAY_PERIOD = FrequenceCalcul.ANNUAL;
    private static final FrequenceCalcul UPDATED_REPAY_PERIOD = FrequenceCalcul.SEMIANNUAL;

    private static final GoviesRepayMode DEFAULT_REPAY_MODE = GoviesRepayMode.AMORTCONST;
    private static final GoviesRepayMode UPDATED_REPAY_MODE = GoviesRepayMode.ANNUITCONST;

    private static final Integer DEFAULT_GRACE_PERIOD = 1;
    private static final Integer UPDATED_GRACE_PERIOD = 2;

    private static final Float DEFAULT_BID_AMOUNT = 1F;
    private static final Float UPDATED_BID_AMOUNT = 2F;

    private static final Float DEFAULT_GET_AMOUNT = 1F;
    private static final Float UPDATED_GET_AMOUNT = 2F;

    private static final Float DEFAULT_OFFERED_AMOUNT = 1F;
    private static final Float UPDATED_OFFERED_AMOUNT = 2F;

    private static final Float DEFAULT_OFFERED_ONC = 1F;
    private static final Float UPDATED_OFFERED_ONC = 2F;

    private static final String DEFAULT_MINUTES_ISSUANCE = "AAAAAAAAAA";
    private static final String UPDATED_MINUTES_ISSUANCE = "BBBBBBBBBB";

    @Autowired
    private IssuanceRepository issuanceRepository;

    @Autowired
    private IssuanceService issuanceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restIssuanceMockMvc;

    private Issuance issuance;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IssuanceResource issuanceResource = new IssuanceResource(issuanceService);
        this.restIssuanceMockMvc = MockMvcBuilders.standaloneSetup(issuanceResource)
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
    public static Issuance createEntity(EntityManager em) {
        Issuance issuance = new Issuance()
            .codeIsin(DEFAULT_CODE_ISIN)
            .issueDate(DEFAULT_ISSUE_DATE)
            .issueReference(DEFAULT_ISSUE_REFERENCE)
            .issueDescription(DEFAULT_ISSUE_DESCRIPTION)
            .issueTranche(DEFAULT_ISSUE_TRANCHE)
            .nominalValue(DEFAULT_NOMINAL_VALUE)
            .averageRate(DEFAULT_AVERAGE_RATE)
            .couponRate(DEFAULT_COUPON_RATE)
            .marginalRate(DEFAULT_MARGINAL_RATE)
            .valueDate(DEFAULT_VALUE_DATE)
            .maturityDate(DEFAULT_MATURITY_DATE)
            .interestPeriod(DEFAULT_INTEREST_PERIOD)
            .repayPeriod(DEFAULT_REPAY_PERIOD)
            .repayMode(DEFAULT_REPAY_MODE)
            .gracePeriod(DEFAULT_GRACE_PERIOD)
            .bidAmount(DEFAULT_BID_AMOUNT)
            .getAmount(DEFAULT_GET_AMOUNT)
            .offeredAmount(DEFAULT_OFFERED_AMOUNT)
            .offeredONC(DEFAULT_OFFERED_ONC)
            .minutesIssuance(DEFAULT_MINUTES_ISSUANCE);
        return issuance;
    }

    @Before
    public void initTest() {
        issuance = createEntity(em);
    }

    @Test
    @Transactional
    public void createIssuance() throws Exception {
        int databaseSizeBeforeCreate = issuanceRepository.findAll().size();

        // Create the Issuance
        restIssuanceMockMvc.perform(post("/api/issuances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(issuance)))
            .andExpect(status().isCreated());

        // Validate the Issuance in the database
        List<Issuance> issuanceList = issuanceRepository.findAll();
        assertThat(issuanceList).hasSize(databaseSizeBeforeCreate + 1);
        Issuance testIssuance = issuanceList.get(issuanceList.size() - 1);
        assertThat(testIssuance.getCodeIsin()).isEqualTo(DEFAULT_CODE_ISIN);
        assertThat(testIssuance.getIssueDate()).isEqualTo(DEFAULT_ISSUE_DATE);
        assertThat(testIssuance.getIssueReference()).isEqualTo(DEFAULT_ISSUE_REFERENCE);
        assertThat(testIssuance.getIssueDescription()).isEqualTo(DEFAULT_ISSUE_DESCRIPTION);
        assertThat(testIssuance.getIssueTranche()).isEqualTo(DEFAULT_ISSUE_TRANCHE);
        assertThat(testIssuance.getNominalValue()).isEqualTo(DEFAULT_NOMINAL_VALUE);
        assertThat(testIssuance.getAverageRate()).isEqualTo(DEFAULT_AVERAGE_RATE);
        assertThat(testIssuance.getCouponRate()).isEqualTo(DEFAULT_COUPON_RATE);
        assertThat(testIssuance.getMarginalRate()).isEqualTo(DEFAULT_MARGINAL_RATE);
        assertThat(testIssuance.getValueDate()).isEqualTo(DEFAULT_VALUE_DATE);
        assertThat(testIssuance.getMaturityDate()).isEqualTo(DEFAULT_MATURITY_DATE);
        assertThat(testIssuance.getInterestPeriod()).isEqualTo(DEFAULT_INTEREST_PERIOD);
        assertThat(testIssuance.getRepayPeriod()).isEqualTo(DEFAULT_REPAY_PERIOD);
        assertThat(testIssuance.getRepayMode()).isEqualTo(DEFAULT_REPAY_MODE);
        assertThat(testIssuance.getGracePeriod()).isEqualTo(DEFAULT_GRACE_PERIOD);
        assertThat(testIssuance.getBidAmount()).isEqualTo(DEFAULT_BID_AMOUNT);
        assertThat(testIssuance.getGetAmount()).isEqualTo(DEFAULT_GET_AMOUNT);
        assertThat(testIssuance.getOfferedAmount()).isEqualTo(DEFAULT_OFFERED_AMOUNT);
        assertThat(testIssuance.getOfferedONC()).isEqualTo(DEFAULT_OFFERED_ONC);
        assertThat(testIssuance.getMinutesIssuance()).isEqualTo(DEFAULT_MINUTES_ISSUANCE);
    }

    @Test
    @Transactional
    public void createIssuanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = issuanceRepository.findAll().size();

        // Create the Issuance with an existing ID
        issuance.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIssuanceMockMvc.perform(post("/api/issuances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(issuance)))
            .andExpect(status().isBadRequest());

        // Validate the Issuance in the database
        List<Issuance> issuanceList = issuanceRepository.findAll();
        assertThat(issuanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsinIsRequired() throws Exception {
        int databaseSizeBeforeTest = issuanceRepository.findAll().size();
        // set the field null
        issuance.setCodeIsin(null);

        // Create the Issuance, which fails.

        restIssuanceMockMvc.perform(post("/api/issuances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(issuance)))
            .andExpect(status().isBadRequest());

        List<Issuance> issuanceList = issuanceRepository.findAll();
        assertThat(issuanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIssueDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = issuanceRepository.findAll().size();
        // set the field null
        issuance.setIssueDate(null);

        // Create the Issuance, which fails.

        restIssuanceMockMvc.perform(post("/api/issuances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(issuance)))
            .andExpect(status().isBadRequest());

        List<Issuance> issuanceList = issuanceRepository.findAll();
        assertThat(issuanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValueDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = issuanceRepository.findAll().size();
        // set the field null
        issuance.setValueDate(null);

        // Create the Issuance, which fails.

        restIssuanceMockMvc.perform(post("/api/issuances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(issuance)))
            .andExpect(status().isBadRequest());

        List<Issuance> issuanceList = issuanceRepository.findAll();
        assertThat(issuanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaturityDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = issuanceRepository.findAll().size();
        // set the field null
        issuance.setMaturityDate(null);

        // Create the Issuance, which fails.

        restIssuanceMockMvc.perform(post("/api/issuances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(issuance)))
            .andExpect(status().isBadRequest());

        List<Issuance> issuanceList = issuanceRepository.findAll();
        assertThat(issuanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIssuances() throws Exception {
        // Initialize the database
        issuanceRepository.saveAndFlush(issuance);

        // Get all the issuanceList
        restIssuanceMockMvc.perform(get("/api/issuances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(issuance.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeIsin").value(hasItem(DEFAULT_CODE_ISIN.toString())))
            .andExpect(jsonPath("$.[*].issueDate").value(hasItem(DEFAULT_ISSUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].issueReference").value(hasItem(DEFAULT_ISSUE_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].issueDescription").value(hasItem(DEFAULT_ISSUE_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].issueTranche").value(hasItem(DEFAULT_ISSUE_TRANCHE.toString())))
            .andExpect(jsonPath("$.[*].nominalValue").value(hasItem(DEFAULT_NOMINAL_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].averageRate").value(hasItem(DEFAULT_AVERAGE_RATE.intValue())))
            .andExpect(jsonPath("$.[*].couponRate").value(hasItem(DEFAULT_COUPON_RATE.intValue())))
            .andExpect(jsonPath("$.[*].marginalRate").value(hasItem(DEFAULT_MARGINAL_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].valueDate").value(hasItem(DEFAULT_VALUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].maturityDate").value(hasItem(DEFAULT_MATURITY_DATE.toString())))
            .andExpect(jsonPath("$.[*].interestPeriod").value(hasItem(DEFAULT_INTEREST_PERIOD.toString())))
            .andExpect(jsonPath("$.[*].repayPeriod").value(hasItem(DEFAULT_REPAY_PERIOD.toString())))
            .andExpect(jsonPath("$.[*].repayMode").value(hasItem(DEFAULT_REPAY_MODE.toString())))
            .andExpect(jsonPath("$.[*].gracePeriod").value(hasItem(DEFAULT_GRACE_PERIOD)))
            .andExpect(jsonPath("$.[*].bidAmount").value(hasItem(DEFAULT_BID_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].getAmount").value(hasItem(DEFAULT_GET_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].offeredAmount").value(hasItem(DEFAULT_OFFERED_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].offeredONC").value(hasItem(DEFAULT_OFFERED_ONC.doubleValue())))
            .andExpect(jsonPath("$.[*].minutesIssuance").value(hasItem(DEFAULT_MINUTES_ISSUANCE.toString())));
    }

    @Test
    @Transactional
    public void getIssuance() throws Exception {
        // Initialize the database
        issuanceRepository.saveAndFlush(issuance);

        // Get the issuance
        restIssuanceMockMvc.perform(get("/api/issuances/{id}", issuance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(issuance.getId().intValue()))
            .andExpect(jsonPath("$.codeIsin").value(DEFAULT_CODE_ISIN.toString()))
            .andExpect(jsonPath("$.issueDate").value(DEFAULT_ISSUE_DATE.toString()))
            .andExpect(jsonPath("$.issueReference").value(DEFAULT_ISSUE_REFERENCE.toString()))
            .andExpect(jsonPath("$.issueDescription").value(DEFAULT_ISSUE_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.issueTranche").value(DEFAULT_ISSUE_TRANCHE.toString()))
            .andExpect(jsonPath("$.nominalValue").value(DEFAULT_NOMINAL_VALUE.doubleValue()))
            .andExpect(jsonPath("$.averageRate").value(DEFAULT_AVERAGE_RATE.intValue()))
            .andExpect(jsonPath("$.couponRate").value(DEFAULT_COUPON_RATE.intValue()))
            .andExpect(jsonPath("$.marginalRate").value(DEFAULT_MARGINAL_RATE.doubleValue()))
            .andExpect(jsonPath("$.valueDate").value(DEFAULT_VALUE_DATE.toString()))
            .andExpect(jsonPath("$.maturityDate").value(DEFAULT_MATURITY_DATE.toString()))
            .andExpect(jsonPath("$.interestPeriod").value(DEFAULT_INTEREST_PERIOD.toString()))
            .andExpect(jsonPath("$.repayPeriod").value(DEFAULT_REPAY_PERIOD.toString()))
            .andExpect(jsonPath("$.repayMode").value(DEFAULT_REPAY_MODE.toString()))
            .andExpect(jsonPath("$.gracePeriod").value(DEFAULT_GRACE_PERIOD))
            .andExpect(jsonPath("$.bidAmount").value(DEFAULT_BID_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.getAmount").value(DEFAULT_GET_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.offeredAmount").value(DEFAULT_OFFERED_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.offeredONC").value(DEFAULT_OFFERED_ONC.doubleValue()))
            .andExpect(jsonPath("$.minutesIssuance").value(DEFAULT_MINUTES_ISSUANCE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingIssuance() throws Exception {
        // Get the issuance
        restIssuanceMockMvc.perform(get("/api/issuances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIssuance() throws Exception {
        // Initialize the database
        issuanceService.save(issuance);

        int databaseSizeBeforeUpdate = issuanceRepository.findAll().size();

        // Update the issuance
        Issuance updatedIssuance = issuanceRepository.findOne(issuance.getId());
        // Disconnect from session so that the updates on updatedIssuance are not directly saved in db
        em.detach(updatedIssuance);
        updatedIssuance
            .codeIsin(UPDATED_CODE_ISIN)
            .issueDate(UPDATED_ISSUE_DATE)
            .issueReference(UPDATED_ISSUE_REFERENCE)
            .issueDescription(UPDATED_ISSUE_DESCRIPTION)
            .issueTranche(UPDATED_ISSUE_TRANCHE)
            .nominalValue(UPDATED_NOMINAL_VALUE)
            .averageRate(UPDATED_AVERAGE_RATE)
            .couponRate(UPDATED_COUPON_RATE)
            .marginalRate(UPDATED_MARGINAL_RATE)
            .valueDate(UPDATED_VALUE_DATE)
            .maturityDate(UPDATED_MATURITY_DATE)
            .interestPeriod(UPDATED_INTEREST_PERIOD)
            .repayPeriod(UPDATED_REPAY_PERIOD)
            .repayMode(UPDATED_REPAY_MODE)
            .gracePeriod(UPDATED_GRACE_PERIOD)
            .bidAmount(UPDATED_BID_AMOUNT)
            .getAmount(UPDATED_GET_AMOUNT)
            .offeredAmount(UPDATED_OFFERED_AMOUNT)
            .offeredONC(UPDATED_OFFERED_ONC)
            .minutesIssuance(UPDATED_MINUTES_ISSUANCE);

        restIssuanceMockMvc.perform(put("/api/issuances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedIssuance)))
            .andExpect(status().isOk());

        // Validate the Issuance in the database
        List<Issuance> issuanceList = issuanceRepository.findAll();
        assertThat(issuanceList).hasSize(databaseSizeBeforeUpdate);
        Issuance testIssuance = issuanceList.get(issuanceList.size() - 1);
        assertThat(testIssuance.getCodeIsin()).isEqualTo(UPDATED_CODE_ISIN);
        assertThat(testIssuance.getIssueDate()).isEqualTo(UPDATED_ISSUE_DATE);
        assertThat(testIssuance.getIssueReference()).isEqualTo(UPDATED_ISSUE_REFERENCE);
        assertThat(testIssuance.getIssueDescription()).isEqualTo(UPDATED_ISSUE_DESCRIPTION);
        assertThat(testIssuance.getIssueTranche()).isEqualTo(UPDATED_ISSUE_TRANCHE);
        assertThat(testIssuance.getNominalValue()).isEqualTo(UPDATED_NOMINAL_VALUE);
        assertThat(testIssuance.getAverageRate()).isEqualTo(UPDATED_AVERAGE_RATE);
        assertThat(testIssuance.getCouponRate()).isEqualTo(UPDATED_COUPON_RATE);
        assertThat(testIssuance.getMarginalRate()).isEqualTo(UPDATED_MARGINAL_RATE);
        assertThat(testIssuance.getValueDate()).isEqualTo(UPDATED_VALUE_DATE);
        assertThat(testIssuance.getMaturityDate()).isEqualTo(UPDATED_MATURITY_DATE);
        assertThat(testIssuance.getInterestPeriod()).isEqualTo(UPDATED_INTEREST_PERIOD);
        assertThat(testIssuance.getRepayPeriod()).isEqualTo(UPDATED_REPAY_PERIOD);
        assertThat(testIssuance.getRepayMode()).isEqualTo(UPDATED_REPAY_MODE);
        assertThat(testIssuance.getGracePeriod()).isEqualTo(UPDATED_GRACE_PERIOD);
        assertThat(testIssuance.getBidAmount()).isEqualTo(UPDATED_BID_AMOUNT);
        assertThat(testIssuance.getGetAmount()).isEqualTo(UPDATED_GET_AMOUNT);
        assertThat(testIssuance.getOfferedAmount()).isEqualTo(UPDATED_OFFERED_AMOUNT);
        assertThat(testIssuance.getOfferedONC()).isEqualTo(UPDATED_OFFERED_ONC);
        assertThat(testIssuance.getMinutesIssuance()).isEqualTo(UPDATED_MINUTES_ISSUANCE);
    }

    @Test
    @Transactional
    public void updateNonExistingIssuance() throws Exception {
        int databaseSizeBeforeUpdate = issuanceRepository.findAll().size();

        // Create the Issuance

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restIssuanceMockMvc.perform(put("/api/issuances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(issuance)))
            .andExpect(status().isCreated());

        // Validate the Issuance in the database
        List<Issuance> issuanceList = issuanceRepository.findAll();
        assertThat(issuanceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteIssuance() throws Exception {
        // Initialize the database
        issuanceService.save(issuance);

        int databaseSizeBeforeDelete = issuanceRepository.findAll().size();

        // Get the issuance
        restIssuanceMockMvc.perform(delete("/api/issuances/{id}", issuance.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Issuance> issuanceList = issuanceRepository.findAll();
        assertThat(issuanceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Issuance.class);
        Issuance issuance1 = new Issuance();
        issuance1.setId(1L);
        Issuance issuance2 = new Issuance();
        issuance2.setId(issuance1.getId());
        assertThat(issuance1).isEqualTo(issuance2);
        issuance2.setId(2L);
        assertThat(issuance1).isNotEqualTo(issuance2);
        issuance1.setId(null);
        assertThat(issuance1).isNotEqualTo(issuance2);
    }
}

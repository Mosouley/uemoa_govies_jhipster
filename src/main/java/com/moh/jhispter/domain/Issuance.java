package com.moh.jhispter.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import com.moh.jhispter.domain.enumeration.FrequenceCalcul;

import com.moh.jhispter.domain.enumeration.GoviesRepayMode;

/**
 * A Issuance.
 */
@Entity
@Table(name = "issuance")
public class Issuance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code_isin", nullable = false)
    private String codeIsin;

    @NotNull
    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Column(name = "issue_reference")
    private String issueReference;

    @Column(name = "issue_description")
    private String issueDescription;

    @Column(name = "issue_tranche")
    private String issueTranche;

    @Column(name = "nominal_value")
    private Float nominalValue;

    @Column(name = "average_rate", precision=10, scale=2)
    private BigDecimal averageRate;

    @Column(name = "coupon_rate", precision=10, scale=2)
    private BigDecimal couponRate;

    @Column(name = "marginal_rate")
    private Float marginalRate;

    @NotNull
    @Column(name = "value_date", nullable = false)
    private LocalDate valueDate;

    @NotNull
    @Column(name = "maturity_date", nullable = false)
    private LocalDate maturityDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "interest_period")
    private FrequenceCalcul interestPeriod;

    @Enumerated(EnumType.STRING)
    @Column(name = "repay_period")
    private FrequenceCalcul repayPeriod;

    @Enumerated(EnumType.STRING)
    @Column(name = "repay_mode")
    private GoviesRepayMode repayMode;

    @Column(name = "grace_period")
    private Integer gracePeriod;

    @Column(name = "bid_amount")
    private Float bidAmount;

    @Column(name = "get_amount")
    private Float getAmount;

    @Column(name = "offered_amount")
    private Float offeredAmount;

    @Column(name = "offered_onc")
    private Float offeredONC;

    @Column(name = "minutes_issuance")
    private String minutesIssuance;

    @ManyToOne
    private Issuer issuer;

    @ManyToOne
    private GoviesType type;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeIsin() {
        return codeIsin;
    }

    public Issuance codeIsin(String codeIsin) {
        this.codeIsin = codeIsin;
        return this;
    }

    public void setCodeIsin(String codeIsin) {
        this.codeIsin = codeIsin;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public Issuance issueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueReference() {
        return issueReference;
    }

    public Issuance issueReference(String issueReference) {
        this.issueReference = issueReference;
        return this;
    }

    public void setIssueReference(String issueReference) {
        this.issueReference = issueReference;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public Issuance issueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
        return this;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public String getIssueTranche() {
        return issueTranche;
    }

    public Issuance issueTranche(String issueTranche) {
        this.issueTranche = issueTranche;
        return this;
    }

    public void setIssueTranche(String issueTranche) {
        this.issueTranche = issueTranche;
    }

    public Float getNominalValue() {
        return nominalValue;
    }

    public Issuance nominalValue(Float nominalValue) {
        this.nominalValue = nominalValue;
        return this;
    }

    public void setNominalValue(Float nominalValue) {
        this.nominalValue = nominalValue;
    }

    public BigDecimal getAverageRate() {
        return averageRate;
    }

    public Issuance averageRate(BigDecimal averageRate) {
        this.averageRate = averageRate;
        return this;
    }

    public void setAverageRate(BigDecimal averageRate) {
        this.averageRate = averageRate;
    }

    public BigDecimal getCouponRate() {
        return couponRate;
    }

    public Issuance couponRate(BigDecimal couponRate) {
        this.couponRate = couponRate;
        return this;
    }

    public void setCouponRate(BigDecimal couponRate) {
        this.couponRate = couponRate;
    }

    public Float getMarginalRate() {
        return marginalRate;
    }

    public Issuance marginalRate(Float marginalRate) {
        this.marginalRate = marginalRate;
        return this;
    }

    public void setMarginalRate(Float marginalRate) {
        this.marginalRate = marginalRate;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public Issuance valueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
        return this;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public Issuance maturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
        return this;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public FrequenceCalcul getInterestPeriod() {
        return interestPeriod;
    }

    public Issuance interestPeriod(FrequenceCalcul interestPeriod) {
        this.interestPeriod = interestPeriod;
        return this;
    }

    public void setInterestPeriod(FrequenceCalcul interestPeriod) {
        this.interestPeriod = interestPeriod;
    }

    public FrequenceCalcul getRepayPeriod() {
        return repayPeriod;
    }

    public Issuance repayPeriod(FrequenceCalcul repayPeriod) {
        this.repayPeriod = repayPeriod;
        return this;
    }

    public void setRepayPeriod(FrequenceCalcul repayPeriod) {
        this.repayPeriod = repayPeriod;
    }

    public GoviesRepayMode getRepayMode() {
        return repayMode;
    }

    public Issuance repayMode(GoviesRepayMode repayMode) {
        this.repayMode = repayMode;
        return this;
    }

    public void setRepayMode(GoviesRepayMode repayMode) {
        this.repayMode = repayMode;
    }

    public Integer getGracePeriod() {
        return gracePeriod;
    }

    public Issuance gracePeriod(Integer gracePeriod) {
        this.gracePeriod = gracePeriod;
        return this;
    }

    public void setGracePeriod(Integer gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public Float getBidAmount() {
        return bidAmount;
    }

    public Issuance bidAmount(Float bidAmount) {
        this.bidAmount = bidAmount;
        return this;
    }

    public void setBidAmount(Float bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Float getGetAmount() {
        return getAmount;
    }

    public Issuance getAmount(Float getAmount) {
        this.getAmount = getAmount;
        return this;
    }

    public void setGetAmount(Float getAmount) {
        this.getAmount = getAmount;
    }

    public Float getOfferedAmount() {
        return offeredAmount;
    }

    public Issuance offeredAmount(Float offeredAmount) {
        this.offeredAmount = offeredAmount;
        return this;
    }

    public void setOfferedAmount(Float offeredAmount) {
        this.offeredAmount = offeredAmount;
    }

    public Float getOfferedONC() {
        return offeredONC;
    }

    public Issuance offeredONC(Float offeredONC) {
        this.offeredONC = offeredONC;
        return this;
    }

    public void setOfferedONC(Float offeredONC) {
        this.offeredONC = offeredONC;
    }

    public String getMinutesIssuance() {
        return minutesIssuance;
    }

    public Issuance minutesIssuance(String minutesIssuance) {
        this.minutesIssuance = minutesIssuance;
        return this;
    }

    public void setMinutesIssuance(String minutesIssuance) {
        this.minutesIssuance = minutesIssuance;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public Issuance issuer(Issuer issuer) {
        this.issuer = issuer;
        return this;
    }

    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
    }

    public GoviesType getType() {
        return type;
    }

    public Issuance type(GoviesType goviesType) {
        this.type = goviesType;
        return this;
    }

    public void setType(GoviesType goviesType) {
        this.type = goviesType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Issuance issuance = (Issuance) o;
        if (issuance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), issuance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Issuance{" +
            "id=" + getId() +
            ", codeIsin='" + getCodeIsin() + "'" +
            ", issueDate='" + getIssueDate() + "'" +
            ", issueReference='" + getIssueReference() + "'" +
            ", issueDescription='" + getIssueDescription() + "'" +
            ", issueTranche='" + getIssueTranche() + "'" +
            ", nominalValue=" + getNominalValue() +
            ", averageRate=" + getAverageRate() +
            ", couponRate=" + getCouponRate() +
            ", marginalRate=" + getMarginalRate() +
            ", valueDate='" + getValueDate() + "'" +
            ", maturityDate='" + getMaturityDate() + "'" +
            ", interestPeriod='" + getInterestPeriod() + "'" +
            ", repayPeriod='" + getRepayPeriod() + "'" +
            ", repayMode='" + getRepayMode() + "'" +
            ", gracePeriod=" + getGracePeriod() +
            ", bidAmount=" + getBidAmount() +
            ", getAmount=" + getGetAmount() +
            ", offeredAmount=" + getOfferedAmount() +
            ", offeredONC=" + getOfferedONC() +
            ", minutesIssuance='" + getMinutesIssuance() + "'" +
            "}";
    }
}

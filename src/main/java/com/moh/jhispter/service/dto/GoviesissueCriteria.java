package com.moh.jhispter.service.dto;

import java.io.Serializable;
import com.moh.jhispter.domain.enumeration.FrequenceCalcul;
import com.moh.jhispter.domain.enumeration.FrequenceCalcul;
import com.moh.jhispter.domain.enumeration.GoviesRepayMode;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;


import io.github.jhipster.service.filter.LocalDateFilter;



/**
 * Criteria class for the Goviesissue entity. This class is used in GoviesissueResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /goviesissues?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class GoviesissueCriteria implements Serializable {
    /**
     * Class for filtering FrequenceCalcul
     */
    public static class FrequenceCalculFilter extends Filter<FrequenceCalcul> {
    }

    /**
     * Class for filtering GoviesRepayMode
     */
    public static class GoviesRepayModeFilter extends Filter<GoviesRepayMode> {
    }

    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter minutesIssuance;

    private FloatFilter nominalValue;

    private FloatFilter averageRate;

    private FloatFilter couponRate;

    private FloatFilter bidAmount;

    private FloatFilter getAmount;

    private FloatFilter offeredAmount;

    private FloatFilter offeredONC;

    private StringFilter codeIsin;

    private IntegerFilter gracePeriod;

    private LocalDateFilter issueDate;

    private StringFilter issueReference;

    private StringFilter issueDescription;

    private StringFilter issueTranche;

    private FloatFilter marginalRate;

    private LocalDateFilter maturityDate;

    private LocalDateFilter valueDate;

    private StringFilter goviesType;

    private StringFilter issuer;

    private FrequenceCalculFilter interestPeriod;

    private FrequenceCalculFilter repayPeriod;

    private GoviesRepayModeFilter repayMode;

    public GoviesissueCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMinutesIssuance() {
        return minutesIssuance;
    }

    public void setMinutesIssuance(StringFilter minutesIssuance) {
        this.minutesIssuance = minutesIssuance;
    }

    public FloatFilter getNominalValue() {
        return nominalValue;
    }

    public void setNominalValue(FloatFilter nominalValue) {
        this.nominalValue = nominalValue;
    }

    public FloatFilter getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(FloatFilter averageRate) {
        this.averageRate = averageRate;
    }

    public FloatFilter getCouponRate() {
        return couponRate;
    }

    public void setCouponRate(FloatFilter couponRate) {
        this.couponRate = couponRate;
    }

    public FloatFilter getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(FloatFilter bidAmount) {
        this.bidAmount = bidAmount;
    }

    public FloatFilter getGetAmount() {
        return getAmount;
    }

    public void setGetAmount(FloatFilter getAmount) {
        this.getAmount = getAmount;
    }

    public FloatFilter getOfferedAmount() {
        return offeredAmount;
    }

    public void setOfferedAmount(FloatFilter offeredAmount) {
        this.offeredAmount = offeredAmount;
    }

    public FloatFilter getOfferedONC() {
        return offeredONC;
    }

    public void setOfferedONC(FloatFilter offeredONC) {
        this.offeredONC = offeredONC;
    }

    public StringFilter getCodeIsin() {
        return codeIsin;
    }

    public void setCodeIsin(StringFilter codeIsin) {
        this.codeIsin = codeIsin;
    }

    public IntegerFilter getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(IntegerFilter gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public LocalDateFilter getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateFilter issueDate) {
        this.issueDate = issueDate;
    }

    public StringFilter getIssueReference() {
        return issueReference;
    }

    public void setIssueReference(StringFilter issueReference) {
        this.issueReference = issueReference;
    }

    public StringFilter getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(StringFilter issueDescription) {
        this.issueDescription = issueDescription;
    }

    public StringFilter getIssueTranche() {
        return issueTranche;
    }

    public void setIssueTranche(StringFilter issueTranche) {
        this.issueTranche = issueTranche;
    }

    public FloatFilter getMarginalRate() {
        return marginalRate;
    }

    public void setMarginalRate(FloatFilter marginalRate) {
        this.marginalRate = marginalRate;
    }

    public LocalDateFilter getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDateFilter maturityDate) {
        this.maturityDate = maturityDate;
    }

    public LocalDateFilter getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDateFilter valueDate) {
        this.valueDate = valueDate;
    }

    public StringFilter getGoviesType() {
        return goviesType;
    }

    public void setGoviesType(StringFilter goviesType) {
        this.goviesType = goviesType;
    }

    public StringFilter getIssuer() {
        return issuer;
    }

    public void setIssuer(StringFilter issuer) {
        this.issuer = issuer;
    }

    public FrequenceCalculFilter getInterestPeriod() {
        return interestPeriod;
    }

    public void setInterestPeriod(FrequenceCalculFilter interestPeriod) {
        this.interestPeriod = interestPeriod;
    }

    public FrequenceCalculFilter getRepayPeriod() {
        return repayPeriod;
    }

    public void setRepayPeriod(FrequenceCalculFilter repayPeriod) {
        this.repayPeriod = repayPeriod;
    }

    public GoviesRepayModeFilter getRepayMode() {
        return repayMode;
    }

    public void setRepayMode(GoviesRepayModeFilter repayMode) {
        this.repayMode = repayMode;
    }

    @Override
    public String toString() {
        return "GoviesissueCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (minutesIssuance != null ? "minutesIssuance=" + minutesIssuance + ", " : "") +
                (nominalValue != null ? "nominalValue=" + nominalValue + ", " : "") +
                (averageRate != null ? "averageRate=" + averageRate + ", " : "") +
                (couponRate != null ? "couponRate=" + couponRate + ", " : "") +
                (bidAmount != null ? "bidAmount=" + bidAmount + ", " : "") +
                (getAmount != null ? "getAmount=" + getAmount + ", " : "") +
                (offeredAmount != null ? "offeredAmount=" + offeredAmount + ", " : "") +
                (offeredONC != null ? "offeredONC=" + offeredONC + ", " : "") +
                (codeIsin != null ? "codeIsin=" + codeIsin + ", " : "") +
                (gracePeriod != null ? "gracePeriod=" + gracePeriod + ", " : "") +
                (issueDate != null ? "issueDate=" + issueDate + ", " : "") +
                (issueReference != null ? "issueReference=" + issueReference + ", " : "") +
                (issueDescription != null ? "issueDescription=" + issueDescription + ", " : "") +
                (issueTranche != null ? "issueTranche=" + issueTranche + ", " : "") +
                (marginalRate != null ? "marginalRate=" + marginalRate + ", " : "") +
                (maturityDate != null ? "maturityDate=" + maturityDate + ", " : "") +
                (valueDate != null ? "valueDate=" + valueDate + ", " : "") +
                (goviesType != null ? "goviesType=" + goviesType + ", " : "") +
                (issuer != null ? "issuer=" + issuer + ", " : "") +
                (interestPeriod != null ? "interestPeriod=" + interestPeriod + ", " : "") +
                (repayPeriod != null ? "repayPeriod=" + repayPeriod + ", " : "") +
                (repayMode != null ? "repayMode=" + repayMode + ", " : "") +
            "}";
    }

}

package com.moh.jhispter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Issuer.
 */
@Entity
@Table(name = "issuer")
public class Issuer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flag_url")
    private String flagUrl;

    @Column(name = "full_name")
    private String fullName;

    @NotNull
    @Column(name = "short_name", nullable = false)
    private String shortName;

    @OneToMany(mappedBy = "issuer")
    @JsonIgnore
    private Set<Issuance> issues = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public Issuer flagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
        return this;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public Issuer fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public Issuer shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Set<Issuance> getIssues() {
        return issues;
    }

    public Issuer issues(Set<Issuance> issuances) {
        this.issues = issuances;
        return this;
    }

    public Issuer addIssue(Issuance issuance) {
        this.issues.add(issuance);
        issuance.setIssuer(this);
        return this;
    }

    public Issuer removeIssue(Issuance issuance) {
        this.issues.remove(issuance);
        issuance.setIssuer(null);
        return this;
    }

    public void setIssues(Set<Issuance> issuances) {
        this.issues = issuances;
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
        Issuer issuer = (Issuer) o;
        if (issuer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), issuer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Issuer{" +
            "id=" + getId() +
            ", flagUrl='" + getFlagUrl() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", shortName='" + getShortName() + "'" +
            "}";
    }
}

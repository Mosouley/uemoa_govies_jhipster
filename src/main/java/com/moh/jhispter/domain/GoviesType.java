package com.moh.jhispter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.moh.jhispter.domain.enumeration.ConventionBase;
import org.springframework.beans.factory.annotation.Required;

/**
 * A GoviesType.
 */
@Entity
@Table(name = "govies_type")
public class GoviesType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @NotNull
    @Column(name = "short_name", nullable = false)
    private String shortName;

    @Enumerated(EnumType.STRING)
    @Column(name = "convention_base")
    private ConventionBase conventionBase;

    @OneToMany(mappedBy = "type")
    @JsonIgnore
    private Set<Issuance> issues = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public GoviesType fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public GoviesType shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public ConventionBase getConventionBase() {
        return conventionBase;
    }

    public GoviesType conventionBase(ConventionBase conventionBase) {
        this.conventionBase = conventionBase;
        return this;
    }

    public void setConventionBase(ConventionBase conventionBase) {
        this.conventionBase = conventionBase;
    }

    public Set<Issuance> getIssues() {
        return issues;
    }

    public GoviesType issues(Set<Issuance> issuances) {
        this.issues = issuances;
        return this;
    }

    public GoviesType addIssue(Issuance issuance) {
        this.issues.add(issuance);
        issuance.setType(this);
        return this;
    }

    public GoviesType removeIssue(Issuance issuance) {
        this.issues.remove(issuance);
        issuance.setType(null);
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
        GoviesType goviesType = (GoviesType) o;
        if (goviesType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), goviesType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GoviesType{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", conventionBase='" + getConventionBase() + "'" +
            "}";
    }
}

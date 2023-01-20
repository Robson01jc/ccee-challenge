package com.ccee.back.entity;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String acronym;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @ElementCollection
    @CollectionTable(name = "generation_value", joinColumns = @JoinColumn(name = "region_id"))
    @Column(name = "value")
    private List<BigDecimal> generationValueList;

    @ElementCollection
    @CollectionTable(name = "purchase_price", joinColumns = @JoinColumn(name = "region_id"))
    @Column(name = "value")
    private List<BigDecimal> purchasePriceList;

    public static Region fromDomain(com.ccee.back.domain.Region domain) {
        var region = new Region();
        region.acronym = domain.getAcronym();
        region.generationValueList = domain.getGenerationList();
        region.purchasePriceList = domain.getPurchaseList();

        return region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public List<BigDecimal> getGenerationValueList() {
        return generationValueList;
    }

    public void setGenerationValueList(List<BigDecimal> generationValueList) {
        this.generationValueList = generationValueList;
    }

    public List<BigDecimal> getPurchasePriceList() {
        return purchasePriceList;
    }

    public void setPurchasePriceList(List<BigDecimal> purchasePriceList) {
        this.purchasePriceList = purchasePriceList;
    }
}

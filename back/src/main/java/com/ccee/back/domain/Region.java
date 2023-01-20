package com.ccee.back.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;
import java.util.List;

public class Region {

    @JacksonXmlProperty(localName = "sigla", isAttribute = true)
    private String acronym;

    @JacksonXmlElementWrapper(localName = "geracao")
    private List<BigDecimal> generationList;

    @JacksonXmlElementWrapper(localName = "compra")
    private List<BigDecimal> purchaseList;

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public List<BigDecimal> getGenerationList() {
        return generationList;
    }

    public void setGenerationList(List<BigDecimal> generationList) {
        this.generationList = generationList;
    }

    public List<BigDecimal> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<BigDecimal> purchaseList) {
        this.purchaseList = purchaseList;
    }
}

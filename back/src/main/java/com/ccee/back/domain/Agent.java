package com.ccee.back.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Date;
import java.util.List;

@JacksonXmlRootElement(localName = "agentes")
public class Agent {

    @JacksonXmlProperty(localName = "codigo")
    private Integer code;

    @JacksonXmlProperty(localName = "data")
    private Date date;

    @JacksonXmlProperty(localName = "regiao")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Region> region;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Region> getRegion() {
        return region;
    }

    public void setRegion(List<Region> region) {
        this.region = region;
    }
}

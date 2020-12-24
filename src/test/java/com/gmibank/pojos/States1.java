package com.gmibank.pojos;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

public class States1 {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("tpcountry")
    private Object tpcountry;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("tpcountry")
    public Object getTpcountry() {
        return tpcountry;
    }
    @JsonProperty("tpcountry")
    public void setTpcountry(Object tpcountry) {
        this.tpcountry = tpcountry;
    }
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    public States1() {
    }
    public States1(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public States1(String name, Object tpcountry) {
        this.name = name;
        this.tpcountry = tpcountry;
    }
    public States1(Integer id, String name, Object tpcountry) {
        this.id = id;
        this.name = name;
        this.tpcountry = tpcountry;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("tpcountry", tpcountry).append("additionalProperties", additionalProperties).toString();
    }
}


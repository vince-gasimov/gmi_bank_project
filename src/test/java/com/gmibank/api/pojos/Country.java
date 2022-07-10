package com.gmibank.api.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) //bilinmeyen bir data geldiginde ignore et (Interview)
public class Country {

    private int id;
    private String name;
    private String states;

    public Country(String name) {
        //this.id = id;
        this.name = name;
        //this.states = states;
    }

    public Country() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }
}

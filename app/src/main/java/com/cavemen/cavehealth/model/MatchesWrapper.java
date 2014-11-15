package com.cavemen.cavehealth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchesWrapper {

    private List<Match> items;

    public List<Match> getItems() {
        return items;
    }

    public void setItems(List<Match> items) {
        this.items = items;
    }
}

package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameDetailAttributes {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

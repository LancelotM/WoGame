package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-17.
 */

public class CategoryItemVo {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("icon_url")
    private String icon_url;

    @JsonProperty("items")
    private List<CategorySubtitleVo> items;

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<CategorySubtitleVo> getItems() {
        return items;
    }

    public void setItems(List<CategorySubtitleVo> items) {
        this.items = items;
    }
}

package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-11.
 */
public class CategoryListVo extends BaseWogameVo {

    @JsonProperty("data")
    private List<CategoryItemVo> categoryData;


    public List<CategoryItemVo> getCategoryData() {
        return categoryData;
    }

    public void setCategoryData(List<CategoryItemVo> categoryData) {
        this.categoryData = categoryData;
    }

}

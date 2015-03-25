package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-11.
 */
public class ShowCategoryVo extends BaseWogameVo {

    @JsonProperty("data")
    private CategoryGameVo categoryGameData;

    public CategoryGameVo getCategoryGameData() {
        return categoryGameData;
    }

    public void setCategoryGameData(CategoryGameVo categoryGameData) {
        this.categoryGameData = categoryGameData;
    }
}


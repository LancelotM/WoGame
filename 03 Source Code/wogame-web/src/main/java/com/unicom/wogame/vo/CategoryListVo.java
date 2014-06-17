package com.unicom.wogame.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-11.
 */
public class CategoryListVo extends BaseWogameVo {

    @JsonProperty("category_list")
    private List<CategoryItemVo> categoryList;

    public List<CategoryItemVo> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryItemVo> categoryList) {
        this.categoryList = categoryList;
    }
}

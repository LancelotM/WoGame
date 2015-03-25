package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jession on 14-6-22.
 */
public class CategoryGameVo {

    @JsonProperty("total_num")
    private Long total_num;

    @JsonProperty("items")
    private List<CategoryGameItemsVo> categoryGameItems;

    public Long getTotal_num() {
        return total_num;
    }

    public void setTotal_num(Long total_num) {
        this.total_num = total_num;
    }

    public List<CategoryGameItemsVo> getCategoryGameItems() {
        return categoryGameItems;
    }

    public void setCategoryGameItems(List<CategoryGameItemsVo> categoryGameItems) {
        this.categoryGameItems = categoryGameItems;
    }

}

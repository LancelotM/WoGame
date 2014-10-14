package com.unicom.game.center.vo;

import java.util.List;

/**
 * Created by jession on 14-6-22.
 */
public class SearchResultVo {
    private int totalRows;
    private int pageNum;
    private int returnRows;

    private List<SearchResultItemVo> searchList;

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getReturnRows() {
        return returnRows;
    }

    public void setReturnRows(int returnRows) {
        this.returnRows = returnRows;
    }

    public List<SearchResultItemVo> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchResultItemVo> searchList) {
        this.searchList = searchList;
    }
}

package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GameInfoDataVo {

    @JsonProperty("total_num")
    private long totalNum;

    @JsonProperty("items")
    private List<GameInfoItemVo> gameInfoItemVoList;

    public List<GameInfoItemVo> getGameInfoItemVoList() {
        return gameInfoItemVoList;
    }

    public void setGameInfoItemVoList(List<GameInfoItemVo> gameInfoItemVoList) {
        this.gameInfoItemVoList = gameInfoItemVoList;
    }

    public long getTotalNum() {

        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }
}


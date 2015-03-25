package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameDetailListVo extends BaseWogameVo{

    @JsonProperty("data")
    private GameDetailVo gameDetailVo;

    public GameDetailVo getGameDetailVo() {
        return gameDetailVo;
    }

    public void setGameDetailVo(GameDetailVo gameDetailVo) {
        this.gameDetailVo = gameDetailVo;
    }
}

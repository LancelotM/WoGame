package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameInfoListVo extends BaseWogameVo{

    @JsonProperty("data")
    private GameInfoDataVo gameInfoVo;

    public GameInfoDataVo getGameInfoVo() {
        return gameInfoVo;
    }

    public void setGameInfoVo(GameInfoDataVo gameInfoVo) {
        this.gameInfoVo = gameInfoVo;
    }
}

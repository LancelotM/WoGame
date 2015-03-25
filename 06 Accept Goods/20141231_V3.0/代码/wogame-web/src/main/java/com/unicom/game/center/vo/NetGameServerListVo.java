package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NetGameServerListVo extends BaseWogameVo{

    @JsonProperty("data")
    private NetGameServerVo netGameServerVo;

    public NetGameServerVo getNetGameServerVo() {
        return netGameServerVo;
    }

    public void setNetGameServerVo(NetGameServerVo netGameServerVo) {
        this.netGameServerVo = netGameServerVo;
    }
}

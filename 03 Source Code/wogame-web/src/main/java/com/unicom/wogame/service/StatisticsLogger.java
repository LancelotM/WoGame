package com.unicom.wogame.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.mapper.JsonMapper;

import java.util.Map;

/**
 * Created by jession on 14-6-11.
 */
public class StatisticsLogger {

    public static final String STATISTICS_LOGGER_NAME = "business";

    private Logger businessLogger = LoggerFactory.getLogger(STATISTICS_LOGGER_NAME);
    private JsonMapper jsonMapper = new JsonMapper();

    public void log(String entity, String action, String user, Map data) {
        String json = (data != null ? jsonMapper.toJson(data) : "{}");
        businessLogger.info("{},{},{},{}", entity, action, user, json);
    }
}

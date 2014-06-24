package com.unicom.game.center.service;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springside.modules.mapper.JsonMapper;

import java.util.Map;

/**
 * Created by jession on 14-6-11.
 */
@Component
public class StatisticsLogger {

    public static final String STATISTICS_LOGGER_NAME = "business";

    private Logger businessLogger = LoggerFactory.getLogger(STATISTICS_LOGGER_NAME);
    private JsonMapper jsonMapper = new JsonMapper();

    public void log(String categoryName, Map data) {
        Map<String, Object> loggerData = Maps.newHashMap();
        loggerData.put(categoryName, data);
        String json = (data != null ? jsonMapper.toJson(loggerData) : "{}");
        businessLogger.info("{}", json);
    }
}

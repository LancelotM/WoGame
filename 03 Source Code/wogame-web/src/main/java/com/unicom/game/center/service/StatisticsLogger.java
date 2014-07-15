package com.unicom.game.center.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springside.modules.mapper.JsonMapper;

/**
 * Created by jession on 14-6-11.
 */
@Component
public class StatisticsLogger {

    public static final String BUSINESS_LOGGER_NAME = "business";
    public static final String DOWNLOAD_LOGGER_NAME = "download";
    public static final String NUMBER_LOGGER_NAME = "number";

    private Logger businessLogger = LoggerFactory.getLogger(BUSINESS_LOGGER_NAME);
    private Logger downloadLogger = LoggerFactory.getLogger(DOWNLOAD_LOGGER_NAME);
    private Logger numberLogger = LoggerFactory.getLogger(NUMBER_LOGGER_NAME);

    private JsonMapper jsonMapper = new JsonMapper();

    public void info(String data) {
        businessLogger.info(data);
    }

    public void download(String data) {
        downloadLogger.info(data);
    }

    public void number(String data) {
        numberLogger.info(data+" ");
    }
}

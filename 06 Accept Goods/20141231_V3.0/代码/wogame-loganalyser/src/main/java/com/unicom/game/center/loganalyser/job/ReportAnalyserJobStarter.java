package com.unicom.game.center.loganalyser.job;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unicom.game.center.loganalyser.imp.ReportAnalyser;

/**
 * @author Alex Yin
 * 
 * @Date 2014-7-23
 */
public class ReportAnalyserJobStarter {

    public static void main(String[] args) throws Exception {
        String[] configLocations = {"classpath:applicationContext_LogAnalyser_bean.xml",
                "classpath:applicationContext_dao.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocations);

        ReportAnalyser reportAnalyser = (ReportAnalyser) ctx.getBean("reportAnalyser");
        reportAnalyser.doLogAnalyse();
    }

}

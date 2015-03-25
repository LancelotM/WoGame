package com.unicom.game.center.loganalyser.job;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unicom.game.center.loganalyser.imp.DownloadAnalyser;

/**
 * Created with IntelliJ IDEA.
 * User: claire_chang
 * Date: 14-7-14
 * Time: 下午1:22
 * To change this template use File | Settings | File Templates.
 */
public class DownloadAnalyserJobStarter {
    public static void main(String[] args) throws Exception {
        String[] configLocations = {"classpath:applicationContext_LogAnalyser_bean.xml",
                "classpath:applicationContext_dao.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocations);

        DownloadAnalyser downloadAnalyser = (DownloadAnalyser) ctx.getBean("downloadAnalyser");
        downloadAnalyser.doLogAnalyse();
    }
}

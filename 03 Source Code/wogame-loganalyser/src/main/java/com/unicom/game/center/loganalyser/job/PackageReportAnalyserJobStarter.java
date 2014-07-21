package com.unicom.game.center.loganalyser.job;

import com.unicom.game.center.loganalyser.imp.PackageReportAnalyser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: claire_chang
 * Date: 14-7-14
 * Time: 上午9:52
 * To change this template use File | Settings | File Templates.
 */
public class PackageReportAnalyserJobStarter {
    public static void main(String[] args) throws Exception {
        String[] configLocations = {"classpath:applicationContext_LogAnalyser_bean.xml",
                "classpath:applicationContext_dao.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocations);

        PackageReportAnalyser packageReportAnalyser = (PackageReportAnalyser) ctx.getBean("packageReportAnalyser");
        packageReportAnalyser.doPackageReportDomainsSave();
    }
}

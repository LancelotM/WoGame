package com.unicom.game.center.loganalyser.job;

import com.unicom.game.center.loganalyser.imp.LogAnalyser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: steve_shao
 * Date: 7/3/14
 * Time: 3:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class SavePackageInfoDomainsJobStarter {

    public static void main(String[] args) throws Exception {
        String[] configLocations = {"classpath:applicationContext_LogAnalyser_bean.xml",
				"classpath:applicationContext_dao.xml"};
           ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocations);

           LogAnalyser logAnalyser = (LogAnalyser) ctx.getBean("logAnalyser");
           logAnalyser.doPackageInfoDomainsSave();
   	}
}

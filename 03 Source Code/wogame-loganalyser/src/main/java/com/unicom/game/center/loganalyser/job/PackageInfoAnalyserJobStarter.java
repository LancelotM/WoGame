package com.unicom.game.center.loganalyser.job;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unicom.game.center.loganalyser.imp.PackageInfoAnalyser;

/**
 * Created with IntelliJ IDEA.
 * User: steve_shao
 * Date: 7/3/14
 * Time: 3:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class PackageInfoAnalyserJobStarter {

    public static void main(String[] args) throws Exception {
        String[] configLocations = {"classpath:applicationContext_LogAnalyser_bean.xml",
				"classpath:applicationContext_dao.xml"};
           ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocations);

           PackageInfoAnalyser logAnalyser = (PackageInfoAnalyser) ctx.getBean("packageInfoAnalyser");
           logAnalyser.doLogAnalyse();
   	}
}

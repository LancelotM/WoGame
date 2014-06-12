package com.unicom.game.center.loganalyser.job;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unicom.game.center.loganalyser.imp.LogAnalyser;

public class LogAnalyserJobStarter {

	public static void main(String[] args) {
        String[] configLocations = {"classpath:applicationContext_LogAnalyser_bean.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocations);
        
        LogAnalyser logAnalyser = (LogAnalyser) ctx.getBean("logAnalyser");
        logAnalyser.doLogAnaylyse();
	}

}

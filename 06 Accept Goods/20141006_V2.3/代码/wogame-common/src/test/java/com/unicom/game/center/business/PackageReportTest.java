package com.unicom.game.center.business;

import com.unicom.game.center.db.dao.PackageReportDao;
import com.unicom.game.center.db.dao.ZTEReportDao;
import com.unicom.game.center.db.domain.PackageReportDomain;
import com.unicom.game.center.db.domain.ZTEReportDomain;
import com.unicom.game.center.model.ReportInfo;
import com.unicom.game.center.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-7-10
 * Time: 下午4:08
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class PackageReportTest {
    @Autowired
    private PackageReportDao dao;

    @Autowired
    private PackageReportBusiness service;

    @Test
    public void testsava(){
        Random r = new Random();
        for(int i = 0;i< 10;i++){
            PackageReportDomain domain = new PackageReportDomain();
            domain.setAppid("\""+i+"\"");
            domain.setAppname("天天跑酷");
            domain.setChannelId("1325");
            domain.setPackageStatus(20);
            domain.setReceiptStatus(r.nextInt(10));
            domain.setDateCreated(DateUtils.stringToDate("2014-07-10","yyyy-MM-dd"));
            dao.save(domain);
        }


    }

    @Test
    public void testfetch(){
        ReportInfo info = service.fetchPackageReport("1325", "2013-07-09", "2014-07-23");
//        ReportInfo info = service.fetchReceiptInfo("1325", "2014-07-10", "2014-07-10");
        System.out.println("失败："+info.getFailSum()+"总数："+info.getPackageSum()+"成功："+info.getSucessSum());
    }

    @Test
    public void testFetchReceipt(){
        ReportInfo info = service.fetchReceiptInfo("1325", "2013-03-10", "2014-07-23");
        System.out.println("失败："+info.getFailSum()+"总数："+info.getPackageSum()+"成功："+info.getSucessSum()+"同步："+info.getSyncSum()+"未同步："+info.getNoSyncSum());
    }

}

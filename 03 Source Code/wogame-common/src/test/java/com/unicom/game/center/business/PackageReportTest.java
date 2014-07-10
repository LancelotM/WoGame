package com.unicom.game.center.business;

import com.unicom.game.center.db.dao.PackageReportDao;
import com.unicom.game.center.db.dao.ZTEReportDao;
import com.unicom.game.center.db.domain.PackageReportDomain;
import com.unicom.game.center.db.domain.ZTEReportDomain;
import com.unicom.game.center.model.ReportInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

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
        PackageReportDomain domain = new PackageReportDomain();
        domain.setAppid("01");
        domain.setAppname("天天跑酷");
        domain.setChannelId("1325");
        domain.setPackageStatus(1);
        domain.setReceiptStatus(0);
        domain.setDateCreated(new Date());
        dao.save(domain);

    }

    @Test
    public void testfetch(){
        ReportInfo info = service.fetchPackageReport("1325", "2014-07-10", "201407-10");
        System.out.println(info.getFailSum()+""+info.getPackageSum()+""+info.getSucessSum());
    }

}

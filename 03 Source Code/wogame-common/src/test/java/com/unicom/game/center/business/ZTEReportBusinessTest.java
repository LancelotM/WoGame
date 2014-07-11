package com.unicom.game.center.business;

import com.unicom.game.center.db.dao.ZTEReportDao;
import com.unicom.game.center.db.domain.ZTEReportDomain;
import com.unicom.game.center.model.ReportInfo;
import com.unicom.game.center.utils.DateUtils;
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
 * Time: 下午3:58
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(locations = { "classpath:applicationContext_dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ZTEReportBusinessTest {

    @Autowired
    private ZTEReportDao dao;

    @Autowired
    private ZTEReportBusiness service;

    @Test
    public void testsava(){
        ZTEReportDomain domain = new ZTEReportDomain();
        domain.setAppid("04");
        domain.setAppname("天天跑酷");
        domain.setChannelId("1325");
        domain.setDateCreate(DateUtils.stringToDate("2014-07-09", "yyyy-MM-dd"));
        domain.setOperateResult(0);
        dao.save(domain);

    }

    @Test
    public void testfetch(){
        ReportInfo info = service.fetchZTEInfo("1325", "2014-07-10", "2014-07-10");
        System.out.println(info.getFailSum()+""+info.getPackageSum()+""+info.getSucessSum());
    }
}

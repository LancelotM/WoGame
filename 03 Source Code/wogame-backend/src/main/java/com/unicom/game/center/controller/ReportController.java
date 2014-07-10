package com.unicom.game.center.controller;

import com.unicom.game.center.business.PackageReportBusiness;
import com.unicom.game.center.business.ZTEReportBusiness;
import com.unicom.game.center.model.ReportInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 14-7-10
 * Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ReportController {

    @Autowired
    private PackageReportBusiness packageReport;

    @Autowired
    private ZTEReportBusiness zteReport;

    @RequestMapping(value = "/getReport", method = {RequestMethod.GET})
    public ModelAndView getReport(){
        return new ModelAndView("/report");
    }

    @RequestMapping(value = "/reportInfo", method = {RequestMethod.POST})
    public ModelAndView getPackageReport(@RequestParam(value="channelId",required = true) String channelId,
                                         @RequestParam(value="startDate",required = true) String startDate,
                                         @RequestParam(value="endDate",required = true) String endDate){
        ModelMap map = new ModelMap();
        ReportInfo packageReportInfo = packageReport.fetchPackageReport(channelId, startDate, endDate);
        ReportInfo zteReportInfo = zteReport.fetchZTEInfo(channelId,startDate,endDate);
        map.put("packageReportInfo",packageReportInfo);
        map.put("zteReportInfo",zteReportInfo);
        return new ModelAndView("/report");
    }
}

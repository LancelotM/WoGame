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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

//    @RequestMapping(value = "/getReport", method = {RequestMethod.GET})
//    public ModelAndView getReport(HttpSession session){
//        Boolean adminFlag = (Boolean)session.getAttribute("admin");
//        if(null != session && null != adminFlag && adminFlag.booleanValue()){
//            return new ModelAndView("/report");
//        }else{
//            return new ModelAndView("/index");
//        }
//    }

    @RequestMapping(value = "/reportInfo", method = {RequestMethod.POST})
    public ModelAndView getPackageReport(@RequestParam(value="channelId",required = false) String channelId,
                                         @RequestParam(value="startDate",required = false) String startDate,
                                         @RequestParam(value="endDate",required = false) String endDate,HttpServletRequest request,HttpSession session){

        ModelMap map = new ModelMap();
        ReportInfo packageReportInfo = packageReport.fetchPackageReport(channelId, startDate, endDate);
        ReportInfo receiptReportInfo = packageReport.fetchReceiptInfo(channelId, startDate, endDate);
        ReportInfo zteReportInfo = zteReport.fetchZTEInfo(channelId,startDate,endDate);
        map.put("start",startDate);
        map.put("end",endDate);
        map.put("chaid",channelId);
        map.put("packageReportInfo",packageReportInfo);
        map.put("receiptReportInfo",receiptReportInfo);
        map.put("zteReportInfo",zteReportInfo);
        if(null == session){
            session = request.getSession(true);
        }
        Boolean adminFlag = (Boolean)session.getAttribute("admin");
        if(null != session && null != adminFlag && adminFlag.booleanValue()){
            return new ModelAndView("/report",map);
        }else{
            return new ModelAndView("/index");
        }
    }
}

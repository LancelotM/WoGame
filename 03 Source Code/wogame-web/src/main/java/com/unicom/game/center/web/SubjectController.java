package com.unicom.game.center.web;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.vo.SubjectDetailListVo;
import com.unicom.game.center.vo.SubjectDetailVo;
import com.unicom.game.center.vo.SubjectListVo;
import com.unicom.game.center.vo.SubjectVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    private GameService gameService;

    private Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public SubjectVo subjectList(@RequestParam("pageNum") int pageNum, @RequestParam(value="pageSize", required=false) int pageSize, Model model) {
        int size = 0;
        try{
            if(pageSize != 0){
                size = pageSize;
            }else {
                size = Constants.PAGE_SIZE_DEFAULT;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Error pageSize!");
        }

        SubjectListVo subjectListVo = gameService.readSubjectList(pageNum, size);

        return subjectListVo.getSubjectList();
    }

    @RequestMapping(value = "/gamelist", method = RequestMethod.GET)
    @ResponseBody
    public SubjectDetailVo subjectGameList(@RequestParam("id") int id, @RequestParam("pageNum") int pageNum, @RequestParam(value="pageSize", required=false) int pageSize, Model model) {
        int size = 0;
        try{
            if(pageSize != 0){
                size = pageSize;
            }else {
                size = Constants.PAGE_SIZE_DEFAULT;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Error pageSize!");
        }

        SubjectDetailListVo subjectDetailListVo = gameService.readSubjectDetailList(id, pageNum, size);

        return subjectDetailListVo.getSubjectDetailList();
    }
}

package com.unicom.game.center.web;

import com.unicom.game.center.service.GameService;
import com.unicom.game.center.util.Constants;
import com.unicom.game.center.vo.SubjectDetailListVo;
import com.unicom.game.center.vo.SubjectDetailVo;
import com.unicom.game.center.vo.SubjectListVo;
import com.unicom.game.center.vo.SubjectVo;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public SubjectVo subjectList(@RequestParam("pageNum") int pageNum, Model model) {
        SubjectListVo subjectListVo = gameService.readSubjectList(pageNum, Constants.PAGE_SIZE_DEFAULT);

        return subjectListVo.getSubjectList();
    }

    @RequestMapping(value = "/gamelist", method = RequestMethod.GET)
    @ResponseBody
    public SubjectDetailVo subjectGameList(@RequestParam("id") int id, @RequestParam("pageNum") int pageNum, Model model) {
        SubjectDetailListVo subjectDetailListVo = gameService.readSubjectDetailList(id, pageNum, Constants.PAGE_SIZE_DEFAULT);

        return subjectDetailListVo.getSubjectDetailList();
    }
}

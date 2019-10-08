package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.Editor;
import com.zjj.aisearch.model.MarkDown;
import com.zjj.aisearch.model.SystemLog;
import com.zjj.aisearch.model.User;
import com.zjj.aisearch.service.IndexService;
import com.zjj.aisearch.service.WriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @program: AISearch
 * @description: 写作
 * @author: zjj
 * @create: 2019-10-05 02:46:49
 **/
@Controller
@Slf4j
public class WriteController {

    @Autowired
    private WriteService writeServiceImpl;
    @Autowired
    private IndexService indexServiceImpl;

    @RequestMapping("markdown")
    public String markdown(RedirectAttributes attributes, HttpServletRequest httpServletRequest) {

        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user != null) {

            return "markdown";
        }else {
            attributes.addFlashAttribute("msg", "请登录");
            return "redirect:login";
        }
    }
    @RequestMapping("editor")
    public String editor(RedirectAttributes attributes, HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user != null) {

            return "editor";
        }else {
            attributes.addFlashAttribute("msg", "请登录");
            return "redirect:login";
        }
    }
    @RequestMapping("/savemarkdown")
    @ResponseBody
    public String saveMarkDown(@RequestBody MarkDown markDown, HttpServletRequest request) {
        Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
        log.info("保存markdown" + ":" + markDown.toString());
        markDown.setCreatetime(new Date().toLocaleString());
        markDown.setLoginLogId(loginLogId);
        writeServiceImpl.saveMarkDown(markDown);
        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(new Date().toLocaleString());
        systemLog.setOperation(":savemarkdown" + "?detail=" + markDown.toString());
        systemLog.setLoginLogId(loginLogId);
        indexServiceImpl.insertSystemLog(systemLog);
        return "success";
    }
    @RequestMapping("/saveeditor")
    @ResponseBody
    public String saveEditor(@RequestBody Editor editor, HttpServletRequest request) {
        Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
        log.info("保存editor" + ":" + editor.toString());
        editor.setCreatetime(new Date().toLocaleString());
        editor.setLoginLogId(loginLogId);
        writeServiceImpl.saveEditor(editor);
        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(new Date().toLocaleString());
        systemLog.setOperation(":saveeditor" + "?detail=" + editor.toString());
        systemLog.setLoginLogId(loginLogId);
        indexServiceImpl.insertSystemLog(systemLog);
        return "success";
    }

}

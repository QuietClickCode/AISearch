package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.SystemLog;
import com.zjj.aisearch.model.User;
import com.zjj.aisearch.service.IndexService;
import com.zjj.aisearch.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @program: AISearch
 * @description: 查询
 * @author: zjj
 * @create: 2019-10-05 02:46:49
 **/
@Controller
@Slf4j
public class BaseController {


    @Autowired
    private IndexService indexServiceImpl;

    /**
     * 跳转到login页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 跳转到regist页面
     *
     * @return
     */
    @GetMapping("/regist")
    public String regist() {
        return "regist";
    }

    /**
     * 重定向进入首页
     */
    @RequestMapping("/")
    public String index() {
        return "redirect:index";

    }

    /**
     * 进入首页的唯一入口
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
            systemLog.setOperation("index");
            systemLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertSystemLog(systemLog);
            model.addAttribute("msg", "你好," + user.getUsername());
            log.info("[{}]进入首页", user.getUsername());
            return "index";
        } else {
            redirectAttributes.addFlashAttribute("msg", "请登录");
            return "redirect:login";
        }
    }
}

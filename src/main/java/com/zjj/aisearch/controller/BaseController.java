package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.*;
import com.zjj.aisearch.service.IndexService;
import com.zjj.aisearch.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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
    @GetMapping("/")
    public String index() {
        return "redirect:index";

    }

    /**
     * 跳转到随机文章页面
     *
     * @return
     */
    @GetMapping("/article")
    public String article(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            return "article";
        } else {
            redirectAttributes.addFlashAttribute("msg", "请登录");
            return "redirect:login";
        }
    }

    /**
     * 进入首页的唯一入口
     */
    @GetMapping("/index")
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

    /**
     * 退出日志列表
     */
    @GetMapping("/logoutloglist")
    public String logoutLogList(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
            systemLog.setOperation("logoutloglist");
            systemLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertSystemLog(systemLog);
            List<LogoutLogList> logoutLogList = indexServiceImpl.selectLogoutLogList();
            model.addAttribute("items", logoutLogList);
            return "logoutloglist";
        } else {
            model.addAttribute("msg", "请登录");
            return "login";
        }
    }

    /**
     * 系统操作日志列表
     */
    @GetMapping("/systemloglist")
    public String systemloglist(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
            systemLog.setOperation("systemloglist");
            systemLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertSystemLog(systemLog);
            List<SystemLogList> systemLogList = indexServiceImpl.selectSystemLogList();
            model.addAttribute("items", systemLogList);
            return "systemLogList";
        } else {
            model.addAttribute("msg", "请登录");
            return "login";
        }
    }

    /**
     * 注册用户列表
     */
    @GetMapping("/userlist")
    public String userList(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
            systemLog.setOperation("userlist");
            systemLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertSystemLog(systemLog);
            List<UserLocation> userLocations = indexServiceImpl.selectUserLocation();
            model.addAttribute("items", userLocations);
            return "userlist";
        } else {
            model.addAttribute("msg", "请登录");
            return "login";
        }
    }

    /**
     * 登录日志列表
     */
    @GetMapping("/loginloglist")
    public String loginLogList(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
            systemLog.setOperation("loginloglist");
            systemLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertSystemLog(systemLog);
            List<LoginLogLocation> loginLogLocation = indexServiceImpl.selectLoginLocation();
            model.addAttribute("items", loginLogLocation);
            return "loginloglist";
        } else {
            model.addAttribute("msg", "请登录");
            return "login";
        }
    }

    /**
     * 搜索记录详情列表
     */
    @GetMapping("/list")
    public String list(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
            systemLog.setOperation("list");
            systemLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertSystemLog(systemLog);
            List<SearchRecordList> searchRecordList = indexServiceImpl.selectSearchRecordList();
            model.addAttribute("items", searchRecordList);
            return "list";
        } else {
            model.addAttribute("msg", "请登录");
            return "login";
        }
    }

    /**
     * 便签记录详情列表
     */
    @GetMapping("/ainote")
    public String aiNotelist(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
            systemLog.setOperation("ainote");
            systemLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertSystemLog(systemLog);
            List<AiNoteList> aiNoteList = indexServiceImpl.selectAiNoteList();
            model.addAttribute("items", aiNoteList);
            return "ainotelist";
        } else {
            model.addAttribute("msg", "请登录");
            return "login";
        }
    }

    /**
     * editor详情列表
     */
    @GetMapping("/editorlist")
    public String editorList(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
            systemLog.setOperation("editorlist");
            systemLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertSystemLog(systemLog);
            List<EditorList> editorLists = indexServiceImpl.selectEditorList();
            model.addAttribute("items", editorLists);
            return "editorlist";
        } else {
            model.addAttribute("msg", "请登录");
            return "login";
        }
    }

    /**
     * editor详情列表
     */
    @GetMapping("/markdownlist")
    public String markdownList(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
            systemLog.setOperation("markdownlist");
            systemLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertSystemLog(systemLog);
            List<MarkDownList> markDownLists = indexServiceImpl.selectMarkDownList();
            model.addAttribute("items", markDownLists);
            return "markdownlist";
        } else {
            model.addAttribute("msg", "请登录");
            return "login";
        }
    }


}

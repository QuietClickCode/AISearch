package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.*;
import com.zjj.aisearch.service.IndexService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
     * 跳转到随机文章页面
     *
     * @return
     */
    @GetMapping("/article")
    public String article() {
        return "article";
    }

    /**
     * 跳转到未授权页面
     *
     * @return
     */
    @GetMapping("/noauth")
    public String noauth() {
        return "noauth";
    }

    /**
     * 进入首页的唯一入口
     */
    @GetMapping("/index")
    @ApiOperation("首页")
    public String index() {
        Subject subject = SecurityUtils.getSubject();
        log.info("[{}]进入首页", ((User) subject.getPrincipal()).getUsername());
        return "index";
    }

    /**
     * 退出日志列表
     */
    @GetMapping("/logoutloglist")
    @ApiOperation("退出日志列表")
    public String logoutLogList(Model model) {
        List<LogoutLogList> logoutLogList = indexServiceImpl.selectLogoutLogList();
        model.addAttribute("items", logoutLogList);
        return "logoutloglist";
    }

    /**
     * 系统操作日志列表
     */
    @GetMapping("/systemloglist")
    @RequiresPermissions("user:systemloglist")
    @ApiOperation("系统操作日志列表")
    public String systemloglist(Model model) {
        List<SystemLogList> systemLogList = indexServiceImpl.selectSystemLogList();
        model.addAttribute("items", systemLogList);
        return "systemLogList";
    }

    /**
     * 注册用户列表
     */
    @GetMapping("/userlist")
    @ApiOperation("注册用户列表")
    public String userList(Model model) {
        List<UserLocation> userLocations = indexServiceImpl.selectUserLocation();
        model.addAttribute("items", userLocations);
        return "userlist";
    }

    /**
     * 登录日志列表
     */
    @GetMapping("/loginloglist")
    @ApiOperation("登录日志列表")
    public String loginLogList(Model model) {
        List<LoginLogLocation> loginLogLocation = indexServiceImpl.selectLoginLocation();
        model.addAttribute("items", loginLogLocation);
        return "loginloglist";
    }

    /**
     * 搜索记录详情列表
     */
    @GetMapping("/list")
    @ApiOperation("搜索记录详情列表")
    public String list(Model model) {
        List<SearchRecordList> searchRecordList = indexServiceImpl.selectSearchRecordList();
        model.addAttribute("items", searchRecordList);
        return "list";
    }

    /**
     * 便签记录详情列表
     */
    @GetMapping("/ainote")
    @ApiOperation("便签记录详情列表")
    public String aiNotelist(Model model) {
        List<AiNoteList> aiNoteList = indexServiceImpl.selectAiNoteList();
        model.addAttribute("items", aiNoteList);
        return "ainotelist";
    }

    /**
     * editor详情列表
     */
    @GetMapping("/editorlist")
    @ApiOperation("editor详情列表")
    public String editorList(Model model) {
        List<EditorList> editorLists = indexServiceImpl.selectEditorList();
        model.addAttribute("items", editorLists);
        return "editorlist";
    }

    /**
     * markdown详情列表
     */
    @GetMapping("/markdownlist")
    @ApiOperation("markdown详情列表")
    public String markdownList(Model model) {
        List<MarkDownList> markDownLists = indexServiceImpl.selectMarkDownList();
        model.addAttribute("items", markDownLists);
        return "markdownlist";
    }


}

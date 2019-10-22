package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.User;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: AISearch
 * @description: 跳转Controller,前后端分离可以用nginx或者node.js替代,唯一作用就是跳转页面
 * @author: zjj
 * @create: 2019-10-05 02:46:49
 **/
@Controller
@Slf4j
public class BaseController {

    /**
     * 跳转系统操作日志列表
     */
    @GetMapping("/systemloglist")
    @RequiresPermissions("user:systemloglist")
    @ApiOperation("跳转系统操作日志列表")
    public String systemloglist() {
        return "systemloglist";
    }

    /**
     * 跳转便签记录列表
     */
    @GetMapping("/ainotelist")
    @ApiOperation("跳转便签记录列表")
    public String ainotelist() {
        return "ainotelist";
    }

    /**
     * 跳转搜索记录列表
     */
    @GetMapping("/list")
    @ApiOperation("跳转搜索记录列表")
    public String list() {
        return "list";
    }

    /**
     * 跳转登录日志列表
     */
    @GetMapping("/loginloglist")
    @ApiOperation("跳转登录日志列表")
    public String loginloglist() {
        return "loginloglist";
    }

    /**
     * 跳转退出日志列表
     */
    @GetMapping("/logoutloglist")
    @ApiOperation("跳转退出日志列表")
    public String logoutloglist() {
        return "logoutloglist";
    }

    /**
     * 跳转markdown日志列表
     */
    @GetMapping("/markdownlist")
    @ApiOperation("跳转markdown日志列表")
    public String markdownlist() {
        return "markdownlist";
    }

    /**
     * 跳转注册用户列表
     */
    @GetMapping("/userlist")
    @ApiOperation("跳转注册用户列表")
    public String userlist() {
        return "userlist";
    }

    /**
     * 跳转editor记录列表
     */
    @GetMapping("/editorlist")
    @ApiOperation("跳转editor记录列表")
    public String editorlist() {
        return "editorlist";
    }

    /**
     * 跳风景美食网站
     */
    @GetMapping("/website")
    @ApiOperation("跳风景美食网站")
    public String website() {
        return "website";
    }

    /**
     * 跳转到login页面
     *
     * @return
     */
    @GetMapping("/login")
    @ApiOperation("跳转到login页面")
    public String login() {
        return "login";
    }


    /**
     * 跳转到regist页面
     *
     * @return
     */
    @GetMapping("/regist")
    @ApiOperation("跳转到regist页面")
    public String regist() {
        return "regist";
    }

    /**
     * 跳转到随机文章页面
     *
     * @return
     */
    @GetMapping("/article")
    @ApiOperation("跳转到随机文章页面")
    public String article() {
        return "article";
    }

    /**
     * 跳转到未授权页面
     *
     * @return
     */
    @GetMapping("/noauth")
    @ApiOperation("跳转到未授权页面")
    public String noauth() {

        return "noauth";
    }
    /**
     * 跳转到404页面
     *
     * @return
     */
    @GetMapping("/error")
    @ApiOperation("跳转到404页面")
    public String error() {
        return "error";
    }
    /**
     * 跳转到搜索详情页面
     *
     * @return
     */
    @GetMapping("/detail")
    @ApiOperation("跳转到搜索详情页面")
    public String detail() {
        return "detail";
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





}

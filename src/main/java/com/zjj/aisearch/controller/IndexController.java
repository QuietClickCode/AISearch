package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.*;
import com.zjj.aisearch.service.IndexService;
import com.zjj.aisearch.utils.DateTimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/***
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-09-21 19:16:26
 **/
@RestController
@Slf4j
@Api(value = "首页", description = "首页")
public class IndexController {

    @Autowired
    private IndexService indexServiceImpl;

    /**
     * 异步校验用户名
     *
     * @return
     */
    @GetMapping("/validateUsername")
    @ApiOperation("异步校验用户名")
    public Object validateUsername(String username) {
        int result = indexServiceImpl.validateUsername(username);
        ResponseResult responseResult = new ResponseResult();
        if (result == 0) {
            responseResult.setStatus(0);
            return responseResult;
        } else {
            responseResult.setStatus(-1).setMsg("用户名已存在");
            return responseResult;
        }
    }

    /**
     * login.html,ajax发送的登录请求
     * UserInfo 包含浏览器传过来的所有信息
     * <p>
     * 待优化:没做异常处理,
     * 数据库字段如果为not null,
     * 前端传过来为null,就会出问题
     * <p>
     * 这个登录的记录系统日志不要去动他,因为比较重要,牵一发动全身
     */
    @RequestMapping("/tologin")
    @ApiOperation(value = "登录")
    public Object tologin(@RequestBody UserInfo userInfo) {

        String username = userInfo.getUser().getUsername();
        String password = userInfo.getUser().getPassword();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        ResponseResult responseResult = new ResponseResult();

        //登录验证
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            log.error("用户不存在");
            responseResult.setMsg("用户不存在").setStatus(-1);
            return responseResult;

        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            log.error("密码错误");
            responseResult.setMsg("密码错误").setStatus(-1);
            return responseResult;
        }

        User user = (User) subject.getPrincipal();

        //插入本次登录的浏览器信息:型号,版本,系统类型
        BrowserInfo browserInfo = new BrowserInfo();
        browserInfo.setSystem(userInfo.getBrowserInfo()[0]);
        browserInfo.setBrowserType(userInfo.getBrowserInfo()[1]);
        browserInfo.setBrowserVersion(userInfo.getBrowserInfo()[2]);
        indexServiceImpl.insertBrowserInfo(browserInfo);
        //返回自动递增的ID
        String browserInfoId = browserInfo.getBrowserInfoId();

        //插入位置信息:X,Y,公网IP,地点,设备类型
        Location location = new Location();
        location.setIp(userInfo.getLocation()[0]);
        location.setLocation(userInfo.getLocation()[1]);
        location.setLocalIp(userInfo.getLocalIp());
        location.setX(userInfo.getLocation()[2]);
        location.setY(userInfo.getLocation()[3]);
        location.setKeyword(userInfo.getPcOrPhone());
        indexServiceImpl.insertLocation(location);
        //返回自动递增的ID
        String locationId = location.getLocationId();

        //插入登录日志
        Integer userId = user.getId();
        LoginLog loginLog = new LoginLog();
        loginLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        loginLog.setBrowserInfoId(browserInfoId);
        loginLog.setLocationId(locationId);
        loginLog.setUserId(userId);
        indexServiceImpl.insertLoginLog(loginLog);
        //返回本次登录日志id
        Integer loginLogId = loginLog.getId();


        log.info("[{}]正在登陆,登录ID为[{}]", username, loginLogId);

        Session session = subject.getSession();
        //往session存入用户数据,和登录loginLogId,用于判断是否登录
        session.setAttribute("user", user);
        session.setAttribute("loginLogId", loginLogId);

        //插入系统日志
        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        systemLog.setOperation("login?" + "username=" + username);
        systemLog.setLoginLogId(loginLogId);
        indexServiceImpl.insertSystemLog(systemLog);
        responseResult.setUrl("index").setStatus(0);
        return responseResult;
    }


    /**
     * regist.html,ajax发送的登录请求
     * UserInfo 包含浏览器传过来的所有信息
     * <p>
     * 待优化:没做异常处理,
     * 数据库字段如果为not null,
     * 前端传过来为null,就会出问题
     */
    @RequestMapping("/toregist")
    @ResponseBody
    @ApiOperation("注册")
    public Object toregist(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        //插入本次登录的浏览器信息:型号,版本,系统类型
        BrowserInfo browserInfo = new BrowserInfo();
        browserInfo.setSystem(userInfo.getBrowserInfo()[0]);
        browserInfo.setBrowserType(userInfo.getBrowserInfo()[1]);
        browserInfo.setBrowserVersion(userInfo.getBrowserInfo()[2]);
        indexServiceImpl.insertBrowserInfo(browserInfo);
        //返回自动递增的ID
        String browserInfoId = browserInfo.getBrowserInfoId();

        //插入位置信息:X,Y,公网IP,地点,设备类型
        Location location = new Location();
        location.setIp(userInfo.getLocation()[0]);
        location.setLocation(userInfo.getLocation()[1]);
        location.setLocalIp(userInfo.getLocalIp());
        location.setX(userInfo.getLocation()[2]);
        location.setY(userInfo.getLocation()[3]);
        location.setKeyword(userInfo.getPcOrPhone());
        indexServiceImpl.insertLocation(location);
        //返回自动递增的ID
        String locationId = location.getLocationId();

        //插入用户表
        User user = userInfo.getUser();
        user.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        user.setBrowserInfoId(browserInfoId);
        user.setLocationId(locationId);

        ResponseResult responseResult = new ResponseResult();
        //异常
        try {
            indexServiceImpl.insertUser(user);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                e.printStackTrace();
                responseResult.setMsg("用户名已存在");
                return responseResult;
            } else {
                e.printStackTrace();
                responseResult.setMsg("未知异常,请检查用户名密码是否符合规范!");
                return responseResult;
            }
        }

        //正常
        //拿到登录id,考虑如果用户已登录然后注册其他账号,
        //没有登录,loginLogId就为空
        //插入系统日志
        Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        systemLog.setOperation("regist?" + "username=" + user.getUsername());
        systemLog.setLoginLogId(loginLogId);
        indexServiceImpl.insertSystemLog(systemLog);
        log.info("[{}]注册成功", user.getUsername());
        responseResult.setMsg("恭喜" + user.getUsername() + "注册成功" + ",您是第" + user.getId() + "位用户")
                .setUrl("login").setStatus(0);
        return responseResult;

    }


    /**
     * ajax实时搜索
     */
    @RequestMapping("/searchItem")
    @ResponseBody
    @ApiOperation("ajax实时搜索")
    public Object searchItem(String keyword) {
        if (!keyword.isEmpty()) {
            List<Item> items = indexServiceImpl.searchItem(keyword);
            return items;
        }
        return null;
    }




    @GetMapping("/logout")
    @ApiOperation("logout")
    public Object logout(HttpServletRequest httpServletRequest) {
        User user = ((User) SecurityUtils.getSubject().getPrincipal());
        ResponseResult responseResult = new ResponseResult();

        Integer loginLogId = (Integer) httpServletRequest.getSession().getAttribute("loginLogId");

        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        systemLog.setOperation(":logout?username" + user.getUsername());
        systemLog.setLoginLogId(loginLogId);
        indexServiceImpl.insertSystemLog(systemLog);

        LogoutLog logoutLog = new LogoutLog();
        logoutLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        logoutLog.setLoginLogId(loginLogId);
        indexServiceImpl.insertLogoutLog(logoutLog);
        responseResult.setUrl("login").setStatus(0).setMsg("退出成功");
        SecurityUtils.getSubject().logout();
        /*这儿退出交给shiro来管理,自己销毁session会报错*/
        /*httpServletRequest.getSession().invalidate();*/
        return responseResult;
    }




    /**
     * 重定向到搜索结果详情页
     */
    @RequestMapping("/todetail")
    @ApiOperation("重定向到搜索结果详情页")
    public String toDetail(@RequestBody Map<String, String> map, HttpServletRequest request) {
        if (!map.get("keyword").isEmpty()) {
            request.getSession().setAttribute("keyword", map.get("keyword"));
            //不做任何事,避免生成两次记录
            return null;
        }
        return null;
    }


    /**
     * 进入搜索结果详情页
     */
    @RequestMapping("/detaillist")
    @ApiOperation("进入搜索结果详情页")
    public Object detail(HttpServletRequest request, HttpServletRequest
            httpServletRequest) {
        String keyword = (String) httpServletRequest.getSession().getAttribute("keyword");
        List<Item> items = indexServiceImpl.searchItem(keyword);

        Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");

        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        systemLog.setOperation("detail" + "?keyword=" + keyword);
        systemLog.setLoginLogId(loginLogId);

        SearchRecord searchRecord = new SearchRecord();
        searchRecord.setKeyword(keyword);
        searchRecord.setSearchTime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        searchRecord.setLoginLogId(loginLogId);
        indexServiceImpl.insertSearchRecord(searchRecord);
        indexServiceImpl.insertSystemLog(systemLog);

        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(items);
        responseResult.setMsg(keyword);
        return responseResult;
    }

    /**
     * 便签模式
     */
    @PostMapping("/note")
    @ResponseBody
    @ApiOperation("便签模式")
    public Object note(@RequestBody Map<String, String> map, HttpServletRequest request) {
        log.info("便签内容为:[{}]", map.get("content"));
        ResponseResult responseResult = new ResponseResult();
        Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");

        AiNote aiNote = new AiNote();
        aiNote.setContent(map.get("content"));
        aiNote.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        aiNote.setLoginLogId(loginLogId);

        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        systemLog.setOperation("note" + "?content=" + aiNote.getContent());
        systemLog.setLoginLogId(loginLogId);

        indexServiceImpl.insertSystemLog(systemLog);
        indexServiceImpl.insertAiNote(aiNote);
        responseResult.setStatus(0).setData(map.get("content"));
        return responseResult;
    }


    /**
     * 查询文章功能
     */
    @PostMapping("/queryarticle")
    @ApiOperation("查询文章功能")
    public Object queryArticle(@RequestBody Map<String, String> map) {
        List<Article> articles = indexServiceImpl.queryArticle(map);
        return articles;
    }

    /**
     * 查询系统操作日志列表
     */
    @PostMapping("/selectsystemloglist")
    @ApiOperation("系统操作日志列表")
    public Integer selectsystemloglist() {
       Integer count = indexServiceImpl.selectSystemLogList();
        return count.intValue();
    }

}



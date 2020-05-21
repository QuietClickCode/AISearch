package com.zjj.aisearch.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zjj.aisearch.config.ConfigBean;
import com.zjj.aisearch.model.*;
import com.zjj.aisearch.service.IndexService;
import com.zjj.aisearch.utils.DateTimeUtil;
import com.zjj.aisearch.utils.IPUtil;
import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;
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
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
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

    @Autowired
    ConfigBean configBean;

    /**
     * 异步校验用户名
     *
     * @return
     */
    @GetMapping("/validateUsername")
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

    public String getBrowserName(String agent) {
        if (agent.indexOf("msie 7") > 0) {
            return "ie7";
        } else if (agent.indexOf("msie 8") > 0) {
            return "ie8";
        } else if (agent.indexOf("msie 9") > 0) {
            return "ie9";
        } else if (agent.indexOf("msie 10") > 0) {
            return "ie10";
        } else if (agent.indexOf("msie") > 0) {
            return "ie";
        } else if (agent.indexOf("opera") > 0) {
            return "opera";
        } else if (agent.indexOf("opera") > 0) {
            return "opera";
        } else if (agent.indexOf("firefox") > 0) {
            return "firefox";
        } else if (agent.indexOf("webkit") > 0) {
            return "webkit";
        } else if (agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0) {
            return "ie11";
        } else {
            return "Others";
        }
    }

    /**
     *
     * 获取系统版本信息
     */
    public static String getSystem(HttpServletRequest request)
    {
        String systenInfo = null;
        String header = request.getHeader("user-agent");
        if (header == null || header.equals(""))// 为空都默认win10
        {
            systenInfo = "windows10";
            return systenInfo;
        }

        // 得到用户的操作系统
        if (header.indexOf("NT 6.1") > 0 || header.indexOf("NT 5") > 0 || header.indexOf("NT 6.3") > 0 || header.indexOf("NT 6.2") > 0 || header.indexOf("NT 6.0") > 0 || header.indexOf("NT 5.1") > 0
                || header.indexOf("NT 5.2") > 0 || header.indexOf("NT 6.0") > 0)// win10一下的都取win7
        {
            systenInfo = "windows7";
        }
        if (header.indexOf("Mac") > 0)// mac系统
        {
            systenInfo = "mac系统";
        }
        if (header.indexOf("Unix") > 0)// unix系统
        {
            systenInfo = "unix系统";
        }
        if (header.indexOf("SunOS") > 0)// solaris系统
        {
            systenInfo = "solaris系统";
        }
        if (header.indexOf("Linux") > 0)// Linux系统
        {
            systenInfo = "linux系统";
        }
        if (header.indexOf("Ubuntu") > 0)// ubuntu系统
        {
            systenInfo = "ubuntu系统";
        }
        if (header.indexOf("iPhone") > 0)// 苹果手机
        {
            systenInfo = "苹果手机";
        }
        if (header.indexOf("Android") > 0)// 安卓系统
        {
            systenInfo = "安卓手机";
        }
        if (header.indexOf("NT 10") > 0)// win10
        {
            systenInfo = "windows10";
        }
        if (header == null || header.equals("") || systenInfo.equals("") || systenInfo == null)// 没找到默认为windows10
        {
            systenInfo = "windows10";
        }
        return systenInfo;

    }
    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址。
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串
     * @param request
     * @return
     */
    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if("127.0.0.1".equals(ip)||"0:0:0:0:0:0:0:1".equals(ip)){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        return ip;
    }
    /**
     * 获取Ip地址，多级反向代理
     * @param request
     * @return
     */
    public static String getIpaddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
    public static String getMyIP() throws IOException {
        String url="http://ip.chinaz.com/getip.aspx";
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText =  sb.toString();;
            jsonText=jsonText.replaceAll("'", "");
            jsonText=jsonText.substring(1,jsonText.length()-1);
            jsonText=jsonText.replaceAll(",", "<br/>");
            return jsonText;
        } finally {
            is.close();
            // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
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
    public Object tologin(@RequestBody UserInfo userInfo,HttpServletRequest request) throws IOException {
        System.out.println(IPUtil.getPublicIp());
        System.out.println(getIpaddr(request));
        System.out.println(IPUtil.getIpAddress(request));
        System.out.println(getSystem(request));
        UASparser uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
        UserAgentInfo userAgentInfo = uasParser.parse(request.getHeader("User-Agent"));
                    System.out.println("操作系统名称："+userAgentInfo.getOsFamily());//
                    System.out.println("操作系统："+userAgentInfo.getOsName());//
                    System.out.println("浏览器名称："+userAgentInfo.getUaFamily());//
                    System.out.println("浏览器版本："+userAgentInfo.getBrowserVersionInfo());//
                    System.out.println("设备类型："+userAgentInfo.getDeviceType());
                    System.out.println("浏览器:"+userAgentInfo.getUaName());
                    System.out.println("类型："+userAgentInfo.getType());
     /*   //获取浏览器信息
        String ua = request.getHeader("User-Agent");
        System.out.println(ua);
//转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
//获取浏览器信息
        Browser browser = userAgent.getBrowser();
//获取系统信息
        OperatingSystem os = userAgent.getOperatingSystem();
        System.out.println(os.isMobileDevice());
//系统名称
        String system = os.getName();
//浏览器名称
        String browserName = browser.getName();
        System.out.println(browser.getName());
        System.out.println(browser.getBrowserType());
        System.out.println(browser.getGroup());
        System.out.println(browser.getVersion(ua));
        System.out.println(browser.getManufacturer());
        System.out.println(browser.getRenderingEngine());
        System.out.println(os.getName());
        System.out.println(os.getGroup());
        System.out.println(os.getManufacturer());
        System.out.println(os.getDeviceType());*/
        /*
        String agent=request.getHeader("User-Agent").toLowerCase();
        System.out.println(agent);
        System.out.println("浏览器版本："+getBrowserName(agent));*/
        log.info("上传目录:" + configBean.getImgDir());
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
        //定义token
        String token = "";
        token = JWT.create().withAudience(user.getUsername())
                .sign(Algorithm.HMAC256(user.getPassword()));
        log.error(token);

BrowserInfo browserInfo = new BrowserInfo();
browserInfo.setBrowserVersion(userAgentInfo.getBrowserVersionInfo());
browserInfo.setBrowserType(userAgentInfo.getUaFamily());
browserInfo.setSystem(getSystem(request));
        indexServiceImpl.insertBrowserInfo(browserInfo);
        //返回自动递增的ID
        String browserInfoId = browserInfo.getBrowserInfoId();
        Location location = new Location();
        //
        location.setIp(getIpaddr(request));
        location.setLocation("重庆市重庆市");
        location.setLocalIp(getIpAddress(request));
        location.setX("29.56471");
        location.setY("106.55073");
        location.setKeyword(userAgentInfo.getDeviceType());
        indexServiceImpl.insertLocation(location);
        //返回自动递增的ID
        String locationId = location.getLocationId();
//插入登录日志
        Integer userId = user.getId();
        LoginLog loginLog = getLoginLog(browserInfoId, locationId, userId);
        indexServiceImpl.insertLoginLog(loginLog);
        //返回本次登录日志id
        Integer loginLogId = loginLog.getId();


        log.info("[{}]正在登陆,登录ID为[{}]", username, loginLogId);

        Session session = subject.getSession();
        //往session存入用户数据,和登录loginLogId,用于判断是否登录
        session.setAttribute("user", user);
        session.setAttribute("loginLogId", loginLogId);

        //插入系统日志
        SystemLog systemLog = getSystemLog(username, loginLogId, "login?", "username=");
        indexServiceImpl.insertSystemLog(systemLog);
        //插入本次登录的浏览器信息:型号,版本,系统类型
        /*BrowserInfo browserInfo = insertBrowserInfo(userInfo);
        indexServiceImpl.insertBrowserInfo(browserInfo);
        //返回自动递增的ID
        String browserInfoId = browserInfo.getBrowserInfoId();

        //插入位置信息:X,Y,公网IP,地点,设备类型
        Location location = insertLocationInfo(userInfo);
        indexServiceImpl.insertLocation(location);
        //返回自动递增的ID
        String locationId = location.getLocationId();

        //插入登录日志
        Integer userId = user.getId();
        LoginLog loginLog = getLoginLog(browserInfoId, locationId, userId);
        indexServiceImpl.insertLoginLog(loginLog);
        //返回本次登录日志id
        Integer loginLogId = loginLog.getId();


        log.info("[{}]正在登陆,登录ID为[{}]", username, loginLogId);

        Session session = subject.getSession();
        //往session存入用户数据,和登录loginLogId,用于判断是否登录
        session.setAttribute("user", user);
        session.setAttribute("loginLogId", loginLogId);

        //插入系统日志
        SystemLog systemLog = getSystemLog(username, loginLogId, "login?", "username=");
        indexServiceImpl.insertSystemLog(systemLog);*/
        responseResult.setUrl("index").setStatus(0);
        responseResult.setMsg(token);
        return responseResult;
    }

    private SystemLog getSystemLog(String username, Integer loginLogId, String s, String s2) {
        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        systemLog.setOperation(s + s2 + username);
        systemLog.setLoginLogId(loginLogId);
        return systemLog;
    }

    private LoginLog getLoginLog(String browserInfoId, String locationId, Integer userId) {
        LoginLog loginLog = new LoginLog();
        loginLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        loginLog.setBrowserInfoId(browserInfoId);
        loginLog.setLocationId(locationId);
        loginLog.setUserId(userId);
        return loginLog;
    }

    private BrowserInfo insertBrowserInfo(@RequestBody UserInfo userInfo) {
        BrowserInfo browserInfo = new BrowserInfo();
        browserInfo.setSystem(userInfo.getBrowserInfo()[0]);
        browserInfo.setBrowserType(userInfo.getBrowserInfo()[1]);
        browserInfo.setBrowserVersion(userInfo.getBrowserInfo()[2]);
        return browserInfo;
    }

    //抽取的公共方法插入位置信息
    private Location insertLocationInfo(@RequestBody UserInfo userInfo) {
        Location location = new Location();
        /*location.setIp(userInfo.getLocation()[0]);
        location.setLocation(userInfo.getLocation()[1]);
        location.setLocalIp(userInfo.getLocalIp());
        location.setX(userInfo.getLocation()[2]);
        location.setY(userInfo.getLocation()[3]);
        location.setKeyword(userInfo.getPcOrPhone());*/
        return location;
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
    @ApiOperation("注册")
    public Object toregist(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        //插入本次登录的浏览器信息:型号,版本,系统类型
        BrowserInfo browserInfo = insertBrowserInfo(userInfo);
        indexServiceImpl.insertBrowserInfo(browserInfo);
        //返回自动递增的ID
        String browserInfoId = browserInfo.getBrowserInfoId();

        //插入位置信息:X,Y,公网IP,地点,设备类型
        Location location = insertLocationInfo(userInfo);
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
                responseResult.setStatus(-1);
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
        SystemLog systemLog = getSystemLog(user.getUsername(), loginLogId, "regist?", "username=");
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
    public Object searchItem(String keyword) {
        if (!keyword.isEmpty()) {
            List<Item> items = indexServiceImpl.searchItem(keyword);
            return items;
        }
        return null;
    }


    @PostMapping("/logout")
    @ApiOperation("logout")
    public Object logout(HttpServletRequest httpServletRequest) {
        //去除登录校验之后,没有保存user,所以会报错
        //User user = ((User) SecurityUtils.getSubject().getPrincipal());
        ResponseResult responseResult = new ResponseResult();

        Integer loginLogId = (Integer) httpServletRequest.getSession().getAttribute("loginLogId");

        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //  systemLog.setOperation(":logout?username" + user.getUsername());
        systemLog.setLoginLogId(loginLogId);
        indexServiceImpl.insertSystemLog(systemLog);

        LogoutLog logoutLog = getLogoutLog(loginLogId);
        indexServiceImpl.insertLogoutLog(logoutLog);
        responseResult.setUrl("login").setStatus(0).setMsg("退出成功");
        SecurityUtils.getSubject().logout();
        /*这儿退出交给shiro来管理,自己销毁session会报错*/
        /*httpServletRequest.getSession().invalidate();*/
        return responseResult;
    }

    //idea重构
    private LogoutLog getLogoutLog(Integer loginLogId) {
        LogoutLog logoutLog = new LogoutLog();
        logoutLog.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        logoutLog.setLoginLogId(loginLogId);
        return logoutLog;
    }


    /**
     * 重定向到搜索结果详情页
     */
    @RequestMapping("/todetail")
    public ResponseResult toDetail(@RequestBody Map<String, String> map, HttpServletRequest request) {
        ResponseResult responseResult = new ResponseResult();
        if (!map.get("keyword").isEmpty()) {
            request.getSession().setAttribute("keyword", map.get("keyword"));
            //不做任何事,避免生成两次记录
            responseResult.setUrl("detail");
            return responseResult;
        }
        return null;
    }


    /**
     * 进入搜索结果详情页
     */
    @RequestMapping("/detaillist")
    @ApiOperation("进入搜索结果详情页")
    public Object detail(HttpServletRequest httpServletRequest, HttpServletResponse
            response) {
        String keyword = (String) httpServletRequest.getSession().getAttribute("keyword");
        List<Item> items = indexServiceImpl.searchItem(keyword);

        Integer loginLogId = (Integer) httpServletRequest.getSession().getAttribute("loginLogId");

        SystemLog systemLog = getSystemLog(keyword, loginLogId, "detail", "?keyword=");

        SearchRecord searchRecord = getSearchRecord(keyword, loginLogId);
        indexServiceImpl.insertSearchRecord(searchRecord);
        indexServiceImpl.insertSystemLog(systemLog);

        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(items);
        responseResult.setMsg(keyword);
        return responseResult;
    }

    private SearchRecord getSearchRecord(String keyword, Integer loginLogId) {
        SearchRecord searchRecord = new SearchRecord();
        searchRecord.setKeyword(keyword);
        searchRecord.setSearchTime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        searchRecord.setLoginLogId(loginLogId);
        return searchRecord;
    }

    /**
     * 便签模式
     */
    @PostMapping("/note")
    @ApiOperation("便签模式")
    public Object note(@RequestBody Map<String, String> map, HttpServletRequest request) {
        log.info("便签内容为:[{}]", map.get("content"));
        ResponseResult responseResult = new ResponseResult();
        Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");

        AiNote aiNote = new AiNote();
        aiNote.setContent(map.get("content"));
        aiNote.setCreatetime(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        aiNote.setLoginLogId(loginLogId);

        SystemLog systemLog = getSystemLog(aiNote.getContent(), loginLogId, "note", "?content=");

        indexServiceImpl.insertSystemLog(systemLog);
        indexServiceImpl.insertAiNote(aiNote);
        responseResult.setStatus(0).setData(map.get("content"));
        return responseResult;
    }


    /**
     * 查询文章功能
     */
    @PostMapping("/queryarticle")
    public Object queryArticle(@RequestBody Map<String, String> map) {
        List<Article> articles = indexServiceImpl.queryArticle(map);
        return articles;
    }


}



package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.*;
import com.zjj.aisearch.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/***
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-09-21 19:16:26
 **/
@Controller
public class IndexController {

    @Autowired
    private IndexService indexServiceImpl;

    /**
     * 这样用可能有缺陷,内存泄漏
     */
    private HashMap<String, Object> map = new HashMap<>();

    /**
     * 实现转发模式,/index
     */
    @RequestMapping(value = "{path}")
    public String del(@PathVariable("path") String path) {
        return path;
    }


    /**
     * 登录
     */
    @RequestMapping("/tologin")
    @ResponseBody
    public String tologin(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        String username = userInfo.getUser().getUsername();
        User isExistUser = indexServiceImpl.selectUserByUserName(username);
        if (isExistUser != null) {
            boolean isEqual = userInfo.getUser().getPassword().equals(isExistUser.getPassword());
            if (isEqual) {
                BrowserInfo browserInfo = new BrowserInfo();
                browserInfo.setSystem(userInfo.getBrowserInfo()[0]);
                browserInfo.setBrowserType(userInfo.getBrowserInfo()[1]);
                browserInfo.setBrowserVersion(userInfo.getBrowserInfo()[2]);
                indexServiceImpl.insertBrowserInfo(browserInfo);
                String browserInfoId = browserInfo.getBrowserInfoId();
                Location location = new Location();
                location.setIp(userInfo.getLocation()[0]);
                location.setLocation(userInfo.getLocation()[1]);
                location.setLocalIp(userInfo.getLocalIp());
                location.setX(userInfo.getLocation()[2]);
                location.setY(userInfo.getLocation()[3]);
                location.setKeyword(userInfo.getPcOrPhone());
                indexServiceImpl.insertLocation(location);
                String locationId = location.getLocationId();
                Integer userId = isExistUser.getId();
                LoginLog loginLog = new LoginLog();
                loginLog.setCreatetime(new Date().toLocaleString());
                loginLog.setBrowserInfoId(browserInfoId);
                loginLog.setLocationId(locationId);
                loginLog.setUserId(userId);
                /**写入登录日志*/
                indexServiceImpl.insertLoginLog(loginLog);

                //获取本次登录日志id
                Integer loginLogId = loginLog.getId();
                request.getSession().setAttribute("user", isExistUser);
                request.getSession().setAttribute("loginLogId", loginLogId);
                SystemLog systemLog = new SystemLog();
                systemLog.setCreatetime(new Date().toLocaleString());
                systemLog.setOperation("login");
                systemLog.setLoginLogId(loginLogId);
                indexServiceImpl.insertSystemLog(systemLog);
                return "success";
            } else {
                return "密码错误";
            }
        } else {
            return "用户不存在";
        }
    }

    /**
     * 注册
     */
    @RequestMapping("/toregist")
    @ResponseBody
    public String toregist(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        BrowserInfo browserInfo = new BrowserInfo();
        browserInfo.setSystem(userInfo.getBrowserInfo()[0]);
        browserInfo.setBrowserType(userInfo.getBrowserInfo()[1]);
        browserInfo.setBrowserVersion(userInfo.getBrowserInfo()[2]);
        indexServiceImpl.insertBrowserInfo(browserInfo);
        String browserInfoId = browserInfo.getBrowserInfoId();
        Location location = new Location();
        location.setIp(userInfo.getLocation()[0]);
        location.setLocation(userInfo.getLocation()[1]);
        location.setLocalIp(userInfo.getLocalIp());
        location.setX(userInfo.getLocation()[2]);
        location.setY(userInfo.getLocation()[3]);
        location.setKeyword(userInfo.getPcOrPhone());
        indexServiceImpl.insertLocation(location);
        String locationId = location.getLocationId();
        User user = userInfo.getUser();
        user.setCreatetime(new Date().toLocaleString());
        user.setBrowserInfoId(browserInfoId);
        user.setLocationId(locationId);
        int i = indexServiceImpl.insertUser(user);
        if (i == 1) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(new Date().toLocaleString());
            systemLog.setOperation("regist");
            systemLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertSystemLog(systemLog);
            return "success";
        }
        return null;
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
            systemLog.setCreatetime(new Date().toLocaleString());
            systemLog.setOperation("index");
            systemLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertSystemLog(systemLog);
            model.addAttribute("msg", "你好," + user.getUsername());
            return "index";
        } else {
            redirectAttributes.addFlashAttribute("msg", "请登录");
            return "redirect:login";
        }

    }

    /**
     * 重定向进入首页
     */
    @RequestMapping("/")
    public String index() {
        return "redirect:index";

    }

    /**
     * ajax实时搜索
     */
    @RequestMapping("/searchItem")
    @ResponseBody
    public Object searchItem(String keyword, HttpServletResponse res) throws IOException {
        if (!keyword.isEmpty()) {
            List<Item> items = indexServiceImpl.searchItem(keyword);
            return items;
        }
        return null;
    }

    /**
     * 进入命令模式
     */
    @RequestMapping("/command")
    @ResponseBody
    public String command(@RequestBody Info info, HttpServletRequest request) throws IOException {
        String[] locationArr = info.getLocation();
        String[] browserInfoArr = info.getBrowserInfo();
        String pcOrPhone = info.getPcOrPhone();
        BrowserInfo bi = new BrowserInfo();
        Location lo = new Location();
        bi.setSystem(browserInfoArr[0]);
        bi.setBrowserType(browserInfoArr[1]);
        bi.setBrowserVersion(browserInfoArr[2]);
        lo.setIp(locationArr[0]);
        lo.setLocation(locationArr[1]);
        lo.setX(locationArr[2]);
        lo.setY(locationArr[3]);
        lo.setKeyword(pcOrPhone);
        lo.setLocalIp(info.getLocalIp());
        indexServiceImpl.insertBrowserInfo(bi);
        indexServiceImpl.insertLocation(lo);
        String browserInfoId = bi.getBrowserInfoId();
        String locationId = lo.getLocationId();

        User user = (User) request.getSession().getAttribute("user");
        if (info.getKeyword().equals("logout")) {
            LogoutLog logoutLog = new LogoutLog();
            logoutLog.setCreatetime(new Date().toLocaleString());
            logoutLog.setBrowserInfoId(browserInfoId);
            logoutLog.setLocationId(locationId);
            logoutLog.setUserId(user.getId());
            indexServiceImpl.insertLogoutLog(logoutLog);
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(new Date().toLocaleString());
            systemLog.setOperation("logout");
            systemLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertSystemLog(systemLog);
            request.getSession().invalidate();
            return "login";
        }
        return "其他操作";
    }

    /**
     * 重定向到搜索结果详情页
     */
    @RequestMapping("/todetail")
    public String toDetail(@RequestBody Info info, RedirectAttributes attributes, HttpServletRequest httpServletRequest, HttpServletResponse res) throws IOException {
        String id = httpServletRequest.getSession().getId();

        if (info.getKeyword().equals(":article")) {
            return "redirect:article";
        }
        if (!info.getKeyword().isEmpty()) {
            /**125.84.181.44,重庆市重庆市,29.56471,106.55073
             windows,chrome,74.0.3729.131*/
            String[] locationArr = info.getLocation();
            String[] browserInfoArr = info.getBrowserInfo();
            String pcOrPhone = info.getPcOrPhone();
            BrowserInfo bi = new BrowserInfo();
            Location lo = new Location();
            bi.setSystem(browserInfoArr[0]);
            bi.setBrowserType(browserInfoArr[1]);
            bi.setBrowserVersion(browserInfoArr[2]);
            lo.setIp(locationArr[0]);
            lo.setLocation(locationArr[1]);
            lo.setX(locationArr[2]);
            lo.setY(locationArr[3]);
            lo.setKeyword(pcOrPhone);
            lo.setLocalIp(info.getLocalIp());
            indexServiceImpl.insertBrowserInfo(bi);
            indexServiceImpl.insertLocation(lo);


            SearchRecord searchRecord = new SearchRecord();
            searchRecord.setBrowserInfoId(bi.getBrowserInfoId());
            searchRecord.setLocationId(lo.getLocationId());
            searchRecord.setSearchTime(new Date().toLocaleString());
            searchRecord.setKeyword(info.getKeyword());
            indexServiceImpl.insertSearchRecord(searchRecord);
            Integer loginLogId = (Integer) httpServletRequest.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(new Date().toLocaleString());
            //这儿不光记录了操作还记录了本次查询的关键字
            systemLog.setOperation("todetail" + "?keyword=" + info.getKeyword());
            systemLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertSystemLog(systemLog);
            List<Item> items = indexServiceImpl.searchItem(info.getKeyword());
            attributes.addFlashAttribute(id + "-" + "items1", items);
            /**这里不是很懂,不重定向会报错,重定向,前端页面不主动+window.location跳转也不生效,这儿必须前后端配合,缺一不可*/
            return "redirect:detail";
        }
        return null;

    }

    /**
     * 进入搜索结果详情页
     */
    @RequestMapping("/detail")
    public ModelAndView detail(HttpServletRequest request, ModelAndView modelAndView, HttpServletRequest
            httpServletRequest, HttpServletResponse res) throws IOException {

        String id = httpServletRequest.getSession().getId();
        Map<String, ?> maps = RequestContextUtils.getInputFlashMap(request);
        List<Item> list = null;
        if (maps != null) {
            list = (List<Item>) maps.get(id + "-" + "items1");
        }

        modelAndView.setViewName("detail");

        if (list != null) {
            map.put(id + "-" + "items1", list);
            modelAndView.addObject("items", list);
            return modelAndView;
        } else {
            List<Item> lists = (List<Item>) map.get(id + "-" + "items1");
            modelAndView.addObject("items", lists);
            return modelAndView;

        }
    }

    /**
     * 便签模式
     */
    @RequestMapping("/note")
    @ResponseBody
    public String note(@RequestBody Info info, HttpServletRequest request) {
        String[] locationArr = info.getLocation();
        String[] browserInfoArr = info.getBrowserInfo();
        String pcOrPhone = info.getPcOrPhone();
        BrowserInfo bi = new BrowserInfo();
        Location lo = new Location();
        bi.setSystem(browserInfoArr[0]);
        bi.setBrowserType(browserInfoArr[1]);
        bi.setBrowserVersion(browserInfoArr[2]);
        lo.setIp(locationArr[0]);
        lo.setLocation(locationArr[1]);
        lo.setX(locationArr[2]);
        lo.setY(locationArr[3]);
        lo.setKeyword(pcOrPhone);
        lo.setLocalIp(info.getLocalIp());

        indexServiceImpl.insertBrowserInfo(bi);
        indexServiceImpl.insertLocation(lo);

        AiNote aiNote = new AiNote();
        aiNote.setContent(info.getKeyword());
        aiNote.setBrowserInfoId(bi.getBrowserInfoId());
        aiNote.setLocationId(lo.getLocationId());
        aiNote.setCreatetime(new Date().toLocaleString());
        //这儿明显冗余了,上面记录浏览器和位置,下面的登录日志id中都有了这些信息,记录两遍干啥子,后面重构的时候,处理下
        aiNote.setLoginLogId((Integer) request.getSession().getAttribute("loginLogId"));
        Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(new Date().toLocaleString());
        systemLog.setOperation("note" + "?content=" + aiNote.getContent());
        systemLog.setLoginLogId(loginLogId);
        indexServiceImpl.insertSystemLog(systemLog);
        indexServiceImpl.insertAiNote(aiNote);
        return info.getKeyword();
    }

    /**
     * 随机csdn文章功能
     */
    @RequestMapping("/article")
    public ModelAndView article(HttpServletRequest request, ModelAndView modelAndView, HttpServletRequest
            httpServletRequest, HttpServletResponse res) throws IOException {
        Article article = new Article();
        for (; ; ) {
            Random r = new Random();
            int id = r.nextInt(101268) + 1;
            article = indexServiceImpl.search(id);
            if (article != null) {
                Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
                SystemLog systemLog = new SystemLog();
                systemLog.setCreatetime(new Date().toLocaleString());
                systemLog.setOperation("article" + "?id=" + article.getId());
                systemLog.setLoginLogId(loginLogId);
                indexServiceImpl.insertSystemLog(systemLog);
                break;
            }
        }
        modelAndView.setViewName("article");
        modelAndView.addObject("article", article);
        return modelAndView;
    }

    /**
     * 搜索记录详情列表
     */
    @RequestMapping("/list")
    public String list(Model model, HttpServletRequest request) {
        Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(new Date().toLocaleString());
        systemLog.setOperation("list");
        systemLog.setLoginLogId(loginLogId);
        indexServiceImpl.insertSystemLog(systemLog);
        List<SearchRecordLocation> searchRecordLocation = indexServiceImpl.selectSearchRecordLocation();
        model.addAttribute("items", searchRecordLocation);
        return "list";
    }

    /**
     * 便签记录详情列表
     */
    @RequestMapping("/ainote")
    public String aiNotelist(Model model, HttpServletRequest request) {
        Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(new Date().toLocaleString());
        systemLog.setOperation("ainote");
        systemLog.setLoginLogId(loginLogId);
        indexServiceImpl.insertSystemLog(systemLog);
        List<AiNoteLocation> aiNoteLocation = indexServiceImpl.selectAiNoteLocation();
        model.addAttribute("items", aiNoteLocation);
        return "aiNoteList";
    }

    /**
     * 注册用户列表
     */
    @RequestMapping("/userlist")
    public String userList(Model model, HttpServletRequest request) {
        Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(new Date().toLocaleString());
        systemLog.setOperation("userlist");
        systemLog.setLoginLogId(loginLogId);
        indexServiceImpl.insertSystemLog(systemLog);
        List<UserLocation> userLocations = indexServiceImpl.selectUserLocation();
        model.addAttribute("items", userLocations);
        return "userlist";
    }

    /**
     * 登录日志列表
     */
    @RequestMapping("/loginloglist")
    public String loginLogList(Model model, HttpServletRequest request) {
        Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(new Date().toLocaleString());
        systemLog.setOperation("loginloglist");
        systemLog.setLoginLogId(loginLogId);
        indexServiceImpl.insertSystemLog(systemLog);
        List<LoginLogLocation> loginLogLocation = indexServiceImpl.selectLoginLocation();
        model.addAttribute("items", loginLogLocation);
        return "loginloglist";
    }

    /**
     * 退出日志列表
     */
    @RequestMapping("/logoutloglist")
    public String logoutLogList(Model model, HttpServletRequest request) {
        Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(new Date().toLocaleString());
        systemLog.setOperation("logoutloglist");
        systemLog.setLoginLogId(loginLogId);
        indexServiceImpl.insertSystemLog(systemLog);
        List<LogoutLogLocation> logoutLogLocation = indexServiceImpl.selectLogoutLocation();
        model.addAttribute("items", logoutLogLocation);
        return "logoutloglist";
    }


    @RequestMapping("/todetail2")
    public String toDetail2(String keyword, RedirectAttributes attributes) {
        if (!keyword.isEmpty()) {
            SearchRecord searchRecord = new SearchRecord();
            searchRecord.setSearchTime(new Date().toLocaleString());
            searchRecord.setKeyword(keyword);
            indexServiceImpl.insertSearchRecord(searchRecord);
            List<Item> items = indexServiceImpl.searchItem(keyword);

            attributes.addFlashAttribute("items2", items);
            return "redirect:detail2";
        }
        return null;
    }

    @RequestMapping("/todetail3")
    public String toDetai3(String keyword, RedirectAttributes attributes) {
        if (!keyword.isEmpty()) {
            map.put("keyword", keyword);
            return "redirect:detail3";
        }
        return null;
    }

    @RequestMapping("/detail2")
    public ModelAndView detail2(HttpServletRequest request, ModelAndView modelAndView) {
        Map<String, ?> maps = RequestContextUtils.getInputFlashMap(request);
        List<Item> list = null;
        if (maps != null) {
            list = (List<Item>) maps.get("items2");
        }
        modelAndView.setViewName("detail2");
        if (list != null) {
            map.put("items2", list);
            modelAndView.addObject("items", list);
            return modelAndView;
        } else {
            List<Item> lists = (List<Item>) map.get("items2");
            modelAndView.addObject("items", lists);
            return modelAndView;
        }
    }

    @RequestMapping("/detail3json")
    @ResponseBody
    public List<Item> detail3() {
        if (map.get("items3") == null) {
            SearchRecord searchRecord = new SearchRecord();
            searchRecord.setSearchTime(new Date().toLocaleString());
            searchRecord.setKeyword((String) map.get("keyword"));
            indexServiceImpl.insertSearchRecord(searchRecord);
            List<Item> items = indexServiceImpl.searchItem((String) map.get("keyword"));
            map.put("items3", items);
            return items;
        } else {
            return (List<Item>) map.get("items3");
        }
    }

}

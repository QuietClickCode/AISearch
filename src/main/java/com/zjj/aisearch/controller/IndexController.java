package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.*;
import com.zjj.aisearch.service.IndexService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "登录方法")
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
    public Object command(String keyword, HttpServletRequest request) throws IOException {
        int index = keyword.indexOf(" ");
        if (index != -1) {
            String substring = keyword.substring(0, index);
            String title = keyword.substring(index + 1);
            if (substring.equals("js")) {
                List<JianShuArticle> jianShuArticles = indexServiceImpl.searchJianShuArticle(title);
                return jianShuArticles;
            }
            if (substring.equals("zh")) {
                List<ZhiHuArticle> zhiHuArticles = indexServiceImpl.searchZhiHuArticle(title);

                return zhiHuArticles;
            }
            if (substring.equals("csdn")) {
                List<Article> Articles = indexServiceImpl.searchArticle(title);

                return Articles;
            }
        }
        return "其他操作";
    }

    /**
     * 定向搜索结果详情
     */
    @RequestMapping("/iscommand")
    public String isCommand(String keyword, RedirectAttributes attributes, HttpServletRequest httpServletRequest, HttpServletResponse res) throws IOException {
        Integer loginLogId = (Integer) httpServletRequest.getSession().getAttribute("loginLogId");
        SystemLog systemLog = new SystemLog();
        String id = httpServletRequest.getSession().getId();
        int index = keyword.indexOf(" ");
        if (index != -1) {
            String substring = keyword.substring(0, index);
            String title = keyword.substring(index + 1);
            if (substring.equals("js")) {
                List<JianShuArticle> jianShuArticles = indexServiceImpl.searchJianShuArticle(title);
                attributes.addFlashAttribute(id + "-" + "items1", jianShuArticles);
                systemLog.setCreatetime(new Date().toLocaleString());
                systemLog.setOperation(":js" + "?keyword=" + title);
                systemLog.setLoginLogId(loginLogId);
                indexServiceImpl.insertSystemLog(systemLog);
                return "redirect:commandlist";
            }
            if (substring.equals("zh")) {
                List<ZhiHuArticle> zhiHuArticles = indexServiceImpl.searchZhiHuArticle(title);
                attributes.addFlashAttribute(id + "-" + "items1", zhiHuArticles);
                systemLog.setCreatetime(new Date().toLocaleString());
                systemLog.setOperation(":zh" + "?keyword=" + title);
                systemLog.setLoginLogId(loginLogId);
                indexServiceImpl.insertSystemLog(systemLog);
                return "redirect:zhihucommandlist";
            }
            if (substring.equals("csdn")) {
                List<Article> Articles = indexServiceImpl.searchArticle(title);
                attributes.addFlashAttribute(id + "-" + "items1", Articles);
                systemLog.setCreatetime(new Date().toLocaleString());
                systemLog.setOperation(":csdn" + "?keyword=" + title);
                systemLog.setLoginLogId(loginLogId);
                indexServiceImpl.insertSystemLog(systemLog);
                return "redirect:commandlist";
            }
        }
        return null;

    }
    /**
     * 进入定向搜索结果详情页
     */
    @RequestMapping("/commandlist")
    public ModelAndView commandlist(HttpServletRequest request, ModelAndView modelAndView, HttpServletRequest
            httpServletRequest, HttpServletResponse res) throws IOException {

        String id = httpServletRequest.getSession().getId();
        Map<String, ?> maps = RequestContextUtils.getInputFlashMap(request);
        List<Item> list = null;
        if (maps != null) {
            list = (List<Item>) maps.get(id + "-" + "items1");
        }
        modelAndView.setViewName("commandlist");
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
     * 进入知乎定向搜索结果详情页
     */
    @RequestMapping("/zhihucommandlist")
    public ModelAndView zhihucommandlist(HttpServletRequest request, ModelAndView modelAndView, HttpServletRequest
            httpServletRequest, HttpServletResponse res) throws IOException {
        String id = httpServletRequest.getSession().getId();
        Map<String, ?> maps = RequestContextUtils.getInputFlashMap(request);
        List<Item> list = null;
        if (maps != null) {
            list = (List<Item>) maps.get(id + "-" + "items1");
        }
        modelAndView.setViewName("zhihucommandlist");
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
        return "ainotelist";
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
    /**
     * 系统操作日志列表
     */
    @RequestMapping("/systemloglist")
    public String systemloglist(Model model, HttpServletRequest request) {
        Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(new Date().toLocaleString());
        systemLog.setOperation("systemloglist");
        systemLog.setLoginLogId(loginLogId);
        indexServiceImpl.insertSystemLog(systemLog);
        List<SystemLogList> systemLogList = indexServiceImpl.selectSystemLogList();
        model.addAttribute("items", systemLogList);
        return "systemLogList";
    }
}

package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.*;
import com.zjj.aisearch.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/***
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-09-21 19:16:26
 **/
@Controller
@Slf4j
@Api(value = "首页", description = "首页")
public class IndexController {

    @Autowired
    private IndexService indexServiceImpl;

    /**
     * 实现转发模式,/index,完美兼容swagger
     */
   /* @RequestMapping(value = "{path}")
    public String del(@PathVariable("path") String path) {
        if (path.equals("swagger-ui.html")) {

            return "/";
        } else {
            return path;
        }
    }*/
    @PostMapping("/test")
    @ResponseBody
    public String test() {
        return "true";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录
     */
    @RequestMapping("/tologin")
    @ResponseBody
    @ApiOperation(value = "login")
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

                log.info("[{}]正在登陆", username);
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
            log.info("[{}]注册成功", user.getUsername());
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
            log.info("[{}]进入首页", user.getUsername());
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
    public Object command(String keyword, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            int index = keyword.indexOf(" ");
            if (index != -1) {
                String substring = keyword.substring(0, index);
                String title = keyword.substring(index + 1);
                if (substring.equals("js")) {
                    log.info("[{}]正在简书搜索[{}]", user.getUsername(), title);
                    List<JianShuArticle> jianShuArticles = indexServiceImpl.searchJianShuArticle(title);
                    return jianShuArticles;
                }
                if (substring.equals("csdn")) {
                    log.info("[{}]正在CSDN搜索[{}]", user.getUsername(), title);
                    List<Article> Articles = indexServiceImpl.searchArticle(title);
                    return Articles;
                }
                if (substring.equals("zh")) {
                    log.info("[{}]正在知乎搜索[{}]", user.getUsername(), title);
                    List<ZhiHuArticle> zhiHuArticles = indexServiceImpl.searchZhiHuArticle(title);
                    return zhiHuArticles;
                }
            }
            return "其他操作";
        } else {
            redirectAttributes.addFlashAttribute("msg", "请登录");
            return "redirect:login";
        }
    }

    /**
     * 定向搜索结果详情
     */
    @RequestMapping("/iscommand")
    public String isCommand(String keyword, RedirectAttributes attributes, HttpServletRequest httpServletRequest, HttpServletResponse res) throws IOException {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) httpServletRequest.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            int index = keyword.indexOf(" ");
            if (index != -1) {
                String substring = keyword.substring(0, index);
                String title = keyword.substring(index + 1);
                httpServletRequest.getSession().setAttribute("title", title);
                if (substring.equals("js")) {
                    httpServletRequest.getSession().setAttribute("command", "js");
                    return null;
                }
                if (substring.equals("zh")) {
                    httpServletRequest.getSession().setAttribute("command", "zh");
                    return null;
                }
                if (substring.equals("csdn")) {
                    httpServletRequest.getSession().setAttribute("command", "csdn");
                    return null;
                }
            }
            return null;
        } else {
            attributes.addFlashAttribute("msg", "请登录");
            return "redirect:login";
        }

    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest, Model model, RedirectAttributes attributes, HttpServletResponse res) throws IOException {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) httpServletRequest.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(new Date().toLocaleString());
            systemLog.setOperation(":logout");
            systemLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertSystemLog(systemLog);
            LogoutLog logoutLog = new LogoutLog();
            logoutLog.setCreatetime(new Date().toLocaleString());
            logoutLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertLogoutLog(logoutLog);
            httpServletRequest.getSession().invalidate();
            attributes.addFlashAttribute("msg", "请登录");
            return "redirect:login";
        } else {
            model.addAttribute("msg", "请登录");
            return "login";
        }
    }

    /**
     * 进入定向搜索结果详情页
     */
    @RequestMapping("/commandlist")
    public ModelAndView commandlist(HttpServletRequest request, ModelAndView modelAndView, HttpServletRequest
            httpServletRequest, HttpServletResponse res) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            String command = (String) httpServletRequest.getSession().getAttribute("command");
            String title = (String) httpServletRequest.getSession().getAttribute("title");
            Integer loginLogId = (int) httpServletRequest.getSession().getAttribute("loginLogId");
            if (command.equals("csdn")) {
                List<Article> Articles = indexServiceImpl.searchArticle(title);
                SystemLog systemLog = new SystemLog();
                systemLog.setCreatetime(new Date().toLocaleString());
                systemLog.setOperation(":csdn" + "?keyword=" + title);
                systemLog.setLoginLogId(loginLogId);
                indexServiceImpl.insertSystemLog(systemLog);
                modelAndView.setViewName("commandlist");
                modelAndView.addObject("items", Articles);
                return modelAndView;
            }
            if (command.equals("js")) {
                List<JianShuArticle> jianShuArticles = indexServiceImpl.searchJianShuArticle(title);
                SystemLog systemLog = new SystemLog();
                systemLog.setCreatetime(new Date().toLocaleString());
                systemLog.setOperation(":js" + "?keyword=" + title);
                systemLog.setLoginLogId(loginLogId);
                indexServiceImpl.insertSystemLog(systemLog);
                modelAndView.setViewName("commandlist");
                modelAndView.addObject("items", jianShuArticles);
                return modelAndView;
            }
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("msg", "请登录");
            return modelAndView;
        }
        return null;
    }


    /**
     * 进入知乎定向搜索结果详情页
     */
    @RequestMapping("/zhihucommandlist")
    public ModelAndView zhihucommandlist(HttpServletRequest request, ModelAndView modelAndView, HttpServletRequest
            httpServletRequest, HttpServletResponse res) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            String command = (String) httpServletRequest.getSession().getAttribute("command");
            String title = (String) httpServletRequest.getSession().getAttribute("title");
            Integer loginLogId = (int) httpServletRequest.getSession().getAttribute("loginLogId");
            if (command.equals("zh")) {
                List<ZhiHuArticle> zhiHuArticles = indexServiceImpl.searchZhiHuArticle(title);
                SystemLog systemLog = new SystemLog();
                systemLog.setCreatetime(new Date().toLocaleString());
                systemLog.setOperation(":zh" + "?keyword=" + title);
                systemLog.setLoginLogId(loginLogId);
                indexServiceImpl.insertSystemLog(systemLog);
                modelAndView.setViewName("zhihucommandlist");
                modelAndView.addObject("items", zhiHuArticles);
                return modelAndView;
            }
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("msg", "请登录");
            return modelAndView;
        }
        return null;
    }

    /**
     * 重定向到搜索结果详情页
     */
    @RequestMapping("/todetail")
    public String toDetail(@RequestBody Info info, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse res) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (!info.getKeyword().isEmpty()) {
                request.getSession().setAttribute("keyword", info.getKeyword());
                //不做任何事,避免生成两次记录
                return null;
            }
            return null;
        } else {
            redirectAttributes.addFlashAttribute("msg", "请登录");
            return "redirect:login";
        }
    }


    /**
     * 进入搜索结果详情页
     */
    @RequestMapping("/detail")
    public ModelAndView detail(HttpServletRequest request, ModelAndView modelAndView, HttpServletRequest
            httpServletRequest, HttpServletResponse res) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            String keyword = (String) httpServletRequest.getSession().getAttribute("keyword");
            List<Item> items = indexServiceImpl.searchItem(keyword);
            modelAndView.addObject("items", items);
            modelAndView.setViewName("/detail");
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(new Date().toLocaleString());
            systemLog.setOperation("detail" + "?keyword=" + keyword);
            systemLog.setLoginLogId(loginLogId);
            SearchRecord searchRecord = new SearchRecord();
            searchRecord.setKeyword(keyword);
            searchRecord.setSearchTime(new Date().toLocaleString());
            searchRecord.setLoginLogId(loginLogId);
            indexServiceImpl.insertSearchRecord(searchRecord);
            indexServiceImpl.insertSystemLog(systemLog);
            return modelAndView;
        } else {
            modelAndView.addObject("msg", "请登录");
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }

    /**
     * 便签模式
     */
    @RequestMapping("/note")
    @ResponseBody
    public String note(@RequestBody Info info, HttpServletRequest request, ModelAndView modelAndView) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            AiNote aiNote = new AiNote();
            aiNote.setContent(info.getKeyword());
            aiNote.setCreatetime(new Date().toLocaleString());
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            aiNote.setLoginLogId(loginLogId);
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(new Date().toLocaleString());
            systemLog.setOperation("note" + "?content=" + aiNote.getContent());
            systemLog.setLoginLogId(loginLogId);
            indexServiceImpl.insertSystemLog(systemLog);
            indexServiceImpl.insertAiNote(aiNote);
            return info.getKeyword();
        } else {
            return "nologin";
        }
    }

    /**
     * 随机csdn文章功能
     */
    @RequestMapping("/article")
    public ModelAndView article(HttpServletRequest request, ModelAndView modelAndView, HttpServletRequest
            httpServletRequest, HttpServletResponse res) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
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
        } else {
            modelAndView.addObject("msg", "请登录");
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }

    /**
     * 搜索记录详情列表
     */
    @RequestMapping("/list")
    public String list(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(new Date().toLocaleString());
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
    @RequestMapping("/ainote")
    public String aiNotelist(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(new Date().toLocaleString());
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
    @RequestMapping("/editorlist")
    public String editorList(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(new Date().toLocaleString());
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
    @RequestMapping("/markdownlist")
    public String markdownList(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(new Date().toLocaleString());
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

    /**
     * 注册用户列表
     */
    @RequestMapping("/userlist")
    public String userList(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(new Date().toLocaleString());
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
    @RequestMapping("/loginloglist")
    public String loginLogList(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(new Date().toLocaleString());
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
     * 退出日志列表
     */
    @RequestMapping("/logoutloglist")
    public String logoutLogList(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(new Date().toLocaleString());
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
    @RequestMapping("/systemloglist")
    public String systemloglist(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer loginLogId = (Integer) request.getSession().getAttribute("loginLogId");
            SystemLog systemLog = new SystemLog();
            systemLog.setCreatetime(new Date().toLocaleString());
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
}

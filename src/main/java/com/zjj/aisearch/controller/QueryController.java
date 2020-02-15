package com.zjj.aisearch.controller;

import com.alibaba.fastjson.JSONArray;
import com.zjj.aisearch.config.ConfigBean;
import com.zjj.aisearch.model.*;
import com.zjj.aisearch.service.QueryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: AISearch
 * @description: 查询
 * @author: zjj
 * @create: 2019-10-05 02:46:49
 **/
@RestController
@Slf4j
public class QueryController {

    @Autowired
    private QueryService queryServiceImpl;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    //配置文件
    @Autowired
    ConfigBean configBean;

    @PostMapping("querySystemLog")
    @RequiresPermissions("user:systemloglist")
    @ApiOperation("根据条件查询系统操作日志数据")
    public List<SystemLogList> querySystemLog(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryForm(queryForm);
    }

    @PostMapping("queryCount")
    @ApiOperation("根据条件查询系统操作日志数据总条数")
    public Integer queryCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryCount(queryForm);
        return count;
    }

    @PostMapping("queryAiNote")
    @ApiOperation("根据条件查询便签记录数据")
    public List<SystemLogList> queryAiNote(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryAiNote(queryForm);
    }

    @PostMapping("queryAiNoteCount")
    @ApiOperation("根据条件查询便签记录数据总条数")
    public Integer queryAiNoteCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryAiNoteCount(queryForm);
        return count;
    }

    @PostMapping("queryEditor")
    @ApiOperation("根据条件查询editor记录数据")
    public List<EditorList> queryEditor(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryEditor(queryForm);
    }

    @PostMapping("queryEditorCount")
    @ApiOperation("根据条件查询editor记录数据总条数")
    public Integer queryEditorCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryEditorCount(queryForm);
        return count;
    }

    @PostMapping("queryList")
    @ApiOperation("根据条件查询搜索记录数据")
    public List<SystemLogList> queryList(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryList(queryForm);
    }

    @PostMapping("queryListCount")
    @ApiOperation("根据条件查询搜索记录数据总条数")
    public Integer queryListCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryListCount(queryForm);
        return count;
    }

    @PostMapping("queryLoginLogList")
    @ApiOperation("根据条件查询登录日志记录数据")
    public List<SystemLogList> queryLoginLogList(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryLoginLogList(queryForm);
    }

    @PostMapping("queryLoginLogListCount")
    @ApiOperation("根据条件查询登录日志记录数据总条数")
    public Integer queryLoginLogListCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryLoginLogListCount(queryForm);
        return count;
    }

    @PostMapping("queryLogoutLogList")
    @ApiOperation("根据条件查询退出日志记录数据")
    public List<SystemLogList> queryLogoutLogList(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryLogoutLogList(queryForm);
    }

    @PostMapping("queryLogoutLogListCount")
    @ApiOperation("根据条件查询退出日志记录数据总条数")
    public Integer queryLogoutLogListCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryLogoutLogListCount(queryForm);
        return count;
    }

    @PostMapping("queryMarkdownList")
    @ApiOperation("根据条件查询Markdown日志记录数据")
    public List<SystemLogList> queryMarkdownList(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryMarkdownList(queryForm);
    }

    @PostMapping("queryMarkdownListCount")
    @ApiOperation("根据条件查询Markdown日志记录数据总条数")
    public Integer queryMarkdownListCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryMarkdownListCount(queryForm);
        return count;
    }

    @PostMapping("queryUserList")
    @ApiOperation("根据条件查询用户记录数据")
    public List<SystemLogList> queryUserList(@RequestBody QueryForm queryForm) {
        return queryServiceImpl.queryUserList(queryForm);
    }
    @PostMapping("queryFileList")
    @ApiOperation("根据条件查询用户记录数据")
    public List<FullTextFile> queryFileList() {
        return queryServiceImpl.queryFileList();
    }

    @PostMapping("queryUserListCount")
    @ApiOperation("根据条件查询用户记录数据总条数")
    public Integer queryUserListCount(@RequestBody QueryForm queryForm) {
        Integer count = queryServiceImpl.queryUserListCount(queryForm);
        return count;
    }

    @PostMapping("querySystem")
    public List<String> querySystem() {
        String cache = stringRedisTemplate.opsForValue().get("SystemCache");
        if (StringUtils.isNotBlank(cache)) {
            List<String> cacheReuslt = JSONArray.parseArray(stringRedisTemplate.opsForValue().get("SystemCache"), String.class);
            log.info("querySystem走缓存");
        }
        List<String> strings = queryServiceImpl.querySystem();
        //结果存缓存
        stringRedisTemplate.opsForValue().set("SystemCache", JSONArray.toJSON(strings).toString(),configBean.getITEM_CACHE_EXPIRE().intValue(), TimeUnit.SECONDS);
        return strings;
    }

    @PostMapping("queryDevice")
    public List<String> queryDevice() {
        String cache = stringRedisTemplate.opsForValue().get("DeviceCache");
        if (StringUtils.isNotBlank(cache)) {
            List<String> cacheReuslt = JSONArray.parseArray(stringRedisTemplate.opsForValue().get("DeviceCache"), String.class);
            log.info("queryDevice走缓存");
        }
        List<String> strings = queryServiceImpl.queryDevice();
        //结果存缓存
        stringRedisTemplate.opsForValue().set("DeviceCache", JSONArray.toJSON(strings).toString(),configBean.getITEM_CACHE_EXPIRE().intValue(), TimeUnit.SECONDS);
        return strings;
    }

    @PostMapping("queryBrowser")
    public List<String> queryBrowser() {
        String cache = stringRedisTemplate.opsForValue().get(configBean.getREDIS_ITEM_PRE());
        if (StringUtils.isNotBlank(cache)) {
            List<String> cacheReuslt = JSONArray.parseArray(stringRedisTemplate.opsForValue().get(configBean.getREDIS_ITEM_PRE()), String.class);
            log.info("queryBrowser走缓存");
            return cacheReuslt;
        }
        List<String> strings = queryServiceImpl.queryBrowser();
        //结果存缓存
        stringRedisTemplate.opsForValue().set(configBean.getREDIS_ITEM_PRE(), JSONArray.toJSON(strings).toString(),configBean.getITEM_CACHE_EXPIRE().intValue(), TimeUnit.SECONDS);
        return strings;
    }

    /**
     * 查询是否登录
     */
    @PostMapping("/islogin")
    public ResponseResult islogin(@RequestBody Map<String, String> map) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        ResponseResult responseResult = new ResponseResult();
        if (user == null) {
            responseResult.setMsg("请登录");
            responseResult.setStatus(-1);
            return responseResult;
        } else {
            responseResult.setMsg(map.get("path"));
            responseResult.setStatus(0);
            return responseResult;
        }
    }

    /**
     * 判断是否有查看系统操作日志列表的权限
     */
    @PostMapping("/issystemloglistpermission")
    @RequiresPermissions("user:systemloglist")
    public ResponseResult systemloglist() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatus(0);
        return responseResult;
    }
}

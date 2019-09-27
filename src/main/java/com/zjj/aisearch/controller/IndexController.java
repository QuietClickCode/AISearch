package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.*;
import com.zjj.aisearch.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-09-21 19:16:26
 **/
@Controller
public class IndexController {

    @Autowired
    private IndexService indexServiceImpl;

    private HashMap<String, Object> map = new HashMap<>();

    @RequestMapping("/search")
    @ResponseBody
    public List<Article> search(String keyword) {
        List<Article> articles = indexServiceImpl.search(keyword);
        return articles;

    }

    @RequestMapping("/searchItem")
    @ResponseBody
    public List<Item> searchItem(String keyword) {
        if (!keyword.isEmpty()) {
            List<Item> items = indexServiceImpl.searchItem(keyword);
            return items;
        }
        return null;

    }

    @RequestMapping("/todetail")
    public String toDetail(String keyword, String location, String browserInfo, RedirectAttributes attributes) {
        if (!keyword.isEmpty()) {
            /*125.84.181.44,重庆市重庆市,29.56471,106.55073
                    windows,chrome,74.0.3729.131*/
            String[] locationArr = location.split(",");
            String[] browserInfoArr = browserInfo.split(",");
            BrowserInfo bi = new BrowserInfo();
            Location lo = new Location();
            bi.setSystem(browserInfoArr[0]);
            bi.setBrowserType(browserInfoArr[1]);
            bi.setBrowserVersion(browserInfoArr[2]);
            lo.setIp(locationArr[0]);
            lo.setLocation(locationArr[1]);
            lo.setX(locationArr[2]);
            lo.setY(locationArr[3]);

            indexServiceImpl.insertBrowserInfo(bi);
            indexServiceImpl.insertLocation(lo);
            SearchRecord searchRecord = new SearchRecord();
            searchRecord.setBrowserInfoId(bi.getBrowserInfoId());
            searchRecord.setLocationId(lo.getLocationId());
            searchRecord.setSearchTime(new Date().toLocaleString());
            searchRecord.setKeyword(keyword);
            indexServiceImpl.insertSearchRecord(searchRecord);
            List<Item> items = indexServiceImpl.searchItem(keyword);
            attributes.addFlashAttribute("items1", items);
            return "redirect:detail";
        }
        return null;
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


    @RequestMapping("/detail")
    public ModelAndView detail(HttpServletRequest request, ModelAndView modelAndView) {

        Map<String, ?> maps = RequestContextUtils.getInputFlashMap(request);
        List<Item> list = null;
        if (maps != null) {
            list = (List<Item>) maps.get("items1");
        }

        modelAndView.setViewName("detail");

        if (list != null) {
            map.put("items1", list);
            modelAndView.addObject("items", list);
            return modelAndView;
        } else {
            List<Item> lists = (List<Item>) map.get("items1");
            modelAndView.addObject("items", lists);
            return modelAndView;

        }


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


    @RequestMapping(value = "{path}")
    public String del(@PathVariable("path") String path) {
        return path;
    }


}

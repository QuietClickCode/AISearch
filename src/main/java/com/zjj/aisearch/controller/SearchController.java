package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.ResponseResult;
import com.zjj.aisearch.pojo.dto.BKYArticleDTO;
import com.zjj.aisearch.pojo.dto.CSDNArticleDTO;
import com.zjj.aisearch.pojo.dto.DouBanMovieDTO;
import com.zjj.aisearch.pojo.dto.JianShuArticleDTO;
import com.zjj.aisearch.pojo.entity.Page;
import com.zjj.aisearch.repository.IBKYArticleRepository;
import com.zjj.aisearch.repository.ICSDNArticleRepository;
import com.zjj.aisearch.repository.IDouBanMovieRepository;
import com.zjj.aisearch.repository.IJianShuArticleRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class SearchController {

    @Autowired
    private IDouBanMovieRepository movieRepository;
    @Autowired
    private IJianShuArticleRepository jianshuRepository;
    @Autowired
    private IBKYArticleRepository bkyRepository;
    @Autowired
    private ICSDNArticleRepository csdnRepository;

    /*@GetMapping("/")
    public String index(Model model) {
        QueryDTO queryDTO = QueryDTO.builder().minScore(7.5f).orderBy("release_date").build();
        Page<DouBanMovieDTO> page = movieRepository.query(queryDTO, 1, 6);
        List<String> recommendWord = Collections.emptyList();
        if (page != null) {
            recommendWord = page.getList().stream().map(DouBanMovieDTO::getRecommendWord).collect(toList());
        }
        model.addAttribute("recommendWord", recommendWord);
        return "index";
    }*/

    @RequestMapping("/s")
    @ResponseBody
    @ApiOperation("elasticsearch豆瓣电影搜索")
    public Object search(
            @RequestParam("wd") String queryString,
            @RequestParam(value = "pn", required = false, defaultValue = "1") int pageNo,
            Model model
    ) {
        log.info("搜索参数wd:{},pn:{}", queryString, pageNo);
        Page<DouBanMovieDTO> page = movieRepository.query(queryString, pageNo, 10);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatus(0);
        responseResult.setData(page);
        responseResult.setMsg(queryString);
        return responseResult;
    }

    @RequestMapping("/searchjianshu")
    @ResponseBody
    @ApiOperation("elasticsearch简书搜索")
    public Object searchJianShu(
            @RequestParam("wd") String queryString,
            @RequestParam(value = "pn", required = false, defaultValue = "1") int pageNo,
            Model model
    ) {
        log.info("搜索参数wd:{},pn:{}", queryString, pageNo);
        Page<JianShuArticleDTO> page = jianshuRepository.query(queryString, pageNo, 10);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatus(0);
        responseResult.setData(page);
        responseResult.setMsg(queryString);
        return responseResult;
    }
    @RequestMapping("/searchbky")
    @ResponseBody
    @ApiOperation("elasticsearch简书搜索")
    public Object searchBKY(
            @RequestParam("wd") String queryString,
            @RequestParam(value = "pn", required = false, defaultValue = "1") int pageNo,
            Model model
    ) {
        log.info("搜索参数wd:{},pn:{}", queryString, pageNo);
        Page<BKYArticleDTO> page = bkyRepository.query(queryString, pageNo, 10);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatus(0);
        responseResult.setData(page);
        responseResult.setMsg(queryString);
        return responseResult;
    }
    @RequestMapping("/searchcsdn")
    @ResponseBody
    @ApiOperation("elasticsearch简书搜索")
    public Object searchCSDN(
            @RequestParam("wd") String queryString,
            @RequestParam(value = "pn", required = false, defaultValue = "1") int pageNo,
            Model model
    ) {
        log.info("搜索参数wd:{},pn:{}", queryString, pageNo);
        Page<CSDNArticleDTO> page = csdnRepository.query(queryString, pageNo, 10);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatus(0);
        responseResult.setData(page);
        responseResult.setMsg(queryString);
        return responseResult;
    }

   /* @GetMapping("/detail/{id}")
    public String detail(
            @PathVariable("id") String id,
            Model model
    ) {
        DouBanMovieDTO movie = movieRepository.get(id);
        System.out.println(movie.toString());
        model.addAttribute("movie", movie);
        return "detail";
    }*/
}

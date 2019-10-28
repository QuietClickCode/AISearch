package com.zjj.aisearch.controller;

import com.zjj.aisearch.model.ResponseResult;
import com.zjj.aisearch.pojo.dto.DouBanMovieDTO;
import com.zjj.aisearch.pojo.entity.Page;
import com.zjj.aisearch.pojo.entity.QueryDTO;
import com.zjj.aisearch.repository.IDouBanMovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
@Slf4j
public class SearchController {

    @Autowired
    private IDouBanMovieRepository movieRepository;

    @GetMapping("/")
    public String index(Model model) {
        QueryDTO queryDTO = QueryDTO.builder().minScore(7.5f).orderBy("release_date").build();
        Page<DouBanMovieDTO> page = movieRepository.query(queryDTO, 1, 6);
        List<String> recommendWord = Collections.emptyList();
        if (page != null) {
            recommendWord = page.getList().stream().map(DouBanMovieDTO::getRecommendWord).collect(toList());
        }
        model.addAttribute("recommendWord", recommendWord);
        return "index";
    }

    @RequestMapping("/s")
    @ResponseBody
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

    @GetMapping("/detail/{id}")
    public String detail(
            @PathVariable("id") String id,
            Model model
    ) {
        DouBanMovieDTO movie = movieRepository.get(id);
        System.out.println(movie.toString());
        model.addAttribute("movie", movie);
        return "detail";
    }
}

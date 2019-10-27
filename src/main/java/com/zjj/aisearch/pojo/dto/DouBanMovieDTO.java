package com.zjj.aisearch.pojo.dto;

import io.searchbox.annotations.JestId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author 苏雄伟 [suxiongwei@kaoshixing.com]
 * @description
 * @date 2019-09-05 14:29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DouBanMovieDTO {
    private List<String> rating ;

    private int rank;

    private String cover_url;

    private boolean is_playable;

    @JestId
    private String id;

    private List<String> types ;

    private List<String> regions ;

    private String title;

    private String url;

    private String release_date;

    private int actor_count;

    private int vote_count;

    private String score;

    private List<String> actors ;

    private boolean is_watched;

    public String getRecommendWord() {
        String word = getTitle();
        String title = getTitle();
        if (!StringUtils.isEmpty(title) && title.contains("《") && title.contains("》")) {
            word = title.substring(title.indexOf('《') + 1, title.indexOf('》'));
        }
        return word;
    }
}

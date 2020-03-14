package com.zjj.aisearch.crawler;

import org.springframework.web.client.RestTemplate;

public class AISearchCrawlerDemo {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        //System.out.println(restTemplate.getForObject("https://Movie.douban.com/j/new_search_subjects?sort=U&range=0,10&tags=&start=1", String.class));
        for (int i = 1; i < 100; i++) {

            System.out.println(restTemplate.getForObject("https://www.zhihu.com/api/v4/questions/273982054/answers?include=data[*].is_normal,admin_closed_comment,reward_info,is_collapsed,annotation_action,annotation_detail,collapse_reason,is_sticky,collapsed_by,suggest_edit,comment_count,can_comment,content,editable_content,voteup_count,reshipment_settings,comment_permission,created_time,updated_time,review_info,relevant_info,question.detail,excerpt,relationship.is_authorized,is_author,voting,is_thanked,is_nothelp,is_labeled,is_recognized;data[*].mark_infos[*].url;data[*].author.follower_count,badge[*].topics&limit=5&offset=" + i + "&platform=desktop&sort_by=default", String.class));
        }
    }
}
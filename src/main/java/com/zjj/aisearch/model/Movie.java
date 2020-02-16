package com.zjj.aisearch.model;

import lombok.Data;

/*等价于上面的@Setter、@Getter、@RequiredArgsConstructor、@ToString、@EqualsAndHashCode*/
@Data
public class Movie {

    private String id; //电影的id
    private String  directors;//导演
    private String title;//标题
    private String cover;//封面
    private String rate;//评分
    private String casts;//演员
}


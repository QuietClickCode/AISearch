package com.zjj.aisearch.pojo.dto;

import io.searchbox.annotations.JestId;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/*等价于上面的@Setter、@Getter、@RequiredArgsConstructor、@ToString、@EqualsAndHashCode*/
@Data
@Table(name = "movie")
public class MovieDTO {

    //加这个这个就作为索引主键的,不然他会自动生成
    @JestId
    @Id
    private String id; //电影的id
    private String  directors;//导演
    private String title;//标题
    private String cover;//封面
    private String rate;//评分
    private String casts;//演员
}


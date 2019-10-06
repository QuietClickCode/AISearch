package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description: 系统日志展示列表
 * @author: zjj
 * @create: 2019-09-27 13:43:21
 **/
@Data
@Getter
@Setter
@ToString
public class AiNoteList extends AiNote {

    private BrowserInfo browserInfo;
    private Location location;
    private User user;
}

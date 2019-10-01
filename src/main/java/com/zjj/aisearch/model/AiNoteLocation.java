package com.zjj.aisearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-10-01 10:49:21
 **/
@Data
@Getter
@Setter
@ToString
public class AiNoteLocation extends AiNote{
    private Location location;
    private BrowserInfo browserInfo;
    private User user;
}

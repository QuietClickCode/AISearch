package com.zjj.aisearch.activemq;

import com.zjj.aisearch.service.impl.QueryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

//activemq消费者
/*
 * @author uv
 * @date 2018/9/15 18:36
 *
 */
@Component
@Slf4j
public class ConsumerService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private QueryServiceImpl queryService;

    @Autowired
    private Queue queue;

    // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @JmsListener(destination = "test")
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo("SQueue")
    public String handleMessage(String name) {
        log.info("成功接受Name" + name);
        Integer totalCount = 0;
        if (name.equals("查询用户总数")) {
             totalCount = queryService.queryUserTotalCount();
        }
        log.info("用户总数为" + totalCount);
        jmsMessagingTemplate.convertAndSend("test2", "用户总数" + totalCount);
        return "用户总数为:" + totalCount;
    }


}
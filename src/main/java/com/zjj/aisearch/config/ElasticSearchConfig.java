package com.zjj.aisearch.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*@Configuration*/
/*@PropertySource(value = "classpath:config/elasticsearch.properties")*/
public class ElasticSearchConfig {
    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);

    @Value("${es.hostName}")
    private String hostName;

    @Value("${es.transport}")
    private Integer transport;

    @Value("${es.cluster.name}")
    private String clusterName;

    @Bean
    public TransportClient transportClient() {
        logger.info("ElasticSearch初始化开始");

        TransportClient transportClient = null;

        try {
            TransportAddress transportAddress = new InetSocketTransportAddress(InetAddress.getByName(hostName), Integer.valueOf(transport));

            //配置信息
            Settings es = Settings.builder().put("cluster.name", clusterName).build();

            //配置信息Settings自定义
            transportClient = new PreBuiltTransportClient(es);

            transportClient.addTransportAddress(transportAddress);
        } catch (UnknownHostException e) {
            logger.error("ES创建错误", e);
        }
        return transportClient;
    }
}
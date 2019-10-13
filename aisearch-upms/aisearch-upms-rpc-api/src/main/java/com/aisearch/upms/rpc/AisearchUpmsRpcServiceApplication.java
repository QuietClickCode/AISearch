package com.aisearch.upms.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: AISearch
 * @description: 服务启动类
 * @author: zjj
 * @create: 2019-10-13 21:44:44
 **/
public class AisearchUpmsRpcServiceApplication {
    //日志记录
    private static final Logger LOGGER = LoggerFactory.getLogger(AisearchUpmsRpcServiceApplication.class);

    public static void main(String[] args) {
        LOGGER.info(">>>>> aisearch-upms-rpc-service 正在启动 <<<<<");
        /**
         ClassPathXmlApplicationContext作用加载类路径下 Spring 的配置文件
         */
        /**
         * ApplicatioContext 是接口有两个实现类:
         * ClassPathXmlApplicationContext :加载类路径下 Spring 的配置文件.
         FileSystemXmlApplicationContext :加载本地磁盘下 Spring 的配置文件.
         BeanFactory有一个架构图
         */
        /**
         * classpath和classpath*区别：
         * classpath即是高速java去哪儿找class文件，class文件的根目录

         classpath：只会到你的class路径中查找找文件。

         classpath*：不仅包含class路径，还包括jar文件中（class路径）进行查找。

         注意： 用classpath*:需要遍历所有的classpath，所以加载速度是很慢的；因此，在规划的时候，应该尽可能规划好资源文件所在的路径，尽量避免使用classpath*。

         classpath*的使用：

         当项目中有多个classpath路径，并同时加载多个classpath路径下（此种情况多数不会遇到）的文件，*就发挥了作用，如果不加*，则表示仅仅加载第一个classpath路径。
         */
        new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
        LOGGER.info(">>>>> aisearch-upms-rpc-service 启动完成 <<<<<");
    }
}

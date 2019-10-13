package com.aisearch.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 启动解压aisearchAdmin-x.x.x.jar到resources目录
 * Created by shuzheng on 2016/12/18.
 */
public class AisearchAdminUtil implements InitializingBean, ServletContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(AisearchAdminUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        System.out.println("=================================+++++++++++++++++++++++++++++++");
        LOGGER.info("===== 开始解压aisearch-admin =====");
        String version = PropertiesFileUtil.getInstance("aisearch-admin-client").get("aisearch.admin.version");
        LOGGER.info("aisearch-admin.jar 版本: {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/aisearch-admin-" + version + ".jar");
        LOGGER.info("aisearch-admin.jar 包路径: {}", jarPath);
        String resources = servletContext.getRealPath("/") + "/resources/aisearch-admin";
        LOGGER.info("aisearch-admin.jar 解压到: {}", resources);
        JarUtil.decompress(jarPath, resources);
        LOGGER.info("===== 解压aisearch-admin完成 =====");
    }

}

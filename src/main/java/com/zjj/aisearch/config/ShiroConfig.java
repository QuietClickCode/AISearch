package com.zjj.aisearch.config;

import com.zjj.aisearch.filter.ShiroLoginFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: AISearch
 * @description: Shiro相关配置
 * @author: zjj
 * @create: 2019-10-20 15:38:59
 **/
@Configuration
public class ShiroConfig {
    //配置session过期时间
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 设置session过期时间3600s
        sessionManager.setGlobalSessionTimeout(36000000L);
        return sessionManager;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //securityManager.setRealm(shiroRealm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

   /* @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();

        *//*未授权处理页*//*
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException", "/noauth");
        resolver.setExceptionMappings(properties);
        return resolver;
    }*/

    /**
     * *
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * *
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(getDefaultWebSecurityManager(getUserRealm()));
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        /**
         * 常用过滤器:
         * anon:无需登录
         * authc:必须认证
         * user:
         * perms:权限
         * role:角色
         */
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authc", new ShiroLoginFilter());
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        filterMap.put("/login", "anon");
        filterMap.put("/regist", "anon");
        filterMap.put("/toregist", "anon");
        filterMap.put("/validateUsername", "anon");
        filterMap.put("/tologin", "anon");
        filterMap.put("/noauth", "anon");
        filterMap.put("/test", "anon");
        filterMap.put("/islogin", "anon");
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "authc");
        filterMap.put("/*", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");
        /*shiroFilterFactoryBean.setLoginUrl("/login");*/
        return shiroFilterFactoryBean;
    }

    @Bean("defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    @Bean("userRealm")
    public UserRealm getUserRealm() {
        return new UserRealm();
    }
}

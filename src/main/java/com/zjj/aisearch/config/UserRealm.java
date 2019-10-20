package com.zjj.aisearch.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-10-20 15:40:00
 **/
public class UserRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission("user:add");

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String name = "zjj";
        String password = "123456";

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        if (!usernamePasswordToken.getUsername().equals(name)) {
            return null;
        }
        System.out.println("认证");
        return new SimpleAuthenticationInfo("", password, "");
    }
}

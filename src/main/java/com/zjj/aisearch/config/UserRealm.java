package com.zjj.aisearch.config;

import com.zjj.aisearch.model.User;
import com.zjj.aisearch.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2019-10-20 15:40:00
 **/
@Slf4j
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private IndexService indexServiceImpl;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("授权");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Integer userId = ((User) SecurityUtils.getSubject().getPrincipal()).getId();
        String permissionUrl = indexServiceImpl.selectPermission(userId);
        simpleAuthorizationInfo.addStringPermission(permissionUrl);



        return simpleAuthorizationInfo;
    }


    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        User isExistUser = indexServiceImpl.selectUserByUserName(username);
        if (isExistUser == null) {
            throw new UnknownAccountException();
        }

        if(!password.equals(isExistUser.getPassword())) {
            throw new IncorrectCredentialsException();
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(isExistUser,password,getName());
        return info;
    }
}

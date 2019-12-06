package com.zjj.aisearch.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.zjj.aisearch.model.User;
import com.zjj.aisearch.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private IndexService indexServiceImpl;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        if (token != null) {
            System.out.println(token + "99999999999999999999999999999999");
            String username = JWT.decode(token).getAudience().get(0);
            System.out.println(username);
            System.out.println(username);
            System.out.println(indexServiceImpl);
            User isExistUser = indexServiceImpl.selectUserByUserName(username);
            System.out.println("------------------------"+isExistUser);
            // 验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(isExistUser.getPassword())).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new JWTVerificationException("401");
            }
        } else {
            throw new JWTVerificationException("401");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
package com.zjj.aisearch.interceptor;

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
        /*String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        if (token != null) {
            String username = JWT.decode(token).getAudience().get(0);
            User isExistUser = indexServiceImpl.selectUserByUserName(username);
            // 验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(isExistUser.getPassword())).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new JWTVerificationException("401");
            }
        } else {
            throw new JWTVerificationException("401");
        }*/
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
package com.xingmeng.interceptor;

import com.xingmeng.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        String requestURI = request.getRequestURI();
//
//        if (requestURI.contains("/login")) {
//            log.info("登录请求，放行...");
//            return true;
//        }

        String token = request.getHeader("token");

        if (token == null || token.isEmpty()) {
            log.info("未携带token，拒绝访问401...");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.error("解析token失败，拒绝访问401...");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        log.info("解析token成功，放行...");
        return true;
    }
}

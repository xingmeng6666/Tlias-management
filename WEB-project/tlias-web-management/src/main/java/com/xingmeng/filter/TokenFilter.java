package com.xingmeng.filter;

import com.xingmeng.utils.CurrentHolder;
import com.xingmeng.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        if (requestURI.contains("/login")) {
            log.info("登录请求，放行...");
            chain.doFilter(httpRequest, httpResponse);
            return;
        }

        String token = httpRequest.getHeader("token");

        if (token == null || token.isEmpty()) {
            log.info("未携带token，拒绝访问401...");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前用户ID: {}将其存入ThreadLocal中", empId);

        } catch (Exception e) {
            log.error("解析token失败，拒绝访问401...");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        log.info("解析token成功，放行...");
        chain.doFilter(httpRequest, httpResponse);
        //删除ThreadLocal中的数据
        CurrentHolder.remove();
    }
}

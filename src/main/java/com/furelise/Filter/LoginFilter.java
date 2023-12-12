package com.furelise.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Order(value = 1)
public class LoginFilter extends OncePerRequestFilter {

    // 定義需要進行過濾的路徑清單
    private final List<String> pathsToFilter = Arrays.asList("/emp", "/otherPath");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("LoginFilter");

        // 強制轉換成 HttpServletRequest 和 HttpServletResponse
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 取得 session
        HttpSession session = req.getSession();

        // 從 session 判斷此 user 是否登入過
        Object account = session.getAttribute("account");

        if (account == null) {
            // 如果未登入，將當前請求的路徑存入 session，然後重導向至登入頁面
            session.setAttribute("location", req.getRequestURI());
            res.sendRedirect(req.getContextPath() + "/login.html");
            System.out.println("LoginFilter2");
            return;
        } else {
            // 如果已登入，繼續執行過濾器鏈
            filterChain.doFilter(request, response);
            System.out.println("LoginFilter3");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // 根據 request 的路徑判斷是否應用過濾器

        // 取得當前請求的路徑
        String path = request.getServletPath();

        // 使用 Java 8 Stream API 檢查 pathsToFilter 中是否有任何一個路徑是當前請求路徑的前綴
        // 如果找到符合的路徑，則返回 false，表示要應用過濾器
        // 如果找不到符合的路徑，則返回 true，表示不應用過濾器
        return pathsToFilter.stream().noneMatch(path::startsWith);
    }
}

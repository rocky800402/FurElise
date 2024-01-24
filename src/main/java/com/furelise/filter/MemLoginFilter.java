package com.furelise.filter;

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
public class MemLoginFilter extends OncePerRequestFilter {

    // 定義需要進行過濾的路徑清單
    private final List<String> pathsToFilter = Arrays.asList(
            "/planmall/shop",
            "/member/info/",
            "/memPlanFront.html",
            "/memOrdDetail.html",
            "/otherPath");

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain)
            throws ServletException, IOException {
        
        // 取得 session
        HttpSession session = req.getSession();

        // 從 session 判斷此 user 是否登入過
        Object account = session.getAttribute("mem");
        
        
        if (account == null) {
            // 如果未登入，將當前請求的路徑存入 session，然後重導向至登入頁面
            session.setAttribute("location", req.getRequestURI());
            resp.sendRedirect(req.getContextPath() + "/login");
            
            return;
        } else {
            // 如果已登入，繼續執行過濾器鏈
            filterChain.doFilter(req, resp);
            
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

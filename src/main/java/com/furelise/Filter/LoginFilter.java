package com.furelise.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//@Component
@WebFilter(
        filterName = "loginFilter",
        urlPatterns = {"/*"}
)
// 宣告這是一個過濾器類別，Spring 會根據其屬性配置過濾器，主要屬性如下。
//filterName : 指定Filter 名稱。
//value : 指定攔截路徑，與urlPatterns 擇一使用。
//urlPatterns : 指定攔截路徑，與value 擇一使用。 多個路徑使用時用，(逗點)分隔，EX urlPatterns = {"/emp/*","/mem/*"})
//servletNames : 設定Filter 過濾哪些Servlet 的請求。
//dispatcherTypes : 設定Filter 過濾哪種請求方式，屬性有ASYNC、ERROR、FORWARD、INCLUDE、REQUEST，預設為全選。
//initParams : 設定Filter 初始化參數。
//asyncSupported : 設定Filter 是否支援異步模式，預設為false。
//description : 描述Filter 用途。
//displayName : 指定Filter 顯示名稱。
//@Order(value = 1) //表示過濾順序，其值越小越優先執行。
public class LoginFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws ServletException, IOException {
        System.out.println("LoginFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // 【取得 session】
        HttpSession session = req.getSession();
        // 【從 session 判斷此user是否登入過】
        Object account = session.getAttribute("account");
        if (account == null) {
            session.setAttribute("location", req.getRequestURI());
            res.sendRedirect(req.getContextPath() + "/login.html");
            System.out.println("LoginFilter2");
            return;
        } else {
            chain.doFilter(request, response);
            System.out.println("LoginFilter3");
        }
    }

    @Override
    public void destroy() {
        config = null;
    }
}

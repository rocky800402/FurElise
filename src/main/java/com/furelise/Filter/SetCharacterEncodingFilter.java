package com.furelise.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
//@Component
@WebFilter(
        filterName = "setCharacterEncodingFilter",
        urlPatterns = {"/*"},
        initParams={
                @WebInitParam(name = "encoding", value = "UTF-8")
        }
)
// 宣告這是一個過濾器類別，Spring 會根據其屬性配置過濾器，主要屬性如下。
//filterName : 指定Filter 名稱。
//value : 指定攔截路徑，與urlPatterns 擇一使用。
//urlPatterns : 指定攔截路徑，與value 擇一使用。 多個路徑使用時用，(逗點)分隔，EX urlPatterns = {"/emp/*","/mem/*"})
//servletNames : 設定Filter 過濾哪些Servlet 的請求。
//dispatcherTypes : 設定Filter 過濾哪種請求方式，屬性有ASYNC、ERROR、FORWARD、INCLUDE、REQUEST，預設為全選。
// 設定Filter 初始化參數。
//asyncSupported : 設定Filter 是否支援異步模式，預設為false。
//description : 描述Filter 用途。
//displayName : 指定Filter 顯示名稱。
@Order(value = 2) //表示過濾順序，其值越小越優先執行。
public class SetCharacterEncodingFilter implements Filter {

    protected String encoding = null;
    protected FilterConfig config = null;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        this.encoding = config.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("SetCharacterEncodingFilter1");
        // 使用 Filter 解決 Query String 之編碼問題
        // request.setCharacterEncoding("特定的字碼集");
        request.setCharacterEncoding(encoding);
        // 將程式控制權交給後續的過濾器
        chain.doFilter(request, response);
        System.out.println("SetCharacterEncodingFilter2");
    }

    @Override
    public void destroy() {
        this.encoding = null;
        this.config = null;

    }
}

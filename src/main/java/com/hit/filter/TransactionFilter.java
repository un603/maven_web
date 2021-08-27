package com.hit.filter;

import com.hit.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(req, resp);
            JDBCUtils.commitAndClose();//提交事务
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);//把异常抛给Tomcat服务器管理展示友好的错误页面
        }
    }

    @Override
    public void destroy() {

    }

}

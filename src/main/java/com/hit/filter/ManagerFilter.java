package com.hit.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManagerFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        Object user = httpServletRequest.getSession().getAttribute("user");

        if(user == null){
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }


}

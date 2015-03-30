package net.evdokimov.eshop.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Base*, Skeleton*, Abstract*
public abstract class BaseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //NOP
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

    @Override
    public void destroy() {
        //NOP
    }
}

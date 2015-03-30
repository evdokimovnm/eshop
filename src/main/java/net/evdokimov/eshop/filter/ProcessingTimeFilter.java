package net.evdokimov.eshop.filter;

import javax.servlet.*;
import java.io.IOException;

public class ProcessingTimeFilter implements Filter {

    public ProcessingTimeFilter() {
        System.out.println(">> ProcessingTimeFilter - NEW");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(">> ProcessingTimeFilter - init ");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long inTime = System.nanoTime();
        chain.doFilter(request, response);
        long outTime = System.nanoTime();
        System.out.println(">> ProcessingTimeFilter: dT = " + (outTime - inTime));
        System.out.println();
    }

    @Override
    public void destroy() {
        System.out.println(">> ProcessingTimeFilter - destroy");
    }
}

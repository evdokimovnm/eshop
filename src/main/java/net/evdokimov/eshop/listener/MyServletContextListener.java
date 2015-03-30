package net.evdokimov.eshop.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class MyServletContextListener implements ServletContextListener {

    public MyServletContextListener() {
        System.out.println(">> MyServletContextListener - NEW");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(">> MyServletContextListener - created, contextPath - " + sce.getSource().toString());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println(">> MyServletContextListener - destroyed, contextPath - " + sce.getServletContext());
    }
}

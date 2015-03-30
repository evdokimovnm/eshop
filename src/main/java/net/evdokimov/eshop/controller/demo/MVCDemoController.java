package net.evdokimov.eshop.controller.demo;

import net.evdokimov.eshop.entity.demo.MockEntityA;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Nikita on 05.03.2015.
 */
public class MVCDemoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // add to REQUEST attribute
        req.setAttribute("requestAttribute", new MockEntityA());
        // add to SESSION attribute
        HttpSession session = req.getSession();
        session.setAttribute("sessionAttribute", new MockEntityA());
        // add to SERVLET_CONTEXT attribute
        ServletContext servletContext = req.getServletContext();
        servletContext.setAttribute("servletContextAttribute", new MockEntityA());

        req.setAttribute("test", "request");
        req.getSession().setAttribute("test", "session");
        req.getServletContext().setAttribute("test", "servletContext");

        req.getRequestDispatcher("mvcMockView.jsp").forward(req, resp);

    }
}

package net.evdokimov.eshop.controller;

import net.evdokimov.eshop.inject.DependencyInjectionServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static net.evdokimov.eshop.controller.SessionAttributes.LOGIN_USER;



@WebServlet(urlPatterns = "/userLogout.do")
public class UserLogoutController extends DependencyInjectionServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.removeAttribute(LOGIN_USER);
        //request.getRequestDispatcher(request.getParameter("ref")).forward(request, response);
        String ref = request.getParameter("ref");
        String id = request.getParameter("id");
        if (id == null) {
            response.sendRedirect(ref);
        } else {
            response.sendRedirect(ref + "?id=" + id);
        }
    }
}



package net.evdokimov.eshop.controller.demo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DemoController extends HttpServlet {//чтобы быть сервлетом нужно наследоваться от HttpServlets

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("Hello from servlet!");
    }
}

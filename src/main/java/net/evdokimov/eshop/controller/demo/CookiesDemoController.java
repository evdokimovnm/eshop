package net.evdokimov.eshop.controller.demo;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Nikita on 06.03.2015.
 */
public class CookiesDemoController extends HttpServlet {
    private static final String COOKIE_NAME = "counter";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie fromClient = null;
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (COOKIE_NAME.equals(cookie.getName())) {
                fromClient = cookie;
                break;
            }
        }
        if (fromClient == null) {
            resp.addCookie(new Cookie(COOKIE_NAME, "" + 1));
            resp.getWriter().write("Your visit this page: 1 time");
        } else {
            int visitCount = Integer.valueOf(fromClient.getValue());
            resp.addCookie(new Cookie(COOKIE_NAME, "" + (++visitCount)));
            resp.getWriter().write("Your visit this page: " + visitCount + " time");
        }
    }
}

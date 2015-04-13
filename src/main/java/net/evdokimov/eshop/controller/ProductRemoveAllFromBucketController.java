package net.evdokimov.eshop.controller;

import static net.evdokimov.eshop.controller.SessionAttributes.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by Nikita on 08.03.2015.
 */
public class ProductRemoveAllFromBucketController extends HttpServlet {
    public static final String PAGE_ERROR = "productAll.do";
    public static final String PARAM_ID = "id";

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                Integer id = Integer.parseInt(idStr);
                HttpSession session = req.getSession(false);
                session.removeAttribute(PRODUCTS_IN_BUCKET);
                //OK
                String newLocation = "product.do?id=" + id;
                resp.sendRedirect(newLocation);
                return;
            } catch (NumberFormatException ignore) {
                // NOP
            }
        } else {
            HttpSession session = req.getSession(false);
            session.removeAttribute(PRODUCTS_IN_BUCKET);
            //OK
            String newLocation = "productAll.do";
            resp.sendRedirect(newLocation);
            return;
        }
        //FAIL
        resp.sendRedirect(PAGE_ERROR);
    }
}

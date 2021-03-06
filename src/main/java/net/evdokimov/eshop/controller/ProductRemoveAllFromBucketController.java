package net.evdokimov.eshop.controller;

import static net.evdokimov.eshop.controller.SessionAttributes.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet(urlPatterns = "/productRemoveAllFromBucket.do")
public class ProductRemoveAllFromBucketController extends HttpServlet {
    public static final String PAGE_ERROR = "productAll.do";
    public static final String PARAM_ID = "id";
    public static final String PARAM_REF = "ref";

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        String ref = req.getParameter(PARAM_REF);
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
            String newLocation = ref;
            resp.sendRedirect(newLocation);
            return;
        }
        //FAIL
        resp.sendRedirect(PAGE_ERROR);
    }
}

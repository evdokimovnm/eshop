package net.evdokimov.eshop.controller;

import net.evdokimov.eshop.dao.OrderDao;
import net.evdokimov.eshop.dao.exception.DaoException;
import net.evdokimov.eshop.dao.impl.jpa.tx.TransactionManager;
import net.evdokimov.eshop.dao.impl.jpa.tx.UnitOfWork;
import net.evdokimov.eshop.entity.Product;
import net.evdokimov.eshop.entity.User;
import net.evdokimov.eshop.inject.DependencyInjectionServlet;
import net.evdokimov.eshop.inject.Inject;
import static net.evdokimov.eshop.controller.SessionAttributes.*;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/order.do")
public class OrderController extends DependencyInjectionServlet {
    public static final String PAGE_OK = "/productRemoveAllFromBucket.do?ref=orderResult.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    @Inject("txManager")
    private TransactionManager txManager;

    @Inject("orderDao")
    private OrderDao orderDao;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        final User user = (User)session.getAttribute(LOGIN_USER);
        final Map<Product, Integer> productsInBucket = (Map<Product, Integer>)session.getAttribute(PRODUCTS_IN_BUCKET);
        try {
            txManager.doInTransaction(new UnitOfWork<Void, DaoException>() {
                @Override
                public Void doInTx(EntityManager manager) throws DaoException {
                    orderDao.insertOrder(manager, user, productsInBucket);
                    return null;
                }
            });
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);

        } catch (DaoException e) {
            e.printStackTrace();
            resp.sendRedirect(PAGE_ERROR);
        }
    }
}

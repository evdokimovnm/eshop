package net.evdokimov.eshop.controller;

import net.evdokimov.eshop.dao.ProductDao;
import net.evdokimov.eshop.dao.exception.DaoException;
import net.evdokimov.eshop.dao.impl.jdbc.tx.TransactionManager;
import net.evdokimov.eshop.dao.impl.jdbc.tx.UnitOfWork;
import net.evdokimov.eshop.entity.Product;
import net.evdokimov.eshop.inject.DependencyInjectionServlet;
import net.evdokimov.eshop.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ProductRemoveController extends DependencyInjectionServlet {
    public static final String PAGE_ERROR = "error.jsp";
    public static final String PARAM_ID = "id";


    @Inject("txManager")
    private TransactionManager txManager;

    @Inject("productDao")
    private ProductDao productDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parId = req.getParameter(PARAM_ID);
        if(parId != null) {
            try {
                final int id = Integer.parseInt(parId);
                txManager.doInTransaction(new UnitOfWork<Product, DaoException>() {
                    @Override
                    public Product doInTx() throws DaoException {
                        productDao.delete(id);
                        return null;
                    }
                });
                // OK
                String newLocation = "productAll.do";
                resp.sendRedirect(newLocation);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}

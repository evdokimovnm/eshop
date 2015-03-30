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
import java.util.concurrent.Callable;


public class ProductInsertController extends DependencyInjectionServlet {
    public static final String PAGE_ERROR = "error.jsp";

    public static final String PARAM_PRODUCT_NAME = "productName";

    @Inject("txManager")
    private TransactionManager txManager;

    @Inject("productDao")
    private ProductDao productDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String productName = req.getParameter(PARAM_PRODUCT_NAME);
        try {
            if (!productName.equals("")) {
                txManager.doInTransaction(new UnitOfWork<Product, DaoException>() {
                    @Override
                    public Product doInTx() throws DaoException {
                        productDao.insert(new Product(0, productName));
                        return null;
                    }
                });
            }
            // OK
            String newLocation = "productAll.do";
            resp.sendRedirect(newLocation);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(PAGE_ERROR);
    }

}

package net.evdokimov.eshop.controller;

import net.evdokimov.eshop.dao.ProductDao;
import net.evdokimov.eshop.dao.ProductTypeDao;
import net.evdokimov.eshop.dao.exception.DaoException;
import net.evdokimov.eshop.dao.impl.jpa.tx.TransactionManager;
import net.evdokimov.eshop.dao.impl.jpa.tx.UnitOfWork;
import net.evdokimov.eshop.entity.Product;
import net.evdokimov.eshop.entity.ProductType;
import net.evdokimov.eshop.inject.DependencyInjectionServlet;
import net.evdokimov.eshop.inject.Inject;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/productInsertNew.do")
public class ProductInsertController extends DependencyInjectionServlet {
    public static final String PAGE_ERROR = "error.jsp";

    public static final String PARAM_PRODUCT_NAME = "productName";
    public static final String PARAM_PRODUCT_TYPE = "productType";

    @Inject("txManager")
    private TransactionManager txManager;

    @Inject("productDao")
    private ProductDao productDao;

    @Inject("productTypeDao")
    private ProductTypeDao productTypeDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String productName = req.getParameter(PARAM_PRODUCT_NAME);
        final String productType = req.getParameter(PARAM_PRODUCT_TYPE);
        try {
            if (!productName.equals("")) {
                txManager.doInTransaction(new UnitOfWork<Product, DaoException>() {
                    @Override
                    public Product doInTx(EntityManager manager) throws DaoException {
                        int productTypeId = productTypeDao.getProductTypeId(manager, productType);
                        productDao.insert(manager, new Product(productName, new ProductType(productTypeId)));
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

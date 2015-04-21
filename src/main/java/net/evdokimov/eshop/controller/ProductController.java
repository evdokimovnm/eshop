package net.evdokimov.eshop.controller;
import net.evdokimov.eshop.dao.ProductDao;
import net.evdokimov.eshop.dao.exception.DaoException;
import net.evdokimov.eshop.dao.impl.jpa.tx.TransactionManager;
import net.evdokimov.eshop.dao.impl.jpa.tx.UnitOfWork;
import net.evdokimov.eshop.entity.Product;
import net.evdokimov.eshop.inject.DependencyInjectionServlet;
import net.evdokimov.eshop.inject.Inject;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/product.do")
public class ProductController extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "product";
    public static final String PAGE_OK = "product.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    @Inject("txManager")
    private TransactionManager txManager;

    @Inject("productDao")
    private ProductDao productDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                final Integer id = Integer.valueOf(idStr);
                Product model = txManager.doInTransaction(new UnitOfWork<Product, DaoException>() {
                    @Override
                    public Product doInTx(EntityManager manager) throws DaoException {
                        return productDao.selectById(manager, id);
                    }
                });
                req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);//внутренний редирект
                //OK
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            } catch (DaoException | NumberFormatException e) {
                e.printStackTrace();
                //FAIL
                resp.sendRedirect(PAGE_ERROR);//внешний редирект
            }
        }
    }
}

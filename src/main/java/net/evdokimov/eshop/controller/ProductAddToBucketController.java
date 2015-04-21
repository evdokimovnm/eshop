package net.evdokimov.eshop.controller;

import net.evdokimov.eshop.dao.ProductDao;
import net.evdokimov.eshop.dao.exception.DaoException;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.dao.impl.jpa.tx.TransactionManager;
import net.evdokimov.eshop.dao.impl.jpa.tx.UnitOfWork;
import net.evdokimov.eshop.entity.Product;
import net.evdokimov.eshop.inject.DependencyInjectionServlet;
import net.evdokimov.eshop.inject.Inject;

import static net.evdokimov.eshop.controller.SessionAttributes.PRODUCTS_IN_BUCKET;


import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


import static java.util.Collections.singletonMap;
import static java.util.Collections.unmodifiableMap;


@WebServlet(urlPatterns = "/productAddToBucket.do")
public class ProductAddToBucketController extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String PAGE_ERROR = "productAll.do";

    @Inject("productDao")
    private ProductDao productDao;

    @Inject("txManager")
    private TransactionManager txManager;

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                final Integer id = Integer.valueOf(idStr);
                Product product = txManager.doInTransaction(new UnitOfWork<Product, DaoException>() {
                    @Override
                    public Product doInTx(EntityManager manager) throws DaoException {
                        return productDao.selectById(manager, id);
                    }
                });
                HttpSession session = req.getSession(true);
                Map<Product, Integer> oldBucked = (Map < Product, Integer >)session.getAttribute(PRODUCTS_IN_BUCKET);
                if (oldBucked == null) {
                    session.setAttribute(PRODUCTS_IN_BUCKET, singletonMap(product, 1));
                } else {
                    Map<Product, Integer> newBucked = new LinkedHashMap<>(oldBucked);
                    if (!newBucked.containsKey(product)) {
                        newBucked.put(product, 1);
                    } else {
                        newBucked.put(product, newBucked.get(product) + 1);
                    }
                    session.setAttribute(PRODUCTS_IN_BUCKET, unmodifiableMap(newBucked));
                }
                // OK
                String newLocation = "product.do?id=" + id;
                resp.sendRedirect(newLocation);
                return;
            } catch (NumberFormatException | NoSuchEntityException | DaoSystemException ignore) {
                /*NOP*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //FAIL
        resp.sendRedirect(PAGE_ERROR);
    }
}

package net.evdokimov.eshop.controller;

import net.evdokimov.eshop.dao.ProductDao;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.dao.impl.jdbc.tx.TransactionManager;
import net.evdokimov.eshop.dao.impl.jdbc.tx.UnitOfWork;
import net.evdokimov.eshop.entity.Product;
import net.evdokimov.eshop.inject.DependencyInjectionServlet;
import net.evdokimov.eshop.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import static java.util.Collections.unmodifiableMap;
import static net.evdokimov.eshop.controller.SessionAttributes.PRODUCTS_IN_BUCKET;

/**
 * Created by Nikita on 07.03.2015.
 */
public class ProductRemoveFromBucketController extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String PAGE_ERROR = "productAll.do";
    public static final String PAGE_RETURN = "ref";

    @Inject("productDao")
    private ProductDao productDao;

    @Inject("txManager")
    private TransactionManager txManager;

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        String ref = req.getParameter(PAGE_RETURN);
        if (idStr != null) {
            try {
                final Integer id = Integer.valueOf(idStr);
                final Product product = txManager.doInTransaction(new UnitOfWork<Product, Exception>() {
                    @Override
                    public Product doInTx() throws Exception {
                        return productDao.selectById(id);
                    }
                });

                HttpSession session = req.getSession(false);
                Map<Product, Integer> oldBucked = (Map < Product, Integer >)session.getAttribute(PRODUCTS_IN_BUCKET);
                Map<Product, Integer> newBucked = new LinkedHashMap<>(oldBucked);
                if (newBucked.get(product) > 1) {
                    newBucked.put(product, newBucked.get(product) - 1);
                } else {
                    newBucked.remove(product);
                }
                session.setAttribute(PRODUCTS_IN_BUCKET, unmodifiableMap(newBucked));
                // OK
                if (ref.equals("bucket")){
                    String newLocation = "bucket.jsp";
                    resp.sendRedirect(newLocation);
                    return;
                } else {
                    String newLocation = "product.do?id=" + ref;
                    resp.sendRedirect(newLocation);
                    return;
                }

            } catch (NumberFormatException | NoSuchEntityException | DaoSystemException ignore) {
                ignore.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //FAIL
        resp.sendRedirect(PAGE_ERROR);
    }
}

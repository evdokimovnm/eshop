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
import java.util.List;
import org.apache.log4j.Logger;

import static org.apache.log4j.Logger.getLogger;
import static net.evdokimov.eshop.util.ClassName.getCurrentClassName;
@WebServlet(urlPatterns = "/productAll.do")
public class ProductAllController extends DependencyInjectionServlet {
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "productList";
    public static final String PAGE_OK = "productType.do";
    public static final String PAGE_ERROR = "error.jsp";

    public static final Logger logger = getLogger(getCurrentClassName());

    @Inject("productDao")
    private ProductDao productDao;

    @Inject("txManager")
    private TransactionManager txManager;

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Product> model = txManager.doInTransaction(new UnitOfWork<List<Product>, DaoException>() {
                @Override
                public List<Product> doInTx(EntityManager manager) throws DaoException {
                    return productDao.selectAll(manager);
                }
            });
            logger.trace("set attribute '" + ATTRIBUTE_MODEL_TO_VIEW + "' to " + model);
            req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
            //OK
            logger.debug("PAGE_OK: RequestDispatcher forward to '" + PAGE_OK + "'");
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
        } catch (DaoException e) {
            //FAIL
            resp.sendRedirect(PAGE_ERROR);//внешний редирект
            logger.warn("PAGE_ERROR: respond send redirect to '" + PAGE_ERROR + "'", e);
        }
    }
}

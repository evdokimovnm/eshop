package net.evdokimov.eshop.controller;


import net.evdokimov.eshop.dao.ProductTypeDao;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.impl.jdbc.tx.TransactionManager;
import net.evdokimov.eshop.dao.impl.jdbc.tx.UnitOfWork;
import net.evdokimov.eshop.entity.ProductType;
import net.evdokimov.eshop.inject.DependencyInjectionServlet;
import net.evdokimov.eshop.inject.Inject;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static net.evdokimov.eshop.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;


public class ProductTypeAllController extends DependencyInjectionServlet {
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "productTypeList";
    public static final String PAGE_OK = "productsAll.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    public static final Logger logger = getLogger(getCurrentClassName());

    @Inject("productTypeDao")
    private ProductTypeDao productTypeDao;

    @Inject("txManager")
    private TransactionManager txManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<ProductType> model = txManager.doInTransaction(new UnitOfWork<List<ProductType>, DaoSystemException>() {
                @Override
                public List<ProductType> doInTx() throws DaoSystemException {
                    return productTypeDao.getProductTypeAll();
                }
            });
            logger.trace("set attribute '" + ATTRIBUTE_MODEL_TO_VIEW + "' to " + model);
            req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
            logger.debug("PAGE_OK: RequestDispatcher forward to '" + PAGE_OK + "'");
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
        } catch (DaoSystemException e) {
            resp.sendRedirect(PAGE_ERROR);
            logger.warn("PAGE_ERROR: respond send redirect to '" + PAGE_ERROR + "'", e);
        }

    }
}

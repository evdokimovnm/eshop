package net.evdokimov.eshop.controller;

import net.evdokimov.eshop.dao.ProductDao;
import net.evdokimov.eshop.dao.exception.DaoException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.dao.impl.jdbc.tx.TransactionManager;
import net.evdokimov.eshop.dao.impl.jdbc.tx.UnitOfWork;
import net.evdokimov.eshop.entity.Product;
import net.evdokimov.eshop.inject.DependencyInjectionServlet;
import net.evdokimov.eshop.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/productChoose.do")
public class ProductChooseController extends DependencyInjectionServlet {
    public static final String PARAM_TYPE_ID = "id";
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "productList";
    public static final String PAGE_OK = "productsChosenList.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    @Inject("productDao")
    private ProductDao productDao;

    @Inject("txManager")
    private TransactionManager txManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeIdStr = req.getParameter(PARAM_TYPE_ID);
        try {
            final Integer typeId = Integer.parseInt(typeIdStr);
            List<Product> model = txManager.doInTransaction(new UnitOfWork<List<Product>, DaoException>() {
                @Override
                public List<Product> doInTx() throws DaoException {
                    return productDao.selectByTypeId(typeId);
                }
            });
            req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
        } catch (NoSuchEntityException e) {
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
        } catch (DaoException | NumberFormatException e) {
            e.printStackTrace();
            resp.sendRedirect(PAGE_ERROR);
        }
    }
}

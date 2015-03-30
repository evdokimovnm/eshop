package net.evdokimov.eshop.controller.old;

import net.evdokimov.eshop.dao.ProductDao;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.dao.impl.mock.ProductDaoMock;
import net.evdokimov.eshop.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Nikita on 06.03.2015.
 */
public class ProductAllControllerOld extends HttpServlet {
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "productList";
    public static final String PAGE_OK = "productAll.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    private ProductDao productDao = new ProductDaoMock();//используем интерфейс ProductDao - OK,
                                                        // но нужно явно указать его реализацию ProductDaoMock - не ОК
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Product> model = productDao.selectAll();
            req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
            //OK
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            return;
        } catch (DaoSystemException ignore) {
            /*NOP*/
        } catch (NoSuchEntityException e) {
            e.printStackTrace();
        }
        //FAIL
        resp.sendRedirect(PAGE_ERROR);//внешний редирект

    }
}

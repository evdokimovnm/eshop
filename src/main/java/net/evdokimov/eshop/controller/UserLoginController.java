package net.evdokimov.eshop.controller;

import net.evdokimov.eshop.dao.UserDao;
import net.evdokimov.eshop.dao.exception.DaoException;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.dao.impl.jdbc.tx.TransactionManager;
import net.evdokimov.eshop.dao.impl.jdbc.tx.UnitOfWork;
import net.evdokimov.eshop.entity.User;
import net.evdokimov.eshop.inject.DependencyInjectionServlet;
import net.evdokimov.eshop.inject.Inject;
import static net.evdokimov.eshop.controller.SessionAttributes.LOGIN_USER;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 1 on 21.03.2015.
 */
public class UserLoginController extends DependencyInjectionServlet {

    public static final String PAGE_ERROR = "error.jsp";

    @Inject("userDao")
    private UserDao userDao;

    @Inject("txManager")
    private TransactionManager txManager;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        if(!login.equals("") && !password.equals("")) {
            try{
                User model = txManager.doInTransaction(new UnitOfWork<User, DaoException>() {
                    @Override
                    public User doInTx() throws DaoException {
                        try {
                            return userDao.selectByLoginAndPassword(login, password);
                        } catch (NoSuchEntityException e) {
                            return null;
                        }
                    }
                });
                if(model == null) {
                    req.setAttribute("loginIncorrect", true);
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                    return;
                }
                HttpSession session = req.getSession();
                session.setAttribute(LOGIN_USER, model);
                String ref = req.getParameter("ref");
                String id = req.getParameter("id");
                if (id.equals("null")) {
                    resp.sendRedirect(ref);
                } else {
                    resp.sendRedirect(ref+ "?id=" + id);
                }
                return;
            } catch (DaoException e) {
                e.printStackTrace();
            }

        } else {
            req.setAttribute("loginIncorrect", true);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}

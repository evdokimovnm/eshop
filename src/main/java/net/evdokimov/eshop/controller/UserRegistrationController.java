package net.evdokimov.eshop.controller;


import net.evdokimov.eshop.dao.UserDao;
import net.evdokimov.eshop.dao.exception.DaoBusinessException;
import net.evdokimov.eshop.dao.exception.DaoException;
import net.evdokimov.eshop.dao.impl.jpa.tx.TransactionManager;
import net.evdokimov.eshop.dao.impl.jpa.tx.UnitOfWork;
import net.evdokimov.eshop.entity.User;
import net.evdokimov.eshop.inject.DependencyInjectionServlet;
import net.evdokimov.eshop.inject.Inject;
import static net.evdokimov.eshop.controller.SessionAttributes.LOGIN_USER;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(urlPatterns = "/userRegistration.do")
public class UserRegistrationController extends DependencyInjectionServlet {

    public static final String PAGE_ERROR = "error.jsp";
    public static final String PAGE_OK = "registration.jsp";
    public static final String LOGIN_OR_EMAIL_IS_ALREADY_EXIST = "loginOrEmailExist";
    public static final String LOGIN_OR_PASSWORD_INCORRECT = "loginIncorrect";

    @Inject("txManager")
    private TransactionManager txManager;

    @Inject("userDao")
    private UserDao userDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String email = req.getParameter("email");
        try {
            if(!login.equals("") && !password.equals("") && !email.equals("")) {
                try {
                    User model = txManager.doInTransaction(new UnitOfWork<User, DaoException>() {
                        @Override
                        public User doInTx(EntityManager manager) throws DaoException {
                            return userDao.insert(manager, new User(login, password, email, "customer"));
                        }
                    });
                    HttpSession session = req.getSession();
                    session.setAttribute(LOGIN_USER, model);
                } catch (DaoBusinessException e) {
                    req.setAttribute(LOGIN_OR_EMAIL_IS_ALREADY_EXIST, true);
                    req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                }
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } else {
                req.setAttribute(LOGIN_OR_PASSWORD_INCORRECT, true);
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}

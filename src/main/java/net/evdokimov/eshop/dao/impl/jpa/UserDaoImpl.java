package net.evdokimov.eshop.dao.impl.jpa;

import net.evdokimov.eshop.dao.UserDao;
import net.evdokimov.eshop.dao.exception.DaoBusinessException;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.User;

import javax.persistence.EntityManager;


public class UserDaoImpl implements UserDao {

    @Override
    public User selectByLoginAndPassword(EntityManager manager, String login, String password) throws DaoSystemException, NoSuchEntityException {
        User userFromDb = (User)manager
                .createQuery("SELECT u FROM User u WHERE login=:login and password=:password")
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
        return userFromDb;
    }

    @Override
    public User insert(EntityManager manager, User user) throws DaoSystemException, DaoBusinessException {
        User userFromBd = manager.merge(user);
        return userFromBd;
    }

}



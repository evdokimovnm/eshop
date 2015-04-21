package net.evdokimov.eshop.dao;

import net.evdokimov.eshop.dao.exception.DaoBusinessException;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.User;

import javax.persistence.EntityManager;


public interface UserDao {
    /**
     * Never return null!
     */
    public User selectByLoginAndPassword(EntityManager manager, String login, String password) throws DaoSystemException, NoSuchEntityException;

    public User insert(EntityManager manager, User user) throws DaoSystemException, DaoBusinessException;
}

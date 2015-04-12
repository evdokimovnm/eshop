package net.evdokimov.eshop.dao;

import net.evdokimov.eshop.dao.exception.DaoBusinessException;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.User;



public interface UserDao {
    /**
     * Never return null!
     */
    public User selectByLoginAndPassword(String login, String password) throws DaoSystemException, NoSuchEntityException;

    public int insert(User user) throws DaoSystemException, DaoBusinessException;
}

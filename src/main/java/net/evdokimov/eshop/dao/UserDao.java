package net.evdokimov.eshop.dao;

import net.evdokimov.eshop.dao.exception.DaoBusinessException;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.Product;
import net.evdokimov.eshop.entity.User;

import java.util.List;

/**
 * Created by Nikita on 04.03.2015.
 */
public interface UserDao {

    /**
     * Never return null!
     */
    public User selectByLoginAndPassword(String login, String password) throws DaoSystemException, NoSuchEntityException;

    //public List<Product> selectAll() throws DaoSystemException;

    public int insert(User user) throws DaoSystemException, DaoBusinessException;

    //public void delete(int id) throws DaoSystemException;
}

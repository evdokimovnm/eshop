package net.evdokimov.eshop.dao;

import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.Product;

import java.util.List;

/**
 * Created by Nikita on 04.03.2015.
 */
public interface ProductDao {

    /**
     * Never return null!
     */
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException;

    public List<Product> selectAll() throws DaoSystemException, NoSuchEntityException;

    public void insert(Product product) throws DaoSystemException;

    public void delete(int id) throws DaoSystemException, NoSuchEntityException;
}

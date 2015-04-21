package net.evdokimov.eshop.dao;

import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.Product;

import javax.persistence.EntityManager;
import java.util.List;


public interface ProductDao {

    /**
     * Never return null!
     */
    public Product selectById(EntityManager manager, int id) throws DaoSystemException, NoSuchEntityException;

    public List<Product> selectByTypeId(EntityManager manager, int type_id) throws DaoSystemException, NoSuchEntityException;

    public List<Product> selectAll(EntityManager manager) throws DaoSystemException, NoSuchEntityException;

    public void insert(EntityManager manager, Product product) throws DaoSystemException;

    public void delete(EntityManager manager, int id) throws DaoSystemException, NoSuchEntityException;
}

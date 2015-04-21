package net.evdokimov.eshop.dao;

import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.ProductType;

import javax.persistence.EntityManager;
import java.util.List;


public interface ProductTypeDao {

    public int getProductTypeId(EntityManager manager, String type) throws DaoSystemException, NoSuchEntityException;

    public List<ProductType> getProductTypeAll(EntityManager manager) throws DaoSystemException;
}

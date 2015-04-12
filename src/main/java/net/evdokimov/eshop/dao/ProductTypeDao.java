package net.evdokimov.eshop.dao;

import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.ProductType;

import java.util.List;


public interface ProductTypeDao {

    public int getProductTypeId(ProductType productType) throws DaoSystemException, NoSuchEntityException;

    public List<ProductType> getProductTypeAll() throws DaoSystemException;
}

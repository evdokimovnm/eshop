package net.evdokimov.eshop.dao;

import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.ProductType;

/**
 * Created by 1 on 09.04.2015.
 */
public interface ProductTypeDao {

    public int getProductTypeId(ProductType productType) throws DaoSystemException, NoSuchEntityException;
}

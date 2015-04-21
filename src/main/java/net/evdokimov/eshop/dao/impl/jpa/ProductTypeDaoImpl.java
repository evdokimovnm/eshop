package net.evdokimov.eshop.dao.impl.jpa;

import net.evdokimov.eshop.dao.ProductTypeDao;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.ProductType;

import javax.persistence.EntityManager;
import java.util.List;


public class ProductTypeDaoImpl implements ProductTypeDao {


    @Override
    public int getProductTypeId(EntityManager manager, String type) throws DaoSystemException, NoSuchEntityException {
        ProductType productTypeFromBd = (ProductType)manager
                .createQuery("SELECT pt FROM ProductType pt WHERE type=:type")
                .setParameter("type", type).getSingleResult();
        return productTypeFromBd.getId();
    }

    @Override
    public List<ProductType> getProductTypeAll(EntityManager manager) throws DaoSystemException {
        List list = manager.createQuery("SELECT pt FROM ProductType pt").getResultList();
        return (List<ProductType>)list;
    }
}

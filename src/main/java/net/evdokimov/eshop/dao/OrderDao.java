package net.evdokimov.eshop.dao;

import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.entity.Product;
import net.evdokimov.eshop.entity.User;

import javax.persistence.EntityManager;
import java.util.Map;

public interface OrderDao {
    public void insertOrder(EntityManager manager, User user, Map<Product, Integer> order) throws DaoSystemException;
}

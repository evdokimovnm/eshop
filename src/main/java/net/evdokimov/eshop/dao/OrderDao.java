package net.evdokimov.eshop.dao;

import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.entity.Product;
import net.evdokimov.eshop.entity.User;

import java.util.Map;

public interface OrderDao {
    public void insertOrder(User user, Map<Product, Integer> order) throws DaoSystemException;
}

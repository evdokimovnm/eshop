package net.evdokimov.eshop.dao.impl.jpa;


import net.evdokimov.eshop.dao.OrderDao;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.entity.Order;
import net.evdokimov.eshop.entity.Product;
import net.evdokimov.eshop.entity.User;

import javax.persistence.EntityManager;
import java.util.Map;


public class OrderDaoImpl implements OrderDao {

    @Override
    public void insertOrder(EntityManager manager, User user, Map<Product, Integer> order) throws DaoSystemException {

        for(Product product : order.keySet()) {
            Order newOrder = new Order();
            newOrder.setUser(user);
            newOrder.setProductId(product.getId());
            newOrder.setQuantity(order.get(product));
            manager.persist(newOrder);
        }
    }
}

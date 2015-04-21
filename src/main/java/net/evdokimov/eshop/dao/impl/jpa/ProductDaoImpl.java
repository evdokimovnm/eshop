package net.evdokimov.eshop.dao.impl.jpa;

import net.evdokimov.eshop.dao.ProductDao;
import net.evdokimov.eshop.entity.Product;


import javax.persistence.EntityManager;
import java.util.List;


public class ProductDaoImpl implements ProductDao {

    @Override
    public Product selectById(EntityManager manager, int id) {
        return manager.find(Product.class, id);
    }

    @Override
    public List<Product> selectByTypeId(EntityManager manager, int type_id) {
        List list =  manager.createQuery("SELECT p FROM Product p where productType.id=:type_id")
                .setParameter("type_id", type_id)
                .getResultList();
        return (List<Product>)list;
    }

    @Override
    public List<Product> selectAll(EntityManager manager) {
        List list = manager.createQuery("SELECT p FROM Product p").getResultList();
        return (List<Product>)list;
    }

    @Override
    public void insert(EntityManager manager, Product product) {
        manager.persist(product);
    }

    @Override
    public void delete(EntityManager manager, int id) {
        manager.remove(manager.find(Product.class, id));
    }
}
package net.evdokimov.eshop.dao.impl.mock;

import net.evdokimov.eshop.dao.ProductDao;
import net.evdokimov.eshop.dao.exception.DaoSystemException;
import net.evdokimov.eshop.dao.exception.NoSuchEntityException;
import net.evdokimov.eshop.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ProductDaoMock_1 implements ProductDao {
    private final Map<Integer, Product> memory = new ConcurrentHashMap<>();

    public ProductDaoMock_1() {
        this.memory.put(1, new Product(1, "Soup"));
        this.memory.put(2, new Product(2, "Shower"));
        this.memory.put(3, new Product(3, "Powder"));
    }

    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        System.out.println("selectById_1 - called!");
        if (!memory.containsKey(id)) {
            throw new NoSuchEntityException("Nо Product for id == '" + id + "'");
        }
        return memory.get(id);
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        return new ArrayList<>(memory.values());
    }

    @Override
    public void insert(Product product) throws DaoSystemException {

    }

    @Override
    public void delete(int id) throws DaoSystemException {

    }
}

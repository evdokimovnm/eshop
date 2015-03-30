package net.evdokimov.eshop.aspect;


import net.evdokimov.eshop.entity.Product;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleCache {
    private Map<List, Product> cache = new HashMap<>();

    public Product logCache (ProceedingJoinPoint call ,int id) throws Throwable {
        List key = Arrays.asList(/*ProductDao.class, "selectById",*/ id);
        if (cache.containsKey(key)) {
            System.out.println("ASPECT.CACHE - return model from cache");
            return cache.get(key);
        } else {
            Product model = (Product)call.proceed();
            cache.put(key, model);
            System.out.println("ASPECT.CACHE - model is added");
            return model;
        }
    }
}

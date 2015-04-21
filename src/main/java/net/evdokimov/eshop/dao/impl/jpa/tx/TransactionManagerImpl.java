package net.evdokimov.eshop.dao.impl.jpa.tx;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TransactionManagerImpl implements TransactionManager {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eshopJpa");

    public TransactionManagerImpl() {
    }

    @Override
    public <T, E extends Exception> T doInTransaction(UnitOfWork<T, E> unitOfWork) throws E {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            T result = unitOfWork.doInTx(entityManager);
            entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }
}

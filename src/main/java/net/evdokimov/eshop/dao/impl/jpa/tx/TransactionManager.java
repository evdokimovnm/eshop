package net.evdokimov.eshop.dao.impl.jpa.tx;

public interface TransactionManager {

    public <T, E extends Exception> T doInTransaction(UnitOfWork<T, E> unitOfWork) throws E;
}

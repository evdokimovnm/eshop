package net.evdokimov.eshop.dao.impl.jdbc.tx;

import java.util.concurrent.Callable;

public interface TransactionManager {

    public <T, E extends Exception> T doInTransaction(UnitOfWork<T, E> unitOfWork) throws E;
}

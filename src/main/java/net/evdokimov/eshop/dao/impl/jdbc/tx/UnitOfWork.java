package net.evdokimov.eshop.dao.impl.jdbc.tx;

/**
 * Created by 1 on 22.03.2015.
 */
public interface UnitOfWork<T, E extends Exception> {
    public T doInTx() throws E;
}

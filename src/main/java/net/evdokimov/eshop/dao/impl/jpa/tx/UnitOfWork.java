package net.evdokimov.eshop.dao.impl.jpa.tx;

import javax.persistence.EntityManager;


public interface UnitOfWork<T, E extends Exception> {
    public T doInTx(EntityManager manager) throws E;
}

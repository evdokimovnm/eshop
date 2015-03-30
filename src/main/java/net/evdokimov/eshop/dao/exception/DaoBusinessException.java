package net.evdokimov.eshop.dao.exception;

/**
 * Created by Nikita on 04.03.2015.
 */
public class DaoBusinessException extends DaoException {
    public DaoBusinessException(String message) {
        super(message);
    }

    public DaoBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}

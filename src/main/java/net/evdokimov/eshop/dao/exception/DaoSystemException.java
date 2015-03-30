package net.evdokimov.eshop.dao.exception;

/**
 * Created by Nikita on 04.03.2015.
 */
public class DaoSystemException extends DaoException {
    public DaoSystemException(String message) {
        super(message);
    }

    public DaoSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}

package net.evdokimov.eshop.dao.exception;

/**
 * Created by Nikita on 04.03.2015.
 */
public class DaoException extends Exception {

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}

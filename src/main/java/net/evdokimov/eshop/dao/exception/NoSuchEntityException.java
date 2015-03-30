package net.evdokimov.eshop.dao.exception;

/**
 * Created by Nikita on 04.03.2015.
 */
public class NoSuchEntityException extends DaoBusinessException {

    public NoSuchEntityException(String message) {
        super(message);
    }

    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}

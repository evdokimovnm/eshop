package net.evdokimov.eshop.aspect;

import org.aspectj.lang.JoinPoint;

/**
 * Created by Nikita on 12.03.2015.
 */
public class SimpleExceptionLogger {

    public void logException(JoinPoint joinPoint, Throwable t) {
        System.out.println("ASPECT.EXCEPTION-LOGGER " + t.getMessage());
    }
}

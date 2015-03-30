package net.evdokimov.eshop.inject;

import java.lang.annotation.*;

@Documented//использование @Inject будет отображаться в javadoc
@Target(ElementType.FIELD)//@Inject можно вешать на поля
@Retention(RetentionPolicy.RUNTIME)//@Inject будет загружена в JVM
public @interface Inject {
    public String value();//значение по умолчанию
}

package net.evdokimov.eshop.validate;




import net.evdokimov.eshop.entity.User;

import java.util.Map;

public interface UserValidator {
    Map<String, String> validate(User user);
}

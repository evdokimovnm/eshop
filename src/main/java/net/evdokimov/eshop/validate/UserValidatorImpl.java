package net.evdokimov.eshop.validate;



import net.evdokimov.eshop.entity.User;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidatorImpl implements UserValidator {
    private String emailPattern;
    private int minLoginLength;
    private int maxLoginLength;
    private int minPasswordLength;
    private int maxPasswordLength;

    public void setEmailPattern(String emailPattern) {
        this.emailPattern = emailPattern;
    }

    public void setMinLoginLength(int minLoginLength) {
        this.minLoginLength = minLoginLength;
    }

    public void setMaxLoginLength(int maxLoginLength) {
        this.maxLoginLength = maxLoginLength;
    }

    public void setMinPasswordLength(int minPasswordLength) {
        this.minPasswordLength = minPasswordLength;
    }

    public void setMaxPasswordLength(int maxPasswordLength) {
        this.maxPasswordLength = maxPasswordLength;
    }

    @Override
    public Map<String, String> validate(User user) {
        HashMap<String, String> errorMap = new HashMap<>();
        validateEMail(user.getEmail(), errorMap);
        validateLogin(user.getLogin(), errorMap);
        validatePassword(user.getPassword(), errorMap);
        return errorMap;
    }

    private void validatePassword(String password, HashMap<String, String> errorMap) {
        if(password == null || password.trim().isEmpty()) {
            errorMap.put("password", "Bad password: empty.");
            return;
        }
        if (password.length() < minPasswordLength) {
            errorMap.put("password", "Bad password: too short.");
            return;
        }
        if (password.length() > maxPasswordLength) {
            errorMap.put("password", "Bad password: too long.");
        }
    }

    private void validateLogin(String login, HashMap<String, String> errorMap) {
        if(login == null || login.trim().isEmpty()) {
            errorMap.put("login", "Bad login: empty.");
            return;
        }
        if (login.length() < minLoginLength) {
            errorMap.put("login", "Bad login: too short.");
            return;
        }
        if (login.length() > maxLoginLength) {
            errorMap.put("login", "Bad login: too long.");
        }
    }

    private void validateEMail(String email, HashMap<String, String> errorMap) {
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) {
            errorMap.put("email", "Bad email.");
        }
    }
}

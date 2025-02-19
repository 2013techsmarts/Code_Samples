package co.smarttechie.rules;

import co.smarttechie.model.User;

public class PasswordRule implements IValidateRule {

    @Override
    public boolean validate(User user) {
        String password = user.password();
        return password.length() >= 8 && password.matches(".*[!@#$%^&*].*");
    }

    @Override
    public String errorMessage() {
        return "Password must be at least 8 characters long and contain at least one special character.";
    }
}

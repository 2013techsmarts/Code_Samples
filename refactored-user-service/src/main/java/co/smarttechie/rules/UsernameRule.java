package co.smarttechie.rules;

import co.smarttechie.model.User;

public class UsernameRule implements IValidateRule {
    @Override
    public boolean validate(User user) {
        return user.username().length() >= 5;
    }

    @Override
    public String errorMessage() {
        return "Username must be at least 5 characters long.";
    }
}

package co.smarttechie.rules;

import co.smarttechie.model.User;

public class AgeRule implements IValidateRule {

    @Override
    public boolean validate(User user) {
        return user.age() >= 18;
    }

    @Override
    public String errorMessage() {
        return "User must be at least 18 years old.";
    }
}

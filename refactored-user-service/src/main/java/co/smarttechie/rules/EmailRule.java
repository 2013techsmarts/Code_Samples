package co.smarttechie.rules;

import co.smarttechie.model.User;

public class EmailRule implements IValidateRule {

    @Override
    public boolean validate(User user) {
        String email = user.email();
        return email.contains("@") && email.contains(".");
    }

    @Override
    public String errorMessage() {
        return "Email must be in a valid format.";
    }
}

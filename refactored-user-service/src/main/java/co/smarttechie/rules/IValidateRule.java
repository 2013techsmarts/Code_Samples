package co.smarttechie.rules;

import co.smarttechie.model.User;

public interface IValidateRule {
    boolean validate(User user);
    String errorMessage();
}

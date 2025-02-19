package co.smarttechie.service;

import co.smarttechie.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    /**
     * This method validates User details, if any validations fails, the registration won't proceed
     * @param user
     * @return
     */
    public List<String> validateUser(User user) {

        List<String> errors = new ArrayList<>();

        if (user.username().length() < 5) {
            errors.add("Username must be at least 5 characters long.");
        }

        if (user.password().length() < 8 || !user.password().matches(".*[!@#$%^&*].*")) {
            errors.add("Password must be at least 8 characters long and contain at least one special character.");
        }

        if (!user.email().contains("@") || !user.email().contains(".")) {
            errors.add("Email must be in a valid format.");
        }

        if (user.age() < 18) {
            errors.add("User must be at least 18 years old.");
        }

        return errors;
    }

    //All other methods goes here
}

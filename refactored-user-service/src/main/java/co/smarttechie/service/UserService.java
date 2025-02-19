package co.smarttechie.service;

import co.smarttechie.model.User;
import co.smarttechie.rules.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final Evaluator evaluator;

    public UserService() {
        // Initialize the evaluator with all validation rules
        this.evaluator = new Evaluator(List.of(
                new UsernameRule(),
                new PasswordRule(),
                new EmailRule(),
                new AgeRule()
        ));
    }


    /**
     * This method validates User details, if any validations fails, the registration won't proceed
     * @param user
     * @return
     */
    public List<String> validateUser(User user) {
        return evaluator.evaluate(user);
    }

    //All other methods goes here
}

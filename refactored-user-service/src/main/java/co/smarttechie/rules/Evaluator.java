package co.smarttechie.rules;

import co.smarttechie.model.User;

import java.util.ArrayList;
import java.util.List;

public class Evaluator {

    private final List<IValidateRule> rules;

    public Evaluator(List<IValidateRule> rules) {
        this.rules = rules;
    }

    public List<String> evaluate(User user) {
        List<String> errors = new ArrayList<>();
        //Iterate the list of rules to perform the validations.
        //Collect error message if any validation fails.
        rules.stream()
                .filter(rule -> !rule.validate(user))
                .map(IValidateRule::errorMessage)
                .forEach(errors::add);

        return errors;
    }
}

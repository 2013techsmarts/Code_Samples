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
        for (IValidateRule rule : rules) {
            if (!rule.validate(user)) {
                errors.add(rule.errorMessage());
            }
        }
        return errors;
    }
}

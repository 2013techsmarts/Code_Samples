package co.smarttechie.controller;

import co.smarttechie.model.User;
import co.smarttechie.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserRegistrationController {
    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This API is used to register User with details.
     * Before registering it validates the User details
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        //Validate User details
        List<String> errors = userService.validateUser(user);

        if (!errors.isEmpty()) {
            //If any validation fails, inform user
            return ResponseEntity.badRequest().body(errors);
        }

        //If not validation errors, proceed with registration
        return ResponseEntity.ok(Map.of("message","Registration successful!"));
    }
}

package com.project.food_order_app.user;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @CrossOrigin
    @PostMapping
    User authenticateUser(
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        HttpServletResponse response
    ) {
        return userService.authenticateUser(email, password, response);
    }

    @PostMapping("/signup")
    User addUser(@RequestBody User user) {
        System.out.println("Sign up");
        return userService.addUser(user.getUid(), user.getEmail(), user.getPassword());
    }
}

package com.project.food_order_app.user;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @CrossOrigin
    @PostMapping("/login/credentials")
    User authenticateUserWithCredentials(
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        HttpServletResponse response
    ) {
        return userService.authenticateUserWithCredentials(email, password, response);
    }

    @CrossOrigin
    @PostMapping("/login/provider")
    User authenticateUserByProvider(
        @RequestParam("uid") String uid,
        HttpServletResponse response
    ) {
        return userService.authenticateUserByProvider(uid, response);
    }

    @PostMapping("/signup")
    User addUser(@RequestBody User user) {
        return userService.addUser(user.getUid(), user.getEmail(), user.getPassword());
    }

    @PostMapping("/{uid}/cart/add")
    Cart addFoodToCart(
        @PathVariable("uid") String uid,
        @RequestBody Cart food,
        HttpServletResponse res) {
        return userService.addFoodToCart(uid, food, res);
    }

    @GetMapping("/{uid}/cart/get")
    List<Cart> getFoodsInCart(@PathVariable("uid") String userId) {
        return userService.getFoodsInCart(userId);
    }

    @PatchMapping("/{uid}/cart/update-quality")
    Cart updateCartItemQuality(
        @PathVariable("uid") String uid,
        @RequestParam("title") String title,
        @RequestParam("quality") int quality) {
        return userService.updateCartItemQuality(uid, title, quality);
    }

    @PatchMapping("/{uid}/cart/remove")
    Cart removeCartItem(
        @PathVariable("uid") String uid,
        @RequestParam("title") String title) {
        return userService.removeCartItem(uid, title);
    }
}

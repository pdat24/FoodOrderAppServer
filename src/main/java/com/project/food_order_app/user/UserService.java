package com.project.food_order_app.user;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Authenticate user,
     * send status code 200 if successful,
     * 409 if invalid password,
     * 401 if not existing
     *
     * @return the user if exists else null
     */
    public User authenticateUserWithCredentials(
        String email, String password, HttpServletResponse response) {
        Query matchEmailOnly = new Query();
        Query matchEmailAndPassword = new Query();
        matchEmailOnly
            .addCriteria(Criteria.where("email").is(email))
            .addCriteria(Criteria.where("password").ne(password));
        matchEmailAndPassword
            .addCriteria(Criteria.where("email").is(email))
            .addCriteria(Criteria.where("password").is(password));
        User user = mongoTemplate.findOne(matchEmailAndPassword, User.class);
        if (user != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            return user;
        } else if (mongoTemplate.findOne(matchEmailOnly, User.class) != null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        } else response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return null;
    }

    public User addUser(String uid, String email, String password) {
        User user = new User(uid, email, password);
        userRepository.insert(user);
        return user;
    }

    public User authenticateUserByProvider(String uid, HttpServletResponse response) {
        Query matchUid = new Query();
        matchUid.addCriteria(Criteria.where("uid").is(uid));
        User user = mongoTemplate.findOne(matchUid, User.class);
        if (user != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            return user;
        } else response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return null;
    }

    public Cart addFoodToCart(String uid, Cart food, HttpServletResponse res) {
        User user = mongoTemplate.findOne(
            new Query().addCriteria(Criteria.where("uid").is(uid)),
            User.class
        );
        if (user != null) {
            List<Cart> cartItems = user.getCart();
            boolean isExisted = false;
            for (Cart i : cartItems) {
                if (i.getTitle().equals(food.getTitle())) {
                    isExisted = true;
                    i.setAmount(i.getAmount() + food.getAmount());
                    break;
                }
            }
            if (!isExisted)
                cartItems.add(food);
            user.setCart(cartItems);
            userRepository.save(user);
            return food;
        }
        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }

    public List<Cart> getFoodsInCart(String uid) {
        User user = mongoTemplate.findOne(
            new Query().addCriteria(Criteria.where("uid").is(uid)),
            User.class
        );
        if (user != null) {
            List<Cart> res = user.getCart();
            Collections.reverse(res);
            return res;
        }
        return new ArrayList<>();
    }

    public Cart updateCartItemQuality(String uid, String title, int quality) {
        User user = mongoTemplate.findOne(
            new Query().addCriteria(Criteria.where("uid").is(uid)),
            User.class);
        if (user != null) {
            Cart result = null;
            List<Cart> tmp = user.getCart();
            for (Cart i : tmp) {
                if (i.getTitle().equals(title)) {
                    i.setAmount(quality);
                    result = i;
                    break;
                }
            }
            userRepository.save(user);
            return result;
        }
        return null;
    }

    public Cart removeCartItem(String uid, String title) {
        User user = mongoTemplate.findOne(
            new Query().addCriteria(Criteria.where("uid").is(uid)),
            User.class);
        if (user != null) {
            Cart result = null;
            List<Cart> tmp = user.getCart();
            for (Cart i : tmp) {
                if (i.getTitle().equals(title)) {
                    tmp.remove(i);
                    result = i;
                    break;
                }
            }
            userRepository.save(user);
            return result;
        }
        return null;
    }
}

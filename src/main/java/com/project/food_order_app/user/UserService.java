package com.project.food_order_app.user;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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
    public User authenticateUser(
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
            response.setStatus(200);
            return user;
        } else if (mongoTemplate.findOne(matchEmailOnly, User.class) != null) {
            response.setStatus(409);
        } else response.setStatus(401);
        return null;
    }

    public User addUser(String uid, String email, String password) {
        User user = new User(uid, email, password);
        userRepository.insert(user);
        return user;
    }
}

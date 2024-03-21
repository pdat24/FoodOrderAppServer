package com.project.food_order_app.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    public Food getFoodById(String id) {
        Optional<Food> food = foodRepository.findById(id);
        return food.orElse(null);
    }

    public List<Food> getBestFoods() {
        Query bestFoodFilter =
            new Query().addCriteria(Criteria.where("BestFood").is(true));
        return mongoTemplate.find(bestFoodFilter, Food.class);
    }

    public List<Food> getFoodByCategory(int categoryId) {
        return mongoTemplate.find(
            new Query().addCriteria(Criteria.where("CategoryId").is(categoryId)),
            Food.class
        );
    }

    public List<Food> getAllFood(String keyword) {
        return foodRepository.findAll();
    }
}

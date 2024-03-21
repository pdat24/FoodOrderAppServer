package com.project.food_order_app.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/food")
public class FoodController {

    @Autowired
    FoodService foodService;

    @GetMapping("/{id}")
    Food getFoodById(@PathVariable("id") String id) {
        return foodService.getFoodById(id);
    }

    @GetMapping("/best")
    List<Food> getBestFoods() {
        return foodService.getBestFoods();
    }

    @GetMapping("/category/{id}")
    List<Food> getFoodsByCategory(@PathVariable("id") int categoryId) {
        return foodService.getFoodByCategory(categoryId);
    }

    @GetMapping("/query")
    List<Food> getAllFood(@RequestParam("keyword") String keyword) {
        List<Food> foods = foodService.getAllFood(keyword);
        List<Food> result = new ArrayList<>();
        for (Food f : foods) {
            if (f.getTitle().toLowerCase().contains(keyword))
                result.add(f);
        }
        return result;
    }
}

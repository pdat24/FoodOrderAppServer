package com.project.food_order_app.food;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Food {
    @Id
    private String id;
    private int CategoryId;
    private String Description;
    private double Price;
    private double Star;
    private boolean BestFood;
    private String ImagePath;
    private long TimeId;
    private String Title;
    private long TimeValue;

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int value) {
        this.CategoryId = value;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String value) {
        this.Description = value;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double value) {
        this.Price = value;
    }

    public double getStar() {
        return Star;
    }

    public void setStar(double value) {
        this.Star = value;
    }

    public boolean getBestFood() {
        return BestFood;
    }

    public void setBestFood(boolean value) {
        this.BestFood = value;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String value) {
        this.ImagePath = value;
    }

    public long getTimeId() {
        return TimeId;
    }

    public void setTimeId(long value) {
        this.TimeId = value;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String value) {
        this.Title = value;
    }

    public String getId() {
        return id;
    }

    public long getTimeValue() {
        return TimeValue;
    }

    public void setTimeValue(long value) {
        this.TimeValue = value;
    }
}

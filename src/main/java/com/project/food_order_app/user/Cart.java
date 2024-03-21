package com.project.food_order_app.user;

public class Cart {
    private String Description;
    private double Price;
    private double Star;
    private String ImagePath;
    private long TimeId;
    private String Title;
    private int Amount;

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

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        this.Amount = amount;
    }
}

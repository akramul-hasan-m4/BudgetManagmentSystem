package com.example.dolphin.budgetmanagmentsystem.model;

/**
 * Created by DOLPHIN on 12/21/2017.
 */

public class Income {

    private String date,category,title,comments;
    private double amount;

    public Income() {
    }

    public Income(String date, String category, String title, String comments, double amount) {
        this.date = date;
        this.category = category;
        this.title = title;
        this.comments = comments;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Income{" +
                "date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", comments='" + comments + '\'' +
                ", amount=" + amount +
                '}';
    }
}

package com.example.dolphin.budgetmanagmentsystem.model;

/**
 * Created by DOLPHIN on 12/21/2017.
 */

public class ExpenseModel {

    private String date,category,title,comments;
    private double ampount;

    public ExpenseModel() {
    }

    public ExpenseModel(String date, String category, String title, String comments, double ampount) {
        this.date = date;
        this.category = category;
        this.title = title;
        this.comments = comments;
        this.ampount = ampount;
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

    public double getAmpount() {
        return ampount;
    }

    public void setAmpount(double ampount) {
        this.ampount = ampount;
    }

    @Override
    public String toString() {
        return "ExpenseModel{" +
                "date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", comments='" + comments + '\'' +
                ", ampount=" + ampount +
                '}';
    }
}

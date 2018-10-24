package com.example.dolphin.budgetmanagmentsystem.model;

/**
 * Created by DOLPHIN on 12/22/2017.
 */

public class BudgetModel {

    private String budget_cat;
    private String budget_start;
    private String budget_end;
    private double budget_amount;

    public BudgetModel() {
    }

    public BudgetModel(String budget_cat, String budget_start, String budget_end, double budget_amount) {
        this.budget_cat = budget_cat;
        this.budget_start = budget_start;
        this.budget_end = budget_end;
        this.budget_amount = budget_amount;
    }

    public String getBudget_cat() {
        return budget_cat;
    }

    public void setBudget_cat(String budget_cat) {
        this.budget_cat = budget_cat;
    }

    public String getBudget_start() {
        return budget_start;
    }

    public void setBudget_start(String budget_start) {
        this.budget_start = budget_start;
    }

    public String getBudget_end() {
        return budget_end;
    }

    public void setBudget_end(String budget_end) {
        this.budget_end = budget_end;
    }

    public double getBudget_amount() {
        return budget_amount;
    }

    public void setBudget_amount(double budget_amount) {
        this.budget_amount = budget_amount;
    }

    @Override
    public String toString() {
        return "BudgetModel{" +
                "budget_cat='" + budget_cat + '\'' +
                ", budget_start='" + budget_start + '\'' +
                ", budget_end='" + budget_end + '\'' +
                ", budget_amount=" + budget_amount +
                '}';
    }
}

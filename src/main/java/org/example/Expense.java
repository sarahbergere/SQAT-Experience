package org.example;

public class Expense {
    String date;
    double amount;
    String category;

    Expense(String date, double amount, String category) {
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }
}
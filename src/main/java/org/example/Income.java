package org.example;

public class Income {
    String date;
    double amount;
    String source;

    Income(String date, double amount, String source) {
        this.date = date;
        this.amount = amount;
        this.source = source;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getSource() {
        return source;
    }
}


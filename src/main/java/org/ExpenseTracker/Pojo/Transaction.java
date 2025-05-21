package org.ExpenseTracker.Pojo;

import java.time.LocalDate;

public class Transaction {
    public enum Type { INCOME, EXPENSE }

    private Type type;
    private LocalDate date;
    private String category;
    private double amount;

    public Transaction(Type type, LocalDate date, String category, double amount) {
        this.type = type;
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public Type getType() { return type; }
    public LocalDate getDate() { return date; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return type + "," + date + "," + category + "," + amount;
    }
}

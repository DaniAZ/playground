package com.backend.backend;

public class Transactions{

    private double amount;
    private String time;

    public Transactions() {
    }
    
    public Transactions(double amount, String time) {
        this.amount = amount;
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Transactions [amount=" + amount + ", time=" + time + "]";
    }
}


package com.shoppingcart.customer;

public class Customer {
    CustomerType type;
    int amount ;

    @Override
    public String toString() {
        return "Customer{" +
                "type=" + type +
                ", amount=" + amount +
                '}';
    }

    public Customer(CustomerType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public CustomerType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
}

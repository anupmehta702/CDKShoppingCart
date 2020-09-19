package com.shoppingCart.customer;

public class Customer {
    CustomerType type;
    int amount ;

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

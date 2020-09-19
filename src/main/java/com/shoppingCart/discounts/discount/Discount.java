package com.shoppingCart.discounts.discount;

import com.shoppingCart.customer.Customer;
import com.shoppingCart.customer.CustomerType;

public abstract class Discount {
    int lowRangeBillAmount = 0;
    int highRangeBillAmount = 0;
    int discountPercentage = 0;
    public CustomerType discountFor;

    public Discount(int lowRangeBillAmount, int highRangeBillAmount, int discountPercentage,CustomerType discountFor) {
        this.lowRangeBillAmount = lowRangeBillAmount;
        this.highRangeBillAmount = highRangeBillAmount;
        this.discountPercentage = discountPercentage;
        this.discountFor = discountFor;
    }

    abstract int calculateDiscount(Customer customer);

    protected boolean isDiscountValidFor(Customer customer) {
        return true;
    }
}

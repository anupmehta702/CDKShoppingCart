package com.shoppingCart.discounts.discount;

import com.shoppingCart.customer.Customer;
import com.shoppingCart.customer.CustomerType;

public class CustomerDiscount implements Discount {
    int lowRangeBillAmount = 0;
    int highRangeBillAmount = 0;
    int discountPercentage = 0;
    public CustomerType discountFor;

    public CustomerDiscount(int lowRangeBillAmount, int highRangeBillAmount, int discountPercentage,CustomerType discountFor) {
        this.lowRangeBillAmount = lowRangeBillAmount;
        this.highRangeBillAmount = highRangeBillAmount;
        this.discountPercentage = discountPercentage;
        this.discountFor = discountFor;
    }

    public int calculateDiscount(Customer customer) {

        return 0;
    }

    @Override
    public String toString() {
        return "CustomerDiscount{" +
                "lowRangeBillAmount=" + lowRangeBillAmount +
                ", highRangeBillAmount=" + highRangeBillAmount +
                ", discountPercentage=" + discountPercentage +
                ", discountFor=" + discountFor +
                '}';
    }
}

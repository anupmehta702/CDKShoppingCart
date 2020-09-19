package com.shoppingCart.discounts.discount;

import com.shoppingCart.customer.Customer;
import com.shoppingCart.customer.CustomerType;

public class CustomerDiscount extends Discount {

    public CustomerDiscount(int lowRangeBillAmount, int highRangeBillAmount, int discountPercentage,CustomerType discountFor) {
        super(lowRangeBillAmount,highRangeBillAmount,discountPercentage,discountFor);
    }

    public int calculateDiscount(Customer customer) {
        isDiscountValidFor(customer);
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

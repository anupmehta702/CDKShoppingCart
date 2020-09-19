package com.shoppingCart.discounts;

import com.shoppingCart.customer.CustomerType;
import com.shoppingCart.discounts.discount.Discount;

import java.util.ArrayList;
import java.util.List;

public class Discounts {
    private CustomerType customerType ;
    private List<Discount> discounts = new ArrayList();

    public Discounts(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Override
    public String toString() {
        return "Discounts{" +
                "customerType=" + customerType +
                '}';
    }

}

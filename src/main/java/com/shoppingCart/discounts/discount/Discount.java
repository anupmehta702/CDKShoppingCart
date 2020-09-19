package com.shoppingCart.discounts.discount;

import com.shoppingCart.customer.Customer;

public interface Discount {
    int calculateDiscount(Customer customer);
}

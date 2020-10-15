package com.shoppingcart;

import com.shoppingcart.customer.Customer;
import com.shoppingcart.discounts.Discounts;

public class BillCounter {
    private final Discounts discounts;

    public BillCounter(Discounts discounts) {
        this.discounts = discounts;
    }

    public int calculateBill(Customer customer){
        if(customer == null || customer.getAmount() == 0 ){
            return 0;
        }
        return customer.getAmount() - discounts.calculateTotalDiscount(customer);
    }
}

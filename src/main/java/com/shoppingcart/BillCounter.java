package com.shoppingcart;

import com.shoppingcart.customer.Customer;
import com.shoppingcart.customer.CustomerType;
import com.shoppingcart.discounts.CustomerDiscount;
import com.shoppingcart.discounts.Discounts;

import java.util.List;
import java.util.Map;

public class BillCounter {
    private final Discounts discounts;

    public BillCounter(Discounts discounts) {
        this.discounts = discounts;
    }

    public void printCurrentDiscountDetails() {
        Map<CustomerType, List<CustomerDiscount>> discountMap = discounts.getExistingDiscounts();
        discountMap.forEach((key, value) -> {
            System.out.println("Discounts for " + key);
            System.out.println(value);
        });
    }

        public int calculateBill (Customer customer){
            if (customer == null || customer.getAmount() == 0) {
                return 0;
            }
            return customer.getAmount() - discounts.calculateTotalDiscount(customer);
        }
    }

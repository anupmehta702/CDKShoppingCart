package com.shoppingCart.discounts.discount;

import com.shoppingCart.customer.Customer;
import com.shoppingCart.customer.CustomerType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerDiscountTest {

    @Test
    public void testCalculateDiscountWithinRange() {
        CustomerDiscount customerDiscount = new CustomerDiscount(1000,4000,10,CustomerType.PREMIUM);
        Customer customer = new Customer(CustomerType.PREMIUM,2000);
        assertEquals(100,customerDiscount.calculateDiscount(customer));
    }

    @Test
    public void testCalculateDiscountOutsideRange() {
        CustomerDiscount customerDiscount = new CustomerDiscount(1000,4000,10,CustomerType.PREMIUM);
        Customer customer = new Customer(CustomerType.PREMIUM,500);
        assertEquals(0,customerDiscount.calculateDiscount(customer));
    }

    @Test
    public void testToString() {
        CustomerDiscount customerDiscount = new CustomerDiscount(0,1000,10,CustomerType.PREMIUM);
        assertEquals("CustomerDiscount{lowRangeBillAmount=0, highRangeBillAmount=1000, discountPercentage=10, discountFor=PREMIUM}",customerDiscount.toString());
    }
}
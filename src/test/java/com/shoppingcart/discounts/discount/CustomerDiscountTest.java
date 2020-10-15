package com.shoppingcart.discounts.discount;

import com.shoppingcart.customer.Customer;
import com.shoppingcart.discounts.CustomerDiscount;
import org.junit.Test;

import static com.shoppingcart.customer.CustomerType.PREMIUM;
import static com.shoppingcart.customer.CustomerType.REGULAR;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CustomerDiscountTest {

    private CustomerDiscount customerDiscount = new CustomerDiscount(1000, 2000, 10, PREMIUM);

    @Test
    public void testCalculateDiscountWithinRange() {
        Customer customer = new Customer(PREMIUM, 1600);
        assertEquals(60, customerDiscount.calculateDiscount(customer));
    }

    @Test
    public void testCalculateDiscountForAmountMoreThanRange() {
        Customer customer = new Customer(PREMIUM, 2600);
        assertEquals(100, customerDiscount.calculateDiscount(customer));
    }

    @Test
    public void testCalculateDiscountLessThanLowerRange() {
        CustomerDiscount customerDiscount = new CustomerDiscount(1000, 4000, 10, PREMIUM);
        Customer customer = new Customer(PREMIUM, 500);
        assertEquals(0, customerDiscount.calculateDiscount(customer));
    }

    @Test
    public void testCalculateDiscountForAmountEqualToZero() {
        CustomerDiscount customerDiscount = new CustomerDiscount(1000, 4000, 10, PREMIUM);
        Customer customer = new Customer(PREMIUM, 0);
        assertEquals(0, customerDiscount.calculateDiscount(customer));
    }

    @Test
    public void isDiscountValidForTestForZeroAmount() {
        Customer customer = new Customer(PREMIUM, 0);
        assertFalse(customerDiscount.isDiscountValidFor(customer));
    }

    @Test
    public void isDiscountValidForTestForValidCustomerType() {
        Customer customer = new Customer(REGULAR, 500);
        assertFalse(customerDiscount.isDiscountValidFor(customer));
         customer = new Customer(PREMIUM, 2500);
        assertTrue(customerDiscount.isDiscountValidFor(customer));
    }

    @Test
    public void isDiscountValidForTestForAmountLessThanLowerRange() {
        Customer customer = new Customer(PREMIUM, 500);
        assertFalse(customerDiscount.isDiscountValidFor(customer));
    }

    @Test
    public void isDiscountValidForTestForAmountValidRange() {
        Customer customer = new Customer(PREMIUM, 1500);
        assertTrue(customerDiscount.isDiscountValidFor(customer));
    }

    @Test
    public void testToString() {
        CustomerDiscount customerDiscount = new CustomerDiscount(0, 1000, 10, PREMIUM);
        assertEquals("CustomerDiscount{lowRangeBillAmount=0, highRangeBillAmount=1000, discountPercentage=10, discountFor=PREMIUM}", customerDiscount.toString());
    }
}
package com.shoppingCart.discounts.discount;

import com.shoppingCart.customer.Customer;
import org.junit.Test;

import static com.shoppingCart.customer.CustomerType.PREMIUM;
import static com.shoppingCart.customer.CustomerType.REGULAR;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CustomerDiscountTest {

    private CustomerDiscount customerDiscount = new CustomerDiscount(1000, 2000, 10, PREMIUM);

    @Test
    public void testCalculateDiscountWithinRange() {
        Customer customer = new Customer(PREMIUM, 1500);
        assertEquals(100, customerDiscount.calculateDiscount(customer));
    }

    @Test
    public void testCalculateDiscountOutsideRange() {
        CustomerDiscount customerDiscount = new CustomerDiscount(1000, 4000, 10, PREMIUM);
        Customer customer = new Customer(PREMIUM, 500);
        assertEquals(0, customerDiscount.calculateDiscount(customer));
    }

    @Test
    public void isDiscountValidForTestForZeroAmount() {
        Customer customer = new Customer(PREMIUM, 0);
        assertFalse(customerDiscount.isDiscountValidFor(customer));
    }

    @Test
    public void isDiscountValidForTestForCustomerType() {
        Customer customer = new Customer(REGULAR, 500);
        assertFalse(customerDiscount.isDiscountValidFor(customer));
    }

    @Test
    public void isDiscountValidForTestForAmountLessThanLowerRange() {
        Customer customer = new Customer(REGULAR, 500);
        assertFalse(customerDiscount.isDiscountValidFor(customer));
    }

    @Test
    public void isDiscountValidForTestForAmountValidRange() {
        Customer customer = new Customer(REGULAR, 1500);
        assertTrue(customerDiscount.isDiscountValidFor(customer));
    }

    @Test
    public void testToString() {
        CustomerDiscount customerDiscount = new CustomerDiscount(0, 1000, 10, PREMIUM);
        assertEquals("CustomerDiscount{lowRangeBillAmount=0, highRangeBillAmount=1000, discountPercentage=10, discountFor=PREMIUM}", customerDiscount.toString());
    }
}
package com.shoppingcart.discounts.discount;

import com.shoppingcart.customer.Customer;
import com.shoppingcart.discounts.CustomerDiscount;
import org.junit.Test;

import static com.shoppingcart.customer.CustomerType.PREMIUM;
import static com.shoppingcart.customer.CustomerType.REGULAR;
import static java.lang.Integer.MAX_VALUE;
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
    public void isRangeWithinTest(){
        CustomerDiscount lowRangeExistingDiscount = new CustomerDiscount(0,1000,10,PREMIUM);
        assertFalse(customerDiscount.isRangeWithin(lowRangeExistingDiscount));


        CustomerDiscount highRangeExistingDiscount = new CustomerDiscount(2000,3000,10,PREMIUM);
        assertFalse(customerDiscount.isRangeWithin(highRangeExistingDiscount));

        CustomerDiscount highRangeWithinExistingDiscount = new CustomerDiscount(500,1500,10,PREMIUM);
        assertTrue(customerDiscount.isRangeWithin(highRangeWithinExistingDiscount));

        CustomerDiscount lowRangeWithinExistingDiscount = new CustomerDiscount(1500,2500,10,PREMIUM);
        assertTrue(customerDiscount.isRangeWithin(lowRangeWithinExistingDiscount));
    }

    @Test
    public void isRangeSubsetOfTest(){
        CustomerDiscount nonSubsetExistingDiscount = new CustomerDiscount(2000, MAX_VALUE,10,PREMIUM);
        assertFalse("Existing discount is not subset of customer discount",customerDiscount.isRangeSuperSetOf(nonSubsetExistingDiscount));

        CustomerDiscount equalRangeExistingSubsetDiscount = new CustomerDiscount(2000,3000,10,PREMIUM);
        assertFalse("Existing discount has same range as customer discount",customerDiscount.isRangeSuperSetOf(equalRangeExistingSubsetDiscount));

        CustomerDiscount subsetExistingDiscount = new CustomerDiscount(1500,1600,10,PREMIUM);
        assertTrue("Existing discount is a subset of customer discount",customerDiscount.isRangeSuperSetOf(subsetExistingDiscount));

    }

}
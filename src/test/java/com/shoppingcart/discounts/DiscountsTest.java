package com.shoppingcart.discounts;

import com.shoppingcart.customer.Customer;
import com.shoppingcart.discounts.discount.CustomerDiscount;
import com.shoppingcart.discounts.exception.AddDiscountException;
import com.shoppingcart.discounts.exception.InvalidDiscountRangeException;
import com.shoppingcart.discounts.exception.OverlappingDiscountAddedException;
import org.junit.Test;

import static com.shoppingcart.customer.CustomerType.PREMIUM;
import static com.shoppingcart.customer.CustomerType.REGULAR;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DiscountsTest {
    private final Discounts discounts = new Discounts();
    private final CustomerDiscount customDiscount = new CustomerDiscount(1000, 2000, 10, PREMIUM);

    @Test
    public void addDiscountTest() throws AddDiscountException {
        assertTrue(discounts.addDiscount(customDiscount));
        assertTrue(discounts.getExistingDiscounts().size() > 0);
        assertTrue(discounts.getExistingDiscounts().get(PREMIUM).contains(customDiscount));
    }


    @Test
    public void addDiscountTest_ValidationForRange()  {
        CustomerDiscount customDiscount = new CustomerDiscount(2000, 1000, 10, PREMIUM);
        AddDiscountException ex = assertThrows(InvalidDiscountRangeException.class, () -> discounts.addDiscount(customDiscount));
        assertEquals("Low range cannot be >= to high range of Discount", ex.getMessage());

    }

    @Test
    public void addDiscountTest_ValidationForNegativeRange()  {
        CustomerDiscount customDiscount = new CustomerDiscount(-2000, 1000, 10, PREMIUM);
        AddDiscountException ex = assertThrows(InvalidDiscountRangeException.class, () -> discounts.addDiscount(customDiscount));
        assertEquals("Low or high range of Discount cannot be <= ZERO", ex.getMessage());
    }

    @Test
    public void addDiscountTest_WithDifferentRange_ForSameType() throws AddDiscountException {
        discounts.addDiscount(customDiscount);
        CustomerDiscount premiumDiscount = new CustomerDiscount(2000, 3000, 20, PREMIUM);
        discounts.addDiscount(premiumDiscount);
        assertTrue(discounts.getExistingDiscounts().size() > 0);
        assertTrue(discounts.getExistingDiscounts().get(PREMIUM).contains(customDiscount));
    }

    @Test
    public void addDiscountTest_ForRangeBetweenExistingDiscount_ForDifferentCustomerType() throws AddDiscountException {
        discounts.addDiscount(customDiscount);
        CustomerDiscount regularDiscount = new CustomerDiscount(1500, 2500, 20, REGULAR);
        discounts.addDiscount(regularDiscount);
        assertTrue(discounts.getExistingDiscounts().get(REGULAR).contains(regularDiscount));
    }

    @Test
    public void addDiscountTest_ForLowerRangeBetweenExistingDiscount_ForSameCustomerType() throws AddDiscountException {
        discounts.addDiscount(customDiscount);
        CustomerDiscount premiumDiscount = new CustomerDiscount(1500, 2500, 20, PREMIUM);
        assertThrows(OverlappingDiscountAddedException.class, () -> discounts.addDiscount(premiumDiscount));
    }

    @Test
    public void addDiscountTest_ForHigherRangeBetweenExistingDiscount_ForSameCustomerType() throws AddDiscountException {
        discounts.addDiscount(customDiscount);
        CustomerDiscount premiumDiscount = new CustomerDiscount(500, 1500, 20, PREMIUM);
        assertThrows(OverlappingDiscountAddedException.class, () -> discounts.addDiscount(premiumDiscount));
    }

    @Test
    public void addDiscountTest_ForExistingDiscountRangeSubsetOfDiscountToAdd_ForSameCustomerType() throws AddDiscountException {
        discounts.addDiscount(customDiscount);
        CustomerDiscount premiumDiscount = new CustomerDiscount(950, 2050, 20, PREMIUM);
        assertThrows(OverlappingDiscountAddedException.class, () -> discounts.addDiscount(premiumDiscount));
    }

    @Test
    public void calculateTotalDiscountTest() throws AddDiscountException {
        CustomerDiscount premiumDiscount1 = new CustomerDiscount(0, 1000, 0, PREMIUM);
        CustomerDiscount premiumDiscount2 = new CustomerDiscount(1000, 2000, 10, PREMIUM);
        CustomerDiscount premiumDiscount3 = new CustomerDiscount(2000, 3000, 20, PREMIUM);
        discounts.addDiscount(premiumDiscount1);
        discounts.addDiscount(premiumDiscount2);
        discounts.addDiscount(premiumDiscount3);
        assertEquals(200, discounts.calculateTotalDiscount(new Customer(PREMIUM, 2500)));
    }

}

package com.shoppingcart;

import com.shoppingcart.customer.Customer;
import com.shoppingcart.discounts.CustomerDiscount;
import com.shoppingcart.discounts.Discounts;
import com.shoppingcart.discounts.exception.AddDiscountException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.shoppingcart.customer.CustomerType.PREMIUM;
import static com.shoppingcart.customer.CustomerType.REGULAR;
import static java.lang.Integer.MAX_VALUE;

public class BillCounterTest {

    private BillCounter billCounter;

    @Before
    public void setup() throws AddDiscountException {
        Discounts discounts = new Discounts();
        addRegularDiscounts(discounts);
        addPremiumDiscounts(discounts);
        billCounter = new BillCounter(discounts);
    }

    @Test
    public void calculateBillTest_ForRegularCustomer() {
        Customer regularCustomer = new Customer(REGULAR, 15000);
        Assert.assertEquals(13500,billCounter.calculateBill(regularCustomer));
    }


    @Test
    public void calculateBillTest_ForPremiumCustomer() {
        Customer premiumCustomer = new Customer(PREMIUM, 15000);
        Assert.assertEquals(12300,billCounter.calculateBill(premiumCustomer));
    }

    @Test
    public void calculateBillTest_ForZeroBillCustomer() {
        Customer premiumCustomer = new Customer(PREMIUM, 0);
        Assert.assertEquals(0,billCounter.calculateBill(premiumCustomer));
    }

    @Test
    public void calculateBillTest_ForNullCustomer() {
        Assert.assertEquals(0,billCounter.calculateBill(null));
    }

    private void addRegularDiscounts(Discounts discounts) throws AddDiscountException {
        CustomerDiscount regularNilDiscount = new CustomerDiscount(0, 5000, 0, REGULAR);
        CustomerDiscount regularTenDiscount = new CustomerDiscount(5000, 10000, 10, REGULAR);
        CustomerDiscount regularTwentyDiscount = new CustomerDiscount(10000, MAX_VALUE, 20, REGULAR);
        discounts.addDiscount(regularNilDiscount);
        discounts.addDiscount(regularTenDiscount);
        discounts.addDiscount(regularTwentyDiscount);
    }

    private  void addPremiumDiscounts(Discounts discounts) throws AddDiscountException {
        CustomerDiscount premiumTenDiscount = new CustomerDiscount(0, 4000, 10, PREMIUM);
        CustomerDiscount premiumFifteenDiscount = new CustomerDiscount(4000, 8000, 15, PREMIUM);
        CustomerDiscount premiumTwentyDiscount = new CustomerDiscount(8000, 12000, 20, PREMIUM);
        CustomerDiscount premiumThirtyDiscount = new CustomerDiscount(12000, MAX_VALUE, 30, PREMIUM);
        discounts.addDiscount(premiumTenDiscount);
        discounts.addDiscount(premiumFifteenDiscount);
        discounts.addDiscount(premiumTwentyDiscount);
        discounts.addDiscount(premiumThirtyDiscount);
    }
}
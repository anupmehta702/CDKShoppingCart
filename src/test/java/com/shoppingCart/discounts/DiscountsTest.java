package com.shoppingCart.discounts;

import org.junit.Assert;
import org.junit.Test;

import static com.shoppingCart.customer.CustomerType.PREMIUM;

public class DiscountsTest {

    @Test
     public void testToString() {
        Discounts discount = new Discounts(PREMIUM);
        Assert.assertTrue(discount!=null);
    }
}
;
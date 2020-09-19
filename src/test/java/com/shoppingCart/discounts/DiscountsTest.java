package com.shoppingCart.discounts;

import com.shoppingCart.customer.CustomerType;
import com.shoppingCart.discounts.discount.CustomerDiscount;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.shoppingCart.customer.CustomerType.PREMIUM;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class DiscountsTest {
    Discounts discounts = new Discounts();

    @Test
    public void addDiscountTest() throws NoSuchFieldException, IllegalAccessException {
        discounts = new Discounts();
        CustomerDiscount premiumDiscount = new CustomerDiscount(1000, 2000, 10, PREMIUM);
        Field privateMapField = Discounts.class.getDeclaredField("customerTypeDiscountMap");
        privateMapField.setAccessible(true);
        Map<CustomerType, List<Discounts>> discountMap = (Map<CustomerType, List<Discounts>>)
                privateMapField.get(discounts);
        assertTrue(discounts.addDiscount(premiumDiscount));
        assertTrue(discountMap.get(PREMIUM).size()>0);
        assertTrue(discountMap.get(PREMIUM).contains(premiumDiscount));
    }
}
;
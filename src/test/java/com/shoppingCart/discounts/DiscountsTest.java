package com.shoppingCart.discounts;

import com.shoppingCart.customer.Customer;
import com.shoppingCart.customer.CustomerType;
import com.shoppingCart.discounts.discount.CustomerDiscount;
import com.shoppingCart.discounts.exception.OverlappingDiscountAddedException;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.shoppingCart.customer.CustomerType.PREMIUM;
import static com.shoppingCart.customer.CustomerType.REGULAR;
import static junit.framework.TestCase.assertTrue;

public class DiscountsTest {
    private Discounts discounts = new Discounts();

    @Test
    public void addDiscountTestForPremiumDiscount() throws NoSuchFieldException, IllegalAccessException, OverlappingDiscountAddedException {
        CustomerDiscount premiumDiscount = new CustomerDiscount(1000, 2000, 10, PREMIUM);
        Field privateMapField = Discounts.class.getDeclaredField("customerTypeDiscountMap");
        privateMapField.setAccessible(true);
        Map<CustomerType, List<Discounts>> discountMap = (Map<CustomerType, List<Discounts>>)
                privateMapField.get(discounts);
        assertTrue(discounts.addDiscount(premiumDiscount));
        assertTrue(discountMap.get(PREMIUM).size() > 0);
        assertTrue(discountMap.get(PREMIUM).contains(premiumDiscount));
    }

    @Test
    public void addDiscountTestForRegularDiscount() throws NoSuchFieldException, IllegalAccessException, OverlappingDiscountAddedException {
        CustomerDiscount regularDiscount = new CustomerDiscount(1000, 2000, 10, REGULAR);
        Field privateMapField = Discounts.class.getDeclaredField("customerTypeDiscountMap");
        privateMapField.setAccessible(true);
        Map<CustomerType, List<Discounts>> discountMap = (Map<CustomerType, List<Discounts>>)
                privateMapField.get(discounts);
        assertTrue(discounts.addDiscount(regularDiscount));
        assertTrue(discountMap.get(REGULAR).size() > 0);
        assertTrue(discountMap.get(REGULAR).contains(regularDiscount));
    }

    @Test
    public void addDiscountTestForExistingRangeForRegularAccount() throws NoSuchFieldException, IllegalAccessException, OverlappingDiscountAddedException {
        addDiscountTestForRegularDiscount();
        CustomerDiscount regularDiscount = new CustomerDiscount(1500, 2500, 20, REGULAR);
        Assert.assertThrows(OverlappingDiscountAddedException.class, () -> discounts.addDiscount(regularDiscount));
    }

    @Test
    public void addDiscountTestForExistingRangeForPremiumAccount() throws NoSuchFieldException, IllegalAccessException, OverlappingDiscountAddedException {
        addDiscountTestForPremiumDiscount();
        CustomerDiscount regularDiscount = new CustomerDiscount(1500, 2500, 20, REGULAR);
        Assert.assertThrows(OverlappingDiscountAddedException.class, () -> discounts.addDiscount(regularDiscount));
    }

    @Test
    public void calculateTotalDiscountTest() throws OverlappingDiscountAddedException {
        CustomerDiscount premiumDiscount1 = new CustomerDiscount(0, 1000, 0, PREMIUM);
        CustomerDiscount premiumDiscount2 = new CustomerDiscount(1000, 2000, 10, PREMIUM);
        CustomerDiscount premiumDiscount3 = new CustomerDiscount(2000, 3000, 20, PREMIUM);
        discounts.addDiscount(premiumDiscount1);
        discounts.addDiscount(premiumDiscount2);
        discounts.addDiscount(premiumDiscount3);
        Assert.assertEquals(200, discounts.calculateTotalDiscount(new Customer(PREMIUM, 2500)));
    }

}

package com.shoppingCart.discounts;

import com.shoppingCart.customer.CustomerType;
import com.shoppingCart.discounts.discount.Discount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shoppingCart.customer.CustomerType.PREMIUM;
import static com.shoppingCart.customer.CustomerType.REGULAR;

public class Discounts {
    private Map<CustomerType, List<Discount>> customerTypeDiscountMap = new HashMap<>();

    public boolean addDiscount(Discount discount){
        if(PREMIUM == discount.discountFor){
            addDiscountFor(discount,PREMIUM);
        }else{
            addDiscountFor(discount,REGULAR);
        }
        return true;
    }

    private void addDiscountFor(Discount discount,CustomerType customerType) {
        if(customerTypeDiscountMap.containsKey(customerType)){
            List<Discount> premiumDiscount = customerTypeDiscountMap.get(discount.discountFor);
            premiumDiscount.add(discount);
        }else{
            List<Discount> premiumDiscounts = new ArrayList();
            premiumDiscounts.add(discount);
            customerTypeDiscountMap.put(customerType,premiumDiscounts);
        }
    }


}

package com.shoppingCart.discounts;

import com.shoppingCart.customer.Customer;
import com.shoppingCart.customer.CustomerType;
import com.shoppingCart.discounts.discount.Discount;
import com.shoppingCart.discounts.exception.OverlappingDiscountAddedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Discounts {
    private Map<CustomerType, List<Discount>> customerTypeDiscountMap = new HashMap<>();

     boolean addDiscount(Discount discountToAdd) throws OverlappingDiscountAddedException {
        if (isDiscountRangeAlreadyPresent(discountToAdd)) {
            throw new OverlappingDiscountAddedException("Discount to add already overlaps existing discount - " + discountToAdd);
        }
        addDiscountFor(discountToAdd);
        return true;
    }

    private void addDiscountFor(Discount discountToAdd) {

        if (customerTypeDiscountMap.containsKey(discountToAdd.discountFor)) {
            List<Discount> premiumDiscount = customerTypeDiscountMap.get(discountToAdd.discountFor);
            premiumDiscount.add(discountToAdd);
        } else {
            List<Discount> customerTypeDiscounts = new ArrayList<>();
            customerTypeDiscounts.add(discountToAdd);
            customerTypeDiscountMap.put(discountToAdd.discountFor, customerTypeDiscounts);
        }
    }

    private boolean isDiscountRangeAlreadyPresent(Discount discountToAdd) {
        if (!customerTypeDiscountMap.containsKey(discountToAdd.discountFor)) {
            return false;
        } else {
            List<Discount> customerTypeDiscounts = customerTypeDiscountMap.get(discountToAdd.discountFor);
            List<Discount> isRangePresentList = customerTypeDiscounts.stream()
                    .filter(existingDiscount -> (
                            existingDiscount.getLowRangeBillAmount() <= discountToAdd.getLowRangeBillAmount()) &&
                            existingDiscount.getHighRangeBillAmount() <= discountToAdd.getHighRangeBillAmount())
                    .collect(Collectors.toList());
            return isRangePresentList.isEmpty();
        }

    }

   /* public void checkIfDiscountIsInRange(Discount existingDiscount, Discount discountToAdd) throws OverlappingDiscountAddedException {
        if ((existingDiscount.getLowRangeBillAmount() <= discountToAdd.getLowRangeBillAmount()) &&
                discountToAdd.getLowRangeBillAmount() <= existingDiscount.getHighRangeBillAmount()) {
            throw new OverlappingDiscountAddedException("Discount to add already overlaps existing discount - " + existingDiscount);
        }

    }*/

     int calculateTotalDiscount(Customer customer) {
        List<Discount> discounts = customerTypeDiscountMap.get(customer.getType());
        int totalDiscount = 0;
        if (!discounts.isEmpty()) {
            for (Discount discount : discounts) {
                totalDiscount = totalDiscount + discount.calculateDiscount(customer);
            }
        }
        return totalDiscount;
    }

}

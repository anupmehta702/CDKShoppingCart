package com.shoppingcart.discounts;

import com.shoppingcart.customer.Customer;
import com.shoppingcart.customer.CustomerType;
import com.shoppingcart.discounts.discount.Discount;
import com.shoppingcart.discounts.exception.OverlappingDiscountAddedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Discounts {
    private final Map<CustomerType, List<Discount>> customerTypeDiscountMap = new HashMap<>();

    public Map<CustomerType, List<Discount>> getExistingDiscounts() {
        return new HashMap<>(customerTypeDiscountMap);
    }

    boolean addDiscount(Discount discountToAdd) throws OverlappingDiscountAddedException {
        if (isDiscountRangeAlreadyPresent(discountToAdd)) {
            throw new OverlappingDiscountAddedException("Discount to add already overlaps existing discount - " + discountToAdd);
        }
        addDiscountFor(discountToAdd);
        return true;
    }

    private void addDiscountFor(Discount discountToAdd) {

        if (customerTypeDiscountMap.containsKey(discountToAdd.getDiscountFor())) {
            List<Discount> premiumDiscount = customerTypeDiscountMap.get(discountToAdd.getDiscountFor());
            premiumDiscount.add(discountToAdd);
        } else {
            List<Discount> customerTypeDiscounts = new ArrayList<>();
            customerTypeDiscounts.add(discountToAdd);
            customerTypeDiscountMap.put(discountToAdd.getDiscountFor(), customerTypeDiscounts);
        }
    }

    private boolean isDiscountRangeAlreadyPresent(Discount discountToAdd) {
        if (customerTypeDiscountMap.isEmpty() || !customerTypeDiscountMap.containsKey(discountToAdd.getDiscountFor())) {
            return false;
        } else {
            List<Discount> customerTypeDiscounts = customerTypeDiscountMap.get(discountToAdd.getDiscountFor());
            List<Discount> isRangePresentList = customerTypeDiscounts
                    .stream()
                    .filter(existingDiscount -> (isLowerRangeWithinExistingDiscountRange(existingDiscount, discountToAdd.getLowRangeBillAmount()) ||
                            isHigherRangeWithinExistingDiscountRange(existingDiscount, discountToAdd.getHighRangeBillAmount()))
                    )
                    .collect(Collectors.toList());
            return !isRangePresentList.isEmpty();
        }

    }

    private boolean isHigherRangeWithinExistingDiscountRange(Discount existingDiscount, int highRangeBillAmount) {
        return existingDiscount.getLowRangeBillAmount() < highRangeBillAmount
                && highRangeBillAmount < existingDiscount.getHighRangeBillAmount();
    }

    private boolean isLowerRangeWithinExistingDiscountRange(Discount existingDiscount, int lowRangeBillAmount) {
        return existingDiscount.getLowRangeBillAmount() < lowRangeBillAmount
                && lowRangeBillAmount < existingDiscount.getHighRangeBillAmount();
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

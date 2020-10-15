package com.shoppingcart.discounts;

import com.shoppingcart.customer.Customer;
import com.shoppingcart.customer.CustomerType;
import com.shoppingcart.discounts.exception.AddDiscountException;
import com.shoppingcart.discounts.exception.InvalidDiscountRangeException;
import com.shoppingcart.discounts.exception.OverlappingDiscountAddedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Discounts {
    private final Map<CustomerType, List<CustomerDiscount>> customerTypeDiscountMap = new HashMap<>();

    public Map<CustomerType, List<CustomerDiscount>> getExistingDiscounts() {
        return new HashMap<>(customerTypeDiscountMap);
    }

    public boolean addDiscount(CustomerDiscount discountToAdd) throws AddDiscountException {
        validateDiscountForRange(discountToAdd);
        if (isDiscountRangeAlreadyPresent(discountToAdd)) {
            throw new OverlappingDiscountAddedException("Discount to add already overlaps existing discount - " + discountToAdd);
        }
        addDiscountFor(discountToAdd);
        return true;
    }

    private void validateDiscountForRange(CustomerDiscount discountToAdd) throws InvalidDiscountRangeException {
        if (discountToAdd.getLowRangeBillAmount() < 0 || discountToAdd.getHighRangeBillAmount() < 0) {
            throw new InvalidDiscountRangeException("Low or high range of Discount cannot be <= ZERO");
        }
        if (discountToAdd.getLowRangeBillAmount() >= discountToAdd.getHighRangeBillAmount()) {
            throw new InvalidDiscountRangeException("Low range cannot be >= to high range of Discount");
        }
    }

    private void addDiscountFor(CustomerDiscount discountToAdd) {

        if (customerTypeDiscountMap.containsKey(discountToAdd.getDiscountFor())) {
            List<CustomerDiscount> premiumDiscount = customerTypeDiscountMap.get(discountToAdd.getDiscountFor());
            premiumDiscount.add(discountToAdd);
        } else {
            List<CustomerDiscount> customerTypeDiscounts = new ArrayList<>();
            customerTypeDiscounts.add(discountToAdd);
            customerTypeDiscountMap.put(discountToAdd.getDiscountFor(), customerTypeDiscounts);
        }
    }

    private boolean isDiscountRangeAlreadyPresent(CustomerDiscount discountToAdd) {
        if (customerTypeDiscountMap.isEmpty() || !customerTypeDiscountMap.containsKey(discountToAdd.getDiscountFor())) {
            return false;
        } else {
            List<CustomerDiscount> customerTypeDiscounts = customerTypeDiscountMap.get(discountToAdd.getDiscountFor());
            List<CustomerDiscount> isRangePresentList = customerTypeDiscounts
                    .stream()
                    .filter(existingDiscount -> (
                            isDiscountToAddRangeWithinExistingDiscountRange(existingDiscount, discountToAdd)) ||
                            isExistingDiscountRangeSubsetOfDiscountToAddRange(existingDiscount, discountToAdd)
                    )
                    .collect(Collectors.toList());
            return !isRangePresentList.isEmpty();
        }

    }

    private boolean isExistingDiscountRangeSubsetOfDiscountToAddRange(CustomerDiscount existingDiscount, CustomerDiscount discountToAdd) {
        return discountToAdd.getLowRangeBillAmount() < existingDiscount.getLowRangeBillAmount() &&
                existingDiscount.getHighRangeBillAmount() < discountToAdd.getHighRangeBillAmount();
    }

    private boolean isDiscountToAddRangeWithinExistingDiscountRange(CustomerDiscount existingDiscount, CustomerDiscount discountToAdd) {
        return isRangeWithinExistingDiscountRange(existingDiscount, discountToAdd.getLowRangeBillAmount())
                || isRangeWithinExistingDiscountRange(existingDiscount, discountToAdd.getHighRangeBillAmount());
    }

    private boolean isRangeWithinExistingDiscountRange(CustomerDiscount existingDiscount, int highRangeBillAmount) {
        return existingDiscount.getLowRangeBillAmount() < highRangeBillAmount
                && highRangeBillAmount < existingDiscount.getHighRangeBillAmount();
    }

    public int calculateTotalDiscount(Customer customer) {
        List<CustomerDiscount> discounts = customerTypeDiscountMap.get(customer.getType());
        int totalDiscount = 0;
        if (!discounts.isEmpty()) {
            for (CustomerDiscount discount : discounts) {
                totalDiscount = totalDiscount + discount.calculateDiscount(customer);
            }
        }
        return totalDiscount;
    }

}

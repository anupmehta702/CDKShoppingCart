package com.shoppingcart.discounts;

import com.shoppingcart.customer.Customer;
import com.shoppingcart.customer.CustomerType;

import java.util.Objects;

public class CustomerDiscount  {

    private int lowRangeBillAmount;
    private int highRangeBillAmount;
    private int discountPercentage;
    private CustomerType discountFor;

    public CustomerDiscount(int lowRangeBillAmount, int highRangeBillAmount, int discountPercentage, CustomerType discountFor) {
        this.lowRangeBillAmount = lowRangeBillAmount;
        this.highRangeBillAmount = highRangeBillAmount;
        this.discountPercentage = discountPercentage;
        this.discountFor = discountFor;
    }

    public int getLowRangeBillAmount() {
        return lowRangeBillAmount;
    }

    public int getHighRangeBillAmount() {
        return highRangeBillAmount;
    }

    public CustomerType getDiscountFor() {
        return discountFor;
    }


    public int calculateDiscount(Customer customer) {
        if (customer.getAmount() == 0 || !isDiscountValidFor(customer)) return 0;
        if (this.highRangeBillAmount - customer.getAmount() < 0) {
            return ((this.highRangeBillAmount - this.lowRangeBillAmount) * discountPercentage) / 100;
        }
        return ((customer.getAmount() - this.lowRangeBillAmount) * discountPercentage) / 100;

    }

    public boolean isDiscountValidFor(Customer customer) {
        if (customer.getType() != discountFor) {
            return false;
        } else return customer.getAmount() > lowRangeBillAmount;

    }

    public boolean isRangeWithin(CustomerDiscount existingDiscount){
       return  isRangeWithinExistingDiscount(existingDiscount, lowRangeBillAmount)
                || isRangeWithinExistingDiscount(existingDiscount, highRangeBillAmount);
    }

    private boolean isRangeWithinExistingDiscount(CustomerDiscount existingDiscount, int customRangeAmount) {
        return existingDiscount.getLowRangeBillAmount() < customRangeAmount
                && customRangeAmount < existingDiscount.getHighRangeBillAmount();
    }

    public boolean isRangeSuperSetOf(CustomerDiscount existingDiscount) {
        return lowRangeBillAmount < existingDiscount.getLowRangeBillAmount() &&
                existingDiscount.getHighRangeBillAmount() < highRangeBillAmount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDiscount discount = (CustomerDiscount) o;
        return lowRangeBillAmount == discount.lowRangeBillAmount &&
                highRangeBillAmount == discount.highRangeBillAmount &&
                discountPercentage == discount.discountPercentage &&
                discountFor == discount.discountFor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowRangeBillAmount, highRangeBillAmount, discountPercentage, discountFor);
    }

    @Override
    public String toString() {
        return "CustomerDiscount{" +
                "lowRangeBillAmount=" + lowRangeBillAmount +
                ", highRangeBillAmount=" + highRangeBillAmount +
                ", discountPercentage=" + discountPercentage +
                ", discountFor=" + discountFor +
                '}';
    }





}

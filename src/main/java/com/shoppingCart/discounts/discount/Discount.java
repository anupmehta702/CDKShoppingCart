package com.shoppingCart.discounts.discount;

import com.shoppingCart.customer.Customer;
import com.shoppingCart.customer.CustomerType;

import java.util.Objects;

public abstract class Discount {
    int lowRangeBillAmount = 0;
    int highRangeBillAmount = 0;
    int discountPercentage = 0;
    public CustomerType discountFor;

    public Discount(int lowRangeBillAmount, int highRangeBillAmount, int discountPercentage,CustomerType discountFor) {
        this.lowRangeBillAmount = lowRangeBillAmount;
        this.highRangeBillAmount = highRangeBillAmount;
        this.discountPercentage = discountPercentage;
        this.discountFor = discountFor;
    }

    public int getLowRangeBillAmount() {
        return lowRangeBillAmount;
    }

    public void setLowRangeBillAmount(int lowRangeBillAmount) {
        this.lowRangeBillAmount = lowRangeBillAmount;
    }

    public int getHighRangeBillAmount() {
        return highRangeBillAmount;
    }

    public void setHighRangeBillAmount(int highRangeBillAmount) {
        this.highRangeBillAmount = highRangeBillAmount;
    }

    abstract public int calculateDiscount(Customer customer);

    protected boolean isDiscountValidFor(Customer customer) {
        if(customer.getType() != discountFor){
            System.out.println("Customer type does not match discount type");
            return false;
        }else if(customer.getAmount() <= lowRangeBillAmount){
            System.out.println("customer bill amount is lesser than lower range of discount");
            return false;
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
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
        return "Discount{" +
                "lowRangeBillAmount=" + lowRangeBillAmount +
                ", highRangeBillAmount=" + highRangeBillAmount +
                ", discountPercentage=" + discountPercentage +
                ", discountFor=" + discountFor +
                '}';
    }
}

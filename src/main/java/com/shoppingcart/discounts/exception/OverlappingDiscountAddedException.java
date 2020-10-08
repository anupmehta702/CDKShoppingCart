package com.shoppingcart.discounts.exception;

public class OverlappingDiscountAddedException extends AddDiscountException {
    public OverlappingDiscountAddedException(String message) {
        super(message);
    }
}

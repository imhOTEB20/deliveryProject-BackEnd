package com.molesgroup.deliveryproject.service.errors;

public class UserTypeNotSupportedException extends RuntimeException {
    public UserTypeNotSupportedException(String message) {
        super(message);
    }
}
package com.ArchiDistribuee.VirtualCRM.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAnnualRevenuesException extends RuntimeException {
    public InvalidAnnualRevenuesException(double lowAnnualRevenue, double highAnnualRevenue) {
        super("High revenue (" + highAnnualRevenue + ") must be greater than low revenue(" + lowAnnualRevenue + ")");
    }
}

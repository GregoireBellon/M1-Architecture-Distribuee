package com.ArchiDistribuee.VirtualCRM.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Calendar;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDatesException extends RuntimeException {
    public InvalidDatesException(Calendar startDate, Calendar endDate) {
        super("Start date(" + startDate.getTime().toString() + ") must be before end date("
                + endDate.getTime().toString() + ")");
    }
}

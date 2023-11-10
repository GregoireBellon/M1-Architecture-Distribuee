package com.ArchiDistribuee.VirtualCRM.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZonedDateTime;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDatesException extends RuntimeException {
    public InvalidDatesException(ZonedDateTime startDate, ZonedDateTime endDate) {
        super("Start date(" + startDate.toLocalTime().toString() + ") must be before end date("
                + endDate.toLocalTime().toString() + ")");
    }
}

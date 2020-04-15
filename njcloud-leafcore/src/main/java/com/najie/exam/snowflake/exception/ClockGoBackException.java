package com.najie.exam.snowflake.exception;

public class ClockGoBackException extends RuntimeException {
    public ClockGoBackException(String message) {
        super(message);
    }
}

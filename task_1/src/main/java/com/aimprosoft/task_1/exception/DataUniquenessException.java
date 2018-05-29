package com.aimprosoft.task_1.exception;

public class DataUniquenessException extends Exception {

    public DataUniquenessException() {
    }

    public DataUniquenessException(String s) {
        super(s);
    }

    public DataUniquenessException(String message, Throwable cause) {
        super(message, cause);
    }
}

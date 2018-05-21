package com.aimprosoft.task_1.exception;

public class TransactionInterruptedException extends Exception {

    public TransactionInterruptedException() {
    }

    public TransactionInterruptedException(String message) {
        super(message);
    }

    public TransactionInterruptedException(String message, Throwable cause) {
        super(message, cause);
    }
}

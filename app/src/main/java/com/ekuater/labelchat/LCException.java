
package com.ekuater.labelchat;

public class LCException extends Exception {

    private static final long serialVersionUID = 3417558210229545485L;

    /**
     * Constructs a new {@code RuntimeException} that includes the current stack
     * trace.
     */
    public LCException() {
    }

    /**
     * Constructs a new {@code RuntimeException} with the current stack trace
     * and the specified detail message.
     * 
     * @param detailMessage the detail message for this exception.
     */
    public LCException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * Constructs a new {@code RuntimeException} with the current stack trace,
     * the specified detail message and the specified cause.
     * 
     * @param detailMessage the detail message for this exception.
     * @param throwable the cause of this exception.
     */
    public LCException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    /**
     * Constructs a new {@code RuntimeException} with the current stack trace
     * and the specified cause.
     * 
     * @param throwable the cause of this exception.
     */
    public LCException(Throwable throwable) {
        super(throwable);
    }
}

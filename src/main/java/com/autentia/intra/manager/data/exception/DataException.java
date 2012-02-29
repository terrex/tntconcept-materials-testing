package com.autentia.intra.manager.data.exception;

/**
 * @author ivan
 */
public class DataException extends java.lang.Exception {

    /**
     * Creates a new instance of <code>DataException</code> without detail message.
     */
    public DataException() {
    }

    /**
     * Constructs an instance of <code>DataException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public DataException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>DataException</code> with the specified detail message and cause.
     *
     * @param msg   the detail message.
     * @param cause the cause.
     */
    public DataException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

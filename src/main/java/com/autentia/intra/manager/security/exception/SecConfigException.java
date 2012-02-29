package com.autentia.intra.manager.security.exception;

/**
 * Error detected on configuration file process
 *
 * @author Carlos García. Autentia
 */
public class SecConfigException extends SecException {
    private static final long serialVersionUID = 4531708687775121268L;

    /**
     * Creates a new instance of <code>SecConfigException</code> without detail message.
     */
    public SecConfigException() {
    }

    /**
     * Constructs an instance of <code>SecConfigException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public SecConfigException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>SecConfigException</code> with the specified detail message and cause.
     *
     * @param msg   the detail message.
     * @param cause the cause.
     */
    public SecConfigException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

package com.autentia.intra.manager.report;

import org.xml.sax.SAXException;

/**
 * @author ivan
 */
public class FinalizeParsingException extends SAXException {

    /**
     * Creates a new instance of <code>FinalizeParsingException</code> without detail message.
     */
    public FinalizeParsingException() {
    }

    /**
     * Constructs an instance of <code>FinalizeParsingException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public FinalizeParsingException(String msg) {
        super(msg);
    }
}

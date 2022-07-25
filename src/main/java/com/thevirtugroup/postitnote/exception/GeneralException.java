package com.thevirtugroup.postitnote.exception;

public class GeneralException extends Exception {

    private static final long serialVersionUID = 1L;

    public GeneralException(String msg) {
        super(msg);
    }

    public GeneralException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}

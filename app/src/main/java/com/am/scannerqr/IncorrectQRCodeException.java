package com.am.scannerqr;

public class IncorrectQRCodeException extends Exception{

    public IncorrectQRCodeException(String errorMessage) {
        super(errorMessage);
    }
}

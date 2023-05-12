package com.example.visitorservice.exception;

public class DuplicateDataException extends RuntimeException{

    public DuplicateDataException(String msg){
        super(msg);
    }
}

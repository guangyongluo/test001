package com.vilin.http.exception;

public class ConnException extends RuntimeException{

    public ConnException(){
        super();
    }

    public ConnException(String message){
        super(message);
    }
}

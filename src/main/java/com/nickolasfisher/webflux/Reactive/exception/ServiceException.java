package com.nickolasfisher.webflux.Reactive.exception;

public class ServiceException extends RuntimeException{

    public ServiceException(String message){
      super(message);
    }
}

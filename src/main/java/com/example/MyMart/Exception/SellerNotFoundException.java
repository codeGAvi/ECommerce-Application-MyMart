package com.example.MyMart.Exception;

public class SellerNotFoundException extends RuntimeException{
    public SellerNotFoundException(String message){
        super(message);
    }
}

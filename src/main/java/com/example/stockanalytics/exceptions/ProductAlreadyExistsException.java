package com.example.stockanalytics.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException() {super("Product already exists");}
    public ProductAlreadyExistsException(String message) {super(message);}
}

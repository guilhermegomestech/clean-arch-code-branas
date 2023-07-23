package br.com.branas.glstore.exceptions;

public class OrderException extends RuntimeException{
    public OrderException() {}

    public OrderException(String message) {
        super(message);
    }
}

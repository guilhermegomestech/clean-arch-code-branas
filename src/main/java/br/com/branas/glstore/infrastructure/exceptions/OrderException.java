package br.com.branas.glstore.infrastructure.exceptions;

public class OrderException extends RuntimeException{
    public OrderException() {}

    public OrderException(String message) {
        super(message);
    }
}

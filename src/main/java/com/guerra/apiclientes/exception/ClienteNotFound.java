package com.guerra.apiclientes.exception;

public class ClienteNotFound extends RuntimeException {

    public ClienteNotFound(String msg) {
        super(msg);
    }
}

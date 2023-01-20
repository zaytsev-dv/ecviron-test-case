package com.ecviron.api.exception;

import lombok.Data;

@Data
public class UnvalidException extends Exception{
    private final String errors;

    public UnvalidException(String errors) {
        super(errors);
        this.errors = errors;
    }
}

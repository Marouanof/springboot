package com.example.demo.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomResponseException extends RuntimeException {
    private int code;
    private String message;
    public static CustomResponseException ResourceNotFound(String message) {
        return new CustomResponseException(404,message);
    }
}

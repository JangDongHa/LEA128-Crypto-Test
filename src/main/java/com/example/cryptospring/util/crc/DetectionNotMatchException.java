package com.example.cryptospring.util.crc;

public class DetectionNotMatchException extends RuntimeException{
    public DetectionNotMatchException(String s) {
        super(s);
    }

    public DetectionNotMatchException(){

    }
}

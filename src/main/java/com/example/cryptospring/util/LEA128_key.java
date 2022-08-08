package com.example.cryptospring.util;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class LEA128_key {
    private String key = "This is Default.";


    public byte[] getKey(){
        validation(key);
        return key.getBytes();
    }

    public void setKey(String key){
        this.key = key;
    }

    private void validation(final String key) {
        try{
            Optional.ofNullable(key)
                    .filter(Predicate.not(String::isBlank))
                    .filter(Predicate.not(s -> s.length() != 16))
                    .orElseThrow(IllegalArgumentException::new);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Key should Non-Blank and must be 16 length");
        }

    }
}

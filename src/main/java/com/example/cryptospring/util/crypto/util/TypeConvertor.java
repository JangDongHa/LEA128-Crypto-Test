package com.example.cryptospring.util.crypto.util;

public class TypeConvertor<T> {

    public T getData(Object data){
        return (T) data.getClass().cast(data);
    }



}

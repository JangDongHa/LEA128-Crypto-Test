package com.example.cryptospring.util.crypto;

import java.math.BigDecimal;

public class Test {
    public static Object changeClass(Object b){
        BigDecimal a = new BigDecimal((int)b);
        return a;
    }
}

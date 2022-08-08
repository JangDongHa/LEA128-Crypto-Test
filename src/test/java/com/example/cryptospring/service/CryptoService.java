package com.example.cryptospring.service;


import com.example.cryptospring.util.LEA128_CTR_decrypt;
import com.example.cryptospring.util.LEA128_CTR_encrypt;
import com.example.cryptospring.util.LEA128_key;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class CryptoService {

    @Autowired
    private LEA128_key key;

    @Autowired
    private LEA128_CTR_encrypt encrypt;

    @Autowired
    private LEA128_CTR_decrypt decrypt;

    @Test
    @Order(1)
    public void encryptObjectUsingLEA128() throws IOException {
        Object plain = "test";
        byte[] cipher = encrypt.object(plain, key);
    }

    @Test
    @Order(2)
    public void decryptObjectUsingLEA128() throws IOException, ClassNotFoundException {
        Object plain = "test";
        byte[] cipher = encrypt.object(plain, key); // 암호화 (return : byte[])

        Object decryptPlain = decrypt.object(cipher, key); // 복호화 (return : Object)

        // 2번째 테스트
        BigDecimal bigDecimal = new BigDecimal(3);
        byte[] decimalCipher = encrypt.object(bigDecimal, key);
        Object decryptBigDecimal = decrypt.object(decimalCipher, key); // 형변환
        // 1번째 바이트에 따른 형변환은 데이터 타입이 너무 많아서 하나씩 추가중 (util -> DataTypeDeclaration)

        assertEquals(plain, decryptPlain);
        assertEquals(bigDecimal, decryptBigDecimal);

    }

    @Test
    @Order(3)
    public void encryptFileUsingLEA128() {
        File file = new File("test.txt");
        String encryptPath = "Encoding_test.txt";


        try{
            encrypt.file(file, encryptPath, key);
        }catch (IOException e){
            throw new RuntimeException("Can not find file path");
        }

    }

    @Test
    @Order(4)
    public void decryptFileUsingLEA128(){ // 결과는 build.gradle 쪽에 나옴
        File encryptFile = new File("Encoding_test.txt");
        String decryptPath = "Decoding_test.txt";
        try {
            decrypt.file(encryptFile, decryptPath, key);
        } catch (IOException e) {
            throw new RuntimeException("Can not find file path");
        }
    }
}

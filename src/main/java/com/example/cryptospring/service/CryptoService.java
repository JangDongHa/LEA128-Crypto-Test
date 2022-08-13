package com.example.cryptospring.service;

import com.example.cryptospring.util.LEA128_CTR_decrypt;
import com.example.cryptospring.util.LEA128_CTR_encrypt;
import com.example.cryptospring.util.LEA128_key;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;

public class CryptoService {

    @Autowired
    private LEA128_key key;

    @Autowired
    private LEA128_CTR_encrypt encrypt;

    @Autowired
    private LEA128_CTR_decrypt decrypt;

    public byte[] encryptObjectUsingLEA128(Object plain) throws IOException {
        byte[] cipher = encrypt.object(plain, key);

        return cipher;
    }

    public Object decryptObjectUsingLEA128(byte[] cipher) throws IOException, ClassNotFoundException {
        Object plain = decrypt.object(cipher, key);

        return plain;
    }

    public void encryptFileUsingLEA128(File file, String encryptPath) {
        try{
            encrypt.file(file, encryptPath, key);
        }catch (IOException e){
            throw new RuntimeException("Can not find file path");
        }

    }

    public void decryptFileUsingLEA128(File encryptFile, String decryptPath){
//        try {
//            decrypt.file(encryptFile, decryptPath, key);
//        } catch (IOException e) {
//            throw new RuntimeException("Can not find file path");
//        }
    }

    public void setKey(String key){
        setKey(key);
    }
}

package com.example.cryptospring.controller;

import com.example.cryptospring.dto.ResponseDto;
import com.example.cryptospring.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;

public class CryptoController {
    @Autowired
    private CryptoService cryptoService;

    @GetMapping("/test/encrypt/{plain}")
    public ResponseDto<byte[]> encryptPlain(@PathVariable String plain) throws IOException { // 실제로 get, PathVariable 로 안 받을 것 (테스트용)
        return new ResponseDto<>(HttpStatus.OK, cryptoService.encryptObjectUsingLEA128(plain));
    }

    @GetMapping("/test/decrypt/{cipher}")
    public ResponseDto<Object> decryptCipher(@PathVariable byte[] cipher) throws IOException, ClassNotFoundException {
        return new ResponseDto<>(HttpStatus.OK, cryptoService.decryptObjectUsingLEA128(cipher));
    }

    @GetMapping("/test/encrypt/file")
    public ResponseDto<String> encryptFile(){
        File file = new File("test.txt");
        String encryptPath = "Encoding_test.txt";
        cryptoService.encryptFileUsingLEA128(file, encryptPath);
        return new ResponseDto<>(HttpStatus.OK, "done");
    }

    @GetMapping("/test/decrypt/file")
    public ResponseDto<String> decryptFile(){
        File file = new File("Encoding_test.txt");
        String decryptPath = "Decoding_test.txt";
        cryptoService.decryptFileUsingLEA128(file, decryptPath);
        return new ResponseDto<>(HttpStatus.OK, "done");
    }

    @GetMapping("/test/key/{key}")
    public ResponseDto<String> changeKey(@PathVariable String key){
        cryptoService.setKey(key);
        return new ResponseDto<>(HttpStatus.OK, "done");
    }
}

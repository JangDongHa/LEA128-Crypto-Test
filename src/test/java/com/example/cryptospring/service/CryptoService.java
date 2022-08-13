package com.example.cryptospring.service;


import com.example.cryptospring.util.LEA128_CTR_decrypt;
import com.example.cryptospring.util.LEA128_CTR_encrypt;
import com.example.cryptospring.util.LEA128_key;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
        File file = new File("test.txt");
        String encryptPath = "Encoding_test.txt";


        try{
            encrypt.file(file, encryptPath, key);
        }catch (IOException e){
            throw new RuntimeException("Can not find file path");
        }
        int crcValue = encrypt.getCrcValue();

        /*
        * encData: "DFWE(FJ&*JF($#&F",
        * crcValue: 2398234 */


        File encryptFile = new File("Encoding_test.txt");
        String decryptPath = "Decoding_test.txt";
        try {
            decrypt.file(encryptFile, decryptPath, key, crcValue);
        } catch (IOException e) {
            throw new RuntimeException("Can not find file path");
        }
    }

    @Test
    public void test(){
        File file = new File("test.txt");
        File tempFile = new File("test2.txt");

        byte[] readData = new byte[8];
        byte[] beforeData = new byte[8];


        try(FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(tempFile)) {

            boolean isFirstRound = true;
            byte type = (byte)fileInputStream.read(); // 형 구분자 제거
            fileOutputStream.write(type);

            while( fileInputStream.read(readData) != -1){
                // 첫 라운드에는 beforeData에 아무런 값이 없으므로 write X
                // 이후 라운드부터는 이전 라운드 값인 beforeData를 넣어줌
                // 이렇게하면 마지막 라운드에 8바이트 전 데이터까지 들어가고 while 문 종료

                System.out.println("readData : " + new String(readData));
                System.out.println("beforeData : " + new String(beforeData));

                if (!isFirstRound){
                    fileOutputStream.write(beforeData);
                }
                else
                    isFirstRound = false;


                System.arraycopy(readData, 0, beforeData, 0, beforeData.length);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Path decPath = Paths.get(file.getPath());
        Path srcPath = Paths.get(tempFile.getPath());
        try {
            Files.move(srcPath, decPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

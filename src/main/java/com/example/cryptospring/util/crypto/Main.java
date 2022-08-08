package com.example.cryptospring.util.crypto;


import com.example.cryptospring.util.LEA128_CTR_decrypt;
import com.example.cryptospring.util.LEA128_CTR_encrypt;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        /* TODO
            파일 16바이트로 자르기
            복호화된 16바이트 블록 write로 입력하기
        * */

        File file = new File("./test.txt");

        //System.out.println(file.getName());


        LEA128_CTR_encrypt lea128_ctr_encrypt = new LEA128_CTR_encrypt();
        LEA128_CTR_decrypt lea128_ctr_decrypt = new LEA128_CTR_decrypt();



        String filename = "./test.txt";
        String encodingDesFileName = "./Encoding_" + filename;
        String decodingDesFileName = "./decoding_" + filename;
        File file_enc = new File(filename);

        //lea128_ctr_encrypt.file(file_enc, encodingDesFileName);
        System.out.println("enc");


        File file_dec = new File(encodingDesFileName);
        //lea128_ctr_decrypt.file(file_dec, decodingDesFileName);
        System.out.println("dec");

        BigDecimal bigDecimal = new BigDecimal(3);
        //byte[] a = lea128_ctr_encrypt.object(bigDecimal);
        //System.out.println(new String(a));
        //Object b = lea128_ctr_decrypt.object(a);
        //String b = String.valueOf(lea128.decryptObject(a));

//
//        BigDecimal bigDecimal1 = (BigDecimal) b.getClass().cast(b);
//        System.out.println("decrypt : " + b.getClass().cast(b));
//        System.out.println(b.getClass());


    }
}

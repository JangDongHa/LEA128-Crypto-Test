package com.example.cryptospring.util.crypto;

import com.example.cryptospring.util.LEA128_CTR_decrypt;
import com.example.cryptospring.util.LEA128_CTR_encrypt;
import com.example.cryptospring.util.crypto.util.DataTypeTranslation;
import com.example.cryptospring.util.crypto.util.TypeConvertor;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;

public class Main2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Object a = "test";


        Class t = Double.class;
        TypeConvertor<String> test = new TypeConvertor<>();

        double bigDecimal =  2.2;


        String key = "jdjdjdjdjdjdjdjd";
        LEA128_CTR_encrypt lea128_ctr_encrypt = new LEA128_CTR_encrypt();
        LEA128_CTR_decrypt lea128_ctr_decrypt = new LEA128_CTR_decrypt();

//        byte[] encBytes = lea128_ctr_encrypt.object(bigDecimal);
//
//        Blob blob = new ByteToBlob(encBytes);
//
//        Object dec = lea128_ctr_decrypt.object(blob.getBytes(0, 0));
//        System.out.println(dec.getClass());
//
//
//        DataTypeTranslation dataTypeTranslation = new DataTypeTranslation();
//
//        float aa = (float)3.3;
//        double bb= 3.3;







        TypeConvertor<BigDecimal> typeConvertor = new TypeConvertor<>();

        //System.out.println(dec.getClass());


        //int b = test.getTest();
        //System.out.println(b);

    }
}

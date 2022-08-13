package com.example.cryptospring.util.crc;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;

@Slf4j

public class CRC {
    private final CRC32 crc32;


    public CRC() {crc32 = new CRC32();}

    public void detector(){

    }


    public void update(byte[] arr, int offset, int len){
        crc32.update(arr,offset,len);
    }


    public void update(byte[] arr){
        crc32.update(arr);
    }

    public byte[] addCRCValue(){
        byte[] crcValueArray = this.getValue();

        System.out.println(toValue(crcValueArray));
        System.out.println(this.getValue());
        return crcValueArray;
    }

    private byte[] getValue(){
        int value = (int)crc32.getValue();
        System.out.println("getValue() => value = " + value);
        return ByteBuffer.allocate(Integer.BYTES).putInt(value).array();
    }

    public int toValue(byte[] data){
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.put(data);
        buffer.flip();
        return buffer.getInt();
    }

    public void detection(File file, int detectionValue) {
        int value = removeCRCValueWithFile(file);
        System.out.println("value = " + value);
        byte[] fileData = new byte[Integer.BYTES];

        try(FileInputStream fileInputStream = new FileInputStream(file);) {
            fileInputStream.read();
            while(fileInputStream.read(fileData) != -1){
                crc32.update(fileData, 0, Integer.BYTES);
            }

            System.out.println("detectionValue = " + detectionValue);
            if(value != detectionValue){
                throw new DetectionNotMatchException("파일명이 맞지 않습니다.");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private int removeCRCValueWithFile(File file){
        byte[] readData = new byte[4];
        byte[] beforeData = new byte[4];
//        File tempFile = new File("abc.txt");
        int value;
        int length = 0;
        System.out.println("file length : " + file.length());
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            byte type = (byte)fileInputStream.read(); // 형 구분자 제거


            boolean isFirstRound = true;

            while( fileInputStream.read(readData) != -1){
                // 첫 라운드에는 beforeData에 아무런 값이 없으므로 write X
                // 이후 라운드부터는 이전 라운드 값인 beforeData를 넣어줌
                // 이렇게하면 마지막 라운드에 8바이트 전 데이터까지 들어가고 while 문 종료
//                if (!isFirstRound){
//
//                }
//                else
//                    isFirstRound = false;
//
//
                System.arraycopy(readData, 0, beforeData, 0, beforeData.length);
//                fileOutputStream.write(beforeData);
                length += 4;
            }

            value = toValue(beforeData);
            System.out.println(value);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        System.out.println("temp length : " + tempFile.length());
//        Path desPath = Paths.get(file.getPath());
//        Path srcPath = Paths.get(tempFile.getPath());
//        System.out.println("len : " + length);
//
//        try {
//            Files.move(srcPath, desPath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return value;
    }



}

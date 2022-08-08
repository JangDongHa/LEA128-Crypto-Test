package com.example.cryptospring.util.crypto.util;


import static com.example.cryptospring.util.crypto.util.DataTypeDeclaration.setTypeInJava;
import static com.example.cryptospring.util.crypto.util.DataTypeDescription.JAVATYPE;

public class DataTypeTranslation {

    public DataTypeTranslation(){
        setTypeInJava();
        DataTypeDeclaration.setClassWithByte(DataTypeDescription.CLASSTYPE);
    }

    public Object alterClassType(Object val, Byte key){
        return DataTypeDescription.CLASSTYPE.get(key).alterClassType(val);
    }

    public static byte getTypeInJava(Object type){
        setTypeInJava();
        return JAVATYPE.get(type.getClass());
    }

}

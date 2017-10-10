package com.banggood.scm.utils;

import java.io.*;

/**
 * Created by Administrator on 2017/8/29.
 */
public class SerializationByByte {


    //对象序列化成byte
    public static byte[] serialize(Object object) {
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] bytes=null;
        try {
            // 序列化
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            bytes= byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
        }
        finally {
            try {
                objectOutputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return bytes;
    }

    //byte反序列化成对象
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream =null;
        Object object=null;
        try {
            // 反序列化
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream=new ObjectInputStream(byteArrayInputStream);
            object= objectInputStream.readObject();
        } catch (Exception e) {
        }
        finally {
            try {
                objectInputStream.close();
                byteArrayInputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return object;
    }
}

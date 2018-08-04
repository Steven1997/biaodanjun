package cn.habitdiary.form.utils;

import cn.habitdiary.form.entity.FormDefinition;

import java.io.*;

public class ObjectIOUtils {
    //先写入对象
    public static void createObj(String filepath, FormDefinition formDefinition) throws IOException {
        //1.创建目标路径
        File file = new File(filepath);
        //2.创建流通道
        //3.创建对象输出流
        //4.向目标路径文件写入对象
        try(FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream objOP = new ObjectOutputStream(fos);){
            objOP.writeObject(formDefinition);
        }

    }

    //读取对象
    public static FormDefinition readObj(String filepath) throws IOException, ClassNotFoundException {
        File file = new File(filepath);
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream objIP = new ObjectInputStream(fis);){
            //读取对象数据，需要将对象流强制转换为 要写入对象的类型
            FormDefinition formDefinition = (FormDefinition) objIP.readObject();
            return formDefinition;
        }

    }
}

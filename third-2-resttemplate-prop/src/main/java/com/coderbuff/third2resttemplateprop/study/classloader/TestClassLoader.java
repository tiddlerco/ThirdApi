package com.coderbuff.third2resttemplateprop.study.classloader;

import java.io.*;

/**
 * @Author 喻可
 * @Date 2021/11/25 15:18
 */
public class TestClassLoader extends ClassLoader {
    public TestClassLoader(ClassLoader parent) {
        super(parent);
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 1、获取class文件二进制字节数组
        byte[] data = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(new File("C:\\study\\myStudy\\JavaLearning\\target\\classes\\com\\stefan\\DailyTest\\classLoader\\Demo.class"));
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fis.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            data = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 2、字节码数组加载到 JVM 的方法区，
        // 并在 JVM 的堆区建立一个java.lang.Class对象的实例
        // 用来封装 Java 类相关的数据和方法
        return this.defineClass(name, data, 0, data.length);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException{
        // 1、找到ext classLoader，并首先委派给它加载，为什么？
        ClassLoader classLoader = getSystemClassLoader();
        while (classLoader.getParent() != null) {
            classLoader = classLoader.getParent();
        }
        Class<?> clazz = null;
        try {
            clazz = classLoader.loadClass(name);
        } catch (ClassNotFoundException e) {
            // Ignore
        }
        if (clazz != null) {
            return clazz;
        }
        // 2、自己加载
        clazz = this.findClass(name);
        if (clazz != null) {
            return clazz;
        }
        // 3、自己加载不了，再调用父类loadClass，保持双亲委派模式
        return super.loadClass(name);
    }
}

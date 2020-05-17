package com.yhh.practice.jvm.classLoader;


/****
 * creator by yhh
 * 指定类加载器的父加载器
 *
 */
public class SuperClassLoader_c3 {

    private static ClassLoader parent1 = new MyClassLoader_c3.MyLoader1();

    private static class MyClasser2 extends ClassLoader {


        public MyClasser2(ClassLoader parent) {
            super(parent);
            //super(parent1);
            System.out.println(getParent());
        }
    }

    public static void main(String[] args) {
        ClassLoader c1 = new MyClassLoader_c3.MyLoader1();
        MyClasser2 myClasser2 = new MyClasser2(c1);

    }
}





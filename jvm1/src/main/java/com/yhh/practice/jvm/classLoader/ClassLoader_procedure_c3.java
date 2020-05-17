package com.yhh.practice.jvm.classLoader;

/****
 * creator by yhh
 * 验证初始化时静态变量赋值
 */
public class ClassLoader_procedure_c3 {

    public static void main(String[] args) {


        System.out.println(T.con);
    }
}


class T {
    public static   T t = new T();

    public static int con = 2;

    private T() {
        con++;
    }
}
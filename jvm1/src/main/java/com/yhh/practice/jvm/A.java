package com.yhh.practice.jvm;

public class A {


    public static void main(String[] args) {

        System.out.println(String.class.getClassLoader());
        System.out.println(A.class.getClassLoader().getClass().getClassLoader());
        System.out.println(A.class.getClassLoader().getParent());
        System.out.println(A.class.getClassLoader().getParent().getParent());
        System.out.println(A.class.getClassLoader().getParent().getClass().getClassLoader());

    }
}

package com.yhh.practice.jvm;

import java.io.Serializable;

public class B implements Cloneable,Serializable {
    public int i =123;

    String st ="sb";

    public void test(){
        System.out.println("success");
    }
}

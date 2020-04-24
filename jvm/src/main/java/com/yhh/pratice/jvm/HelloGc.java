package com.yhh.pratice.jvm;

import java.util.LinkedList;
import java.util.List;

public class HelloGc {


    /***
     * 不断添加大对象  导致OOM
     *  java.lang.OutOfMemoryError: Java heap space
     * @param args
     */
    public static void main(String[] args) {
        List list = new LinkedList();
        for(;;){
            byte[] bytes = new byte[1024*1024];
            list.add(bytes);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}

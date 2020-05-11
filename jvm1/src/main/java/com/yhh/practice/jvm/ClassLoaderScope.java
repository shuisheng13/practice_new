package com.yhh.practice.jvm;

/***
 * 验证类加载器加载的路径
 */
public class ClassLoaderScope {


    public static void main(String[] args) {

        String pathBoot = System.getProperty("sun.boot.class.path");
        System.out.println(pathBoot.replaceAll(":",System.lineSeparator()));
        System.out.println("--------------------------------");
        String pathExt = System.getProperty("java.ext.dirs");
        System.out.println(pathExt.replaceAll(":",System.lineSeparator()));
        System.out.println("--------------------------------");

        String pathClass = System.getProperty("java.class.path");
        System.out.println(pathClass.replaceAll(":",System.lineSeparator()));


    }


}

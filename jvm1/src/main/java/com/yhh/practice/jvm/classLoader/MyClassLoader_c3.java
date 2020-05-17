package com.yhh.practice.jvm.classLoader;

import java.io.*;

public class MyClassLoader_c3 {


    private static String path = "/Users/yanghaohua/IdeaProjects/practice_new/jvm1/target/classes/com/yhh/practice/jvm";


    /****
     * 重写类加载器的  findclass方法
     *
     */
    public static class MyLoader1 extends ClassLoader{

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {


            File f = new File(path, name.replace(".", "/").concat(".class"));
            try {
                FileInputStream fis = new FileInputStream(f);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int b = 0;

                while ((b=fis.read()) !=0) {
                    baos.write(b);
                }

                byte[] bytes = baos.toByteArray();
                baos.close();
                fis.close();//可以写的更加严谨

                return defineClass(name, bytes, 0, bytes.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return super.findClass(name); //throws ClassNotFoundException

        }
    }

    /***
     * 重写classLoader  loadClass 方法
     *
      */
    public static class BreakClassloader extends ClassLoader{

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {

            File f = new File(path + name.replace(".", "/").concat(".class"));
            if(!f.exists()) return super.loadClass(name);
            try {

                InputStream is = new FileInputStream(f);

                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return super.loadClass(name);

        }
    }

    /***
     * MyLoader1 测试方法
     * @param args
     * @throws ClassNotFoundException
     */
//    public static void main(String[] args) throws ClassNotFoundException {
//
//        ClassLoader myLoader1 = new MyLoader1();
//        Class a = myLoader1.loadClass("com.yhh.practice.jvm.A");
//        System.out.println(a.hashCode());
//        ClassLoader myLoader2 = new MyLoader1();
//        Class b = myLoader1.loadClass("com.yhh.practice.jvm.A");
//        System.out.println(b.hashCode());
//        System.out.println(a==b);
//    }

    /***
     * BreakClassloader测试方法
     * @param args
     * @throws ClassNotFoundException
     */

    public static void main(String[] args) throws ClassNotFoundException {

//        ClassLoader m1 = new BreakClassloader();
//        Class a = m1.loadClass("com.yhh.practice.jvm.A");
//        ClassLoader m2 = new BreakClassloader();
//        Class b  = m2.loadClass("com.yhh.practice.jvm.A");
//        System.out.println(a.hashCode());
//        System.out.println(b.hashCode());

        /******
         *
         *
         */

        ClassLoader  mm1 = new BreakClassloader();
        Class c = mm1.loadClass("com.yhh.practice.jvm.B");
        ClassLoader mm2 = new BreakClassloader();
        Class d  = mm2.loadClass("com.yhh.practice.jvm.B");
        System.out.println(c.hashCode());
        System.out.println(d.hashCode());



    }



}

package com.yhh.practice.jvm.classLoader;


import com.sun.xml.internal.ws.assembler.jaxws.HandlerTubeFactory;

/****
 * dcl 单例模式的问题
 */
public class HalfInitClass {

    private HalfInitClass c1 = null;

    private HalfInitClass(){
        getInstall();

    }

    public HalfInitClass getInstall(){
        if(null==c1){
            synchronized (HalfInitClass.class){
                if(null==c1){
                    c1 = new HalfInitClass();
                }
            }
        }
        return c1;
    }

    public static void main(String[] args) {

        HalfInitClass c = new HalfInitClass();


        System.out.println(c.hashCode());
    }

}

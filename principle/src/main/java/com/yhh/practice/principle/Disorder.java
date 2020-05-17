//package com.yhh.practice.principle;
//
///***
// * creator by yhh
// * 验证CPU 乱序执行场景
// */
//public class Disorder {
//
//
//    private static int a = 0,b=0;
//    private static int x =0,y=0;
//
//    public static void main(String[] args) throws Exception{
//
//        int i =0;
//        for(;;){
//            i++;
//            a=0;b=0;x=0;y=0;
//
//            Thread t1 = new Thread(()->{
//                a=1;
//                x=b;
//            });
//
//
//            Thread t2 = new Thread(()->{
//               b=1;
//               y=a;
//            });
//
//            t1.start();t2.start();
//            t1.join();t2.join();
//
//            String result = "第" + i + "次 (" + x + "," + y + "）";
//            if(x==0&&y==0){
//                System.err.println(result);
//                break;
//            }
//
//
//
//
//        }
//
//
//    }
//
//}

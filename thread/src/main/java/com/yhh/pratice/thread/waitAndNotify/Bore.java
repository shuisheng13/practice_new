package com.yhh.pratice.thread.waitAndNotify;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/***
 * 枪膛
 *
 * 子弹入膛
 *
 * 射出子弹
 *
 */
public class Bore {

    private int MAX_SIZE = 20;

    public List<Cartridge> cartridgeList = new ArrayList<Cartridge>();

    private volatile int num = 0;

    /***
     * 子弹入膛动作
     * @param cartridge
     */
    public void push(Cartridge cartridge){

        synchronized (cartridgeList) {
            while (true) {
                if (cartridgeList.size() >= MAX_SIZE) {
                    try {
                        cartridgeList.wait();
                        System.out.println("当前线程  <>  " + Thread.currentThread().getName() + "  开始生产子弹满了   ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                cartridgeList.add(cartridge);
                num++;
                System.out.println("当前线程  <>  " + Thread.currentThread().getName() + "  开始生产子弹 " + num);
            }

        }
    }

    /***
     * 子弹扫射动作
     */

    public void pull() {

        synchronized (cartridgeList) {
            while (true) {
                if (cartridgeList.size() == 0) {
                    cartridgeList.notifyAll();
                }
                for (int i = 0; i < cartridgeList.size(); i++) {
                    cartridgeList.remove(i);
                    System.out.println("当前线程  <>  " + Thread.currentThread().getName() + "  射出子弹   " + i);
                }
                try {
                    cartridgeList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

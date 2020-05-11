package com.yhh.pratice.thread.waitAndNotify;


/***
 * 生产者
 * 生产子弹
 *
 *
 */

public class Comsumer  extends Thread{


    private Bore bore;

    public void setBore(Bore bore) {
        this.bore = bore;
    }

    @Override
    public void run() {
        synchronized (bore){
            while (true){
                Cartridge cartridge = new Cartridge();
                bore.push(cartridge);
            }
        }
    }

}

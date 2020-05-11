package com.yhh.pratice.thread.waitAndNotify;


/****
 * creator by yhh
 *
 * 消费者
 *
 *
 *
 */
public class Provider extends Thread{



    private Bore bore;

    public void setBore(Bore bore) {
        this.bore = bore;
    }

    @Override
    public void run() {

        bore.pull();
        }

}

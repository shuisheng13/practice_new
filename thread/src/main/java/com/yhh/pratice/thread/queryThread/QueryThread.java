package com.yhh.pratice.thread.queryThread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/***
 *
 * creator by yhh
 *
 * 查看所有线程信息  包括线程id ,name,状态
 *
 * 可以考虑用于业务线程死锁监控
 */

public class QueryThread {


    private static class WhileThread extends Thread{
        @Override
        public void run() {

        while (true){

        }
        }
    }

    public static void main(String[] args) {

        WhileThread whileThread = new WhileThread();
        whileThread.setName("not stop thread");
        whileThread.start();

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的monitor和synchronizer信息，仅仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);


        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] "
                    +" threadName  ----> "+ threadInfo.getThreadName()
                    +" threadState ------> "+threadInfo.getThreadState());
        }

    }

}

package daemon;

/***
 * creator by yhh
 *
 * 随主线程终止而终止
 *
 * 此处有个bug
 */
public class DaemonThread {

    private static class MyThread implements Runnable{
        public void run() {

            int n = 0;


            try {
                while (!Thread.currentThread().isInterrupted()){
                    while (n<=500000){
                        n++;
                        System.out.println("当前执行次数 ---------->  "+n);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.out.println("当前线程 isInterrupted ------>  "+Thread.currentThread().isInterrupted());
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("当前线程已执行完 finally 块");
            }


        }
    }

    public static void main(String[] args) throws InterruptedException {

        MyThread my = new MyThread();
        Thread thread = new Thread(my);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();

    }
}

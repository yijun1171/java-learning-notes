package org.yj.thread;

/**
 * Created by yijun.yj on 2015/7/28.
 */
public class InterruptTest {

    public static void main(String[] args) {

        Thread t = new Thread(new Runnable() {
            public void run() {
                Thread current = Thread.currentThread();
                while (true) {
                    if (current.isInterrupted()) {
                        Thread.interrupted();
                        System.out.println("exit");
                        return;
                    }
                    System.out.println("running");
                }
            }
        });
        t.start();
        try {
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();

    }
}

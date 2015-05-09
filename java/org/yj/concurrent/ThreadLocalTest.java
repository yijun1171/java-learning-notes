package org.yj.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yj on 15-4-30.
 */
public class ThreadLocalTest {

    private static AtomicInteger count = new AtomicInteger(0);

    private static int threadCount = 10;

    private static CountDownLatch startPoint = new CountDownLatch(threadCount);

    static class Task implements Runnable{

        private ThreadLocal<Integer> myLocal = new ThreadLocal<Integer>();

        @Override
        public void run() {
            try {
                myLocal.set(count.incrementAndGet());
                startPoint.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + ":" + "id-" + myLocal.get());
        }
    }

    public static void main(String[] args){
        for (int i = 0; i < threadCount; i++){
            new Thread(new Task()).start();
            startPoint.countDown();
        }

    }
}

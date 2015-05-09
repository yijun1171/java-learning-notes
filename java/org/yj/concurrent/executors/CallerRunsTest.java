package org.yj.concurrent.executors;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yj on 15-5-1.
 */
public class CallerRunsTest {

    static class Task implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++);//execute
            System.out.println(Thread.currentThread());
        }
    }
    public static void main(String[] args){
        int CAPACITY = 8;
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(5,5,0L, TimeUnit.SECONDS,
                        new LinkedBlockingDeque<Runnable>(CAPACITY));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        Thread.currentThread().setName("main");
        for(int i = 0; i < 100; i++){
            executor.execute(new Thread(new Task(),"Task-" + i));
            System.out.println("submit task "+i);
        }
    }
}

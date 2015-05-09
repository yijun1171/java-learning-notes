package org.yj.concurrent.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yj on 15-4-30.
 */
public class ExecutorTest {

    public static void main(String[] args){
//        Executor executor = Executors.newFixedThreadPool(5);//固定大小线程池
//        for(int i = 0 ;i < 10; i++){
//            executor.execute(new Task(i));
//        }
//        Executor executor = Executors.newCachedThreadPool();//可缓存线程池 无线程容量限制
//        for(int i = 0 ;i < 100; i++){
//            executor.execute(new Task(i));
//        }

//        Executor singleExecutor = Executors.newSingleThreadExecutor();//单线程线程池
//        for(int i = 0 ;i < 10; i++){
//            singleExecutor.execute(new Task(i));
//        }

        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(5);//

        for(int i = 0 ;i < 10; i++){
            scheduledExecutor.schedule(new Task(i),1L, TimeUnit.SECONDS);
        }
        scheduledExecutor.shutdown();

    }

    static class Task implements Runnable{

        int id;
        Task(int i){
            id = i;
        }
        @Override
        public void run() {
            System.out.println("task-"+id+" is running. thread:"+Thread.currentThread());
            for(int i = 0; i < Integer.MAX_VALUE; i++);
            System.out.println("task-"+id+" compeleted thread:"+Thread.currentThread());
        }
    }

}

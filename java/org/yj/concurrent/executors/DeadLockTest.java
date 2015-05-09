package org.yj.concurrent.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by yj on 15-5-1.
 */
public class DeadLockTest {

    static ExecutorService exec = Executors.newSingleThreadExecutor();

    static class Task implements Callable<String>{

        @Override
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit(new LoadTask("header"));
            footer = exec.submit(new LoadTask("footer"));
            System.out.println("wait header and footer");
            return header.get() + footer.get();
        }
    }

    static class LoadTask implements Callable<String>{

        String name;
        LoadTask(String name){
            this.name = name;
        }
        @Override
        public String call() throws Exception {
            System.out.println("load " + name);
            return null;
        }
    }

    public static void main(String[] args){

        exec.submit(new Task());
    }
}

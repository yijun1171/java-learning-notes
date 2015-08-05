package org.yj.thread;

/**
 * Created by yijun.yj on 2015/7/28.
 * 线程同步
 */
public class ThreadTest {

    public static void main(String[] args){

        Lock lock = new Lock();
        Thread t = new Thread(new SubThread(lock));
        t.start();

        try {
            Thread.sleep(1000 * 2);

            //结束子线程
            lock.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class SubThread implements Runnable{

        //同步记号
        private Lock lock;

        public SubThread(Lock lock) {
            this.lock = lock;
        }

        public void run() {
            while (true){
                if(!lock.isContinue()){
                    System.out.println("exit!");
                    return;
                }
                System.out.println("running");
            }
        }
    }

    public static class Lock{

        private volatile boolean flag = true;

        public void stop(){
            flag = false;
        }

        public boolean isContinue(){
            return flag;
        }
    }

}

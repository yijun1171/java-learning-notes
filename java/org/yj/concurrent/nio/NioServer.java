package org.yj.concurrent.nio;

import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yj on 15-5-2.
 */
public class NioServer implements Server{

    final int PORT;

    //工作线程池 用于处理IO
    ExecutorService pool;

    Multiplexer listening;

    Map<Channel, Worker> map = new ConcurrentHashMap<Channel, Worker>();

    public NioServer(int port, ExecutorService pool){
        PORT = port;
        listening = new Multiplexer(this, PORT);
        this.pool = pool;
    }

    @Override
    public void start() {
        listening.start();//用一个线程监听端口
    }

    @Override
    public void stop() {
        listening.interrupt();
        pool.shutdown();
    }

    //将connected的channel交给工作线程处理IO
    @Override
    public void handle(SelectionKey selectionKey) {
        if(selectionKey.isReadable()){
            Worker worker = new Worker(selectionKey, new EchoHandler());
            worker.setMode(Worker.READ);
            pool.submit(worker);
            map.put(selectionKey.channel(), worker);
        }else if(selectionKey.isWritable()){
            //Worker worker = map.get(selectionKey.channel());
           // worker.setMode(Worker.WRITE);
            //pool.submit(worker);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Server server = new NioServer(9999, Executors.newFixedThreadPool(2));
        server.start();

    }
}

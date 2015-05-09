package org.yj.concurrent.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yj on 15-5-2.
 */
public class Client implements Runnable{

    SocketChannel socketChannel;

    int id;

    CountDownLatch count;

    Selector selector;

    Client(String address, int port ,int id, CountDownLatch count){
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(address, port));
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.id = id;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!socketChannel.isConnected());
        try {

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            StringBuilder message = new StringBuilder("this is client-" + id);
            socketChannel.write(ByteBuffer.wrap(message.toString().getBytes()));
            buffer.clear();
            socketChannel.read(buffer);
            System.out.println(new String(buffer.array(),"utf-8"));
            socketChannel.finishConnect();
            socketChannel.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        CountDownLatch countDownLatch = new CountDownLatch(1);
        //for (int i = 0; i < 10; i ++){
            new Thread(new Client("127.0.0.1",9999,1,countDownLatch)).start();
            countDownLatch.countDown();
        //}
    }
}

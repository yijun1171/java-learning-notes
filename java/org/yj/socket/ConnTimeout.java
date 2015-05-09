package org.yj.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yj on 15-5-9.
 * 模拟连接队列满导致连接建立超时的情况
 * 会抛出SocketTimeoutException
 */
public class ConnTimeout {

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //参数1将队列大小限制在一个较小的范围
                    ServerSocket server = new ServerSocket(9999,1);
                    System.out.println("start");
                    while(true){
                        Socket socket = server.accept();
                        Thread.sleep(100000);//抑制server的处理速度,使得TCP连接在队列中堆积
                        System.out.print("accept");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(100);
        int i;
        for (i = 0 ; i<200; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Socket socket = new Socket();
                        socket.connect(new InetSocketAddress("127.0.0.1",9999),10);
                        System.out.println("connect to server,socket ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }
}

package org.yj.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by yj on 15-5-9.
 * 模拟读超时过程
 */
public class ReadTimeout {
    public static void main(String[] args){

        //通过共享变量控制读写
        final AtomicBoolean flag = new AtomicBoolean(false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket server = new ServerSocket(9999);
                    System.out.println("start");
                    Socket socket = server.accept();
                    System.out.print("accept");
                    while (!flag.get());
                    socket.getOutputStream().write("socket is still valid".getBytes());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        InputStream in = null;
        byte[] data = new byte[1024];
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            socket.setKeepAlive(true);
            socket.setSoTimeout(100);//设置读超时
            in = socket.getInputStream();
            while (true){
                in.read(data);//等待超时
            }
        } catch (IOException e) {
            if(e instanceof SocketTimeoutException) {
                System.out.println("超时");
                flag.compareAndSet(false,true);//通知server
                //此时socket仍然可以正常读写
                assert in != null;
                try {
                    in.read(data);
                    System.out.println(new String(data));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

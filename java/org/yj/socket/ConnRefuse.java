package org.yj.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by yj on 15-5-9.
 * 模拟连接不可达
 * 会抛出IOException,表明拒绝连接
 */
public class ConnRefuse {

    public static void main(String[] args) throws InterruptedException {

        try {
            Socket socket = new Socket();
            socket.bind(new InetSocketAddress(6666));
            //此时9999端口并没有打开,server会返回RST表示不可达,client端会抛出拒绝连接异常
            socket.connect(new InetSocketAddress("119.75.217.109", 9999), 10);
        }catch (SocketTimeoutException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}

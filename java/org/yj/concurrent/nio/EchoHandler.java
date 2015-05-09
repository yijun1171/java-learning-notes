package org.yj.concurrent.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SocketChannel;

/**
 * Created by yj on 15-5-2.
 */
public class EchoHandler implements Handler<String> {

    @Override
    public String handle(Channel channel, ByteBuffer buffer){
        SocketChannel socketChannel;
        if(channel instanceof SocketChannel)
            socketChannel = (SocketChannel) channel;
        else
            return null;
        System.out.println("handle");
        buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        try {
            socketChannel.read(buffer);
            System.out.println(buffer.position());
            buffer.flip();
            socketChannel.write(buffer);
            System.out.println(buffer.limit());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(buffer.array());
    }
}

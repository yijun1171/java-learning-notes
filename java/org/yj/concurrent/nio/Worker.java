package org.yj.concurrent.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

/**
 * Created by yj on 15-5-2.
 */
public class Worker implements Callable<String>{

    Handler<String> handler;

    SelectionKey selectionKey;

    boolean mode;//true:read false:write

    static boolean READ = true;

    static boolean WRITE = false;

    ByteBuffer buffer;

    Logger logger = Logger.getLogger(this.getClass().getName());

    public Worker(SelectionKey selectionKey, Handler<String> handler){
        this.selectionKey = selectionKey;
        this.handler = handler;
    }

    public void setMode(boolean mode){this.mode = mode;}

    @Override
    public String call() throws Exception {
        String message = null;
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        if(mode){//read
            message = handler.handle(socketChannel,buffer);
            logger.info("channel:"+socketChannel+"read:"+message);
        }else {
            buffer.flip();
            socketChannel.write(buffer);
            logger.info("channel:"+socketChannel+"write:"+new String(buffer.array()));
        }
        return message;
    }
}

package org.yj.concurrent.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by yj on 15-5-2.
 */
public class Multiplexer extends Thread{

    Server server;

    ServerSocketChannel serverChannel;

    Selector selector;

    Logger logger = Logger.getLogger(this.getClass().getName());

    int interestSet = SelectionKey.OP_CONNECT
                    | SelectionKey.OP_WRITE
                    | SelectionKey.OP_READ;

    public Multiplexer(Server server, int port){
        this.server = server;
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.socket().bind(new InetSocketAddress("localhost",port));
            serverChannel.configureBlocking(false);//non-blocking
            selector = Selector.open();
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        }
    }

    //负责监听端口 并等待到来的Channel完成connection
    @Override
    public void run(){
       logger.info("listening");
       while(true){
           //服务器关闭 收到中断
           if(Thread.interrupted()){
               try {
                   serverChannel.close();
                   selector.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
               logger.log(Level.INFO,"listening stop");
               return;
           }
           try {
               SocketChannel socketChannel = serverChannel.accept();
               if(socketChannel != null){
                   socketChannel.configureBlocking(false);
                   logger.log(Level.INFO, "new socket accepted");
                   socketChannel.register(selector, interestSet);
                   int readyChannels = selector.select();
                   if(readyChannels == 0) continue;//没有准备好的channel
               }
               Set<SelectionKey> selectedKeys = selector.selectedKeys();
               Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
               while (keyIterator.hasNext()){
                   SelectionKey key = keyIterator.next();
                   if(key.isConnectable()){
                       logger.log(Level.INFO,key.channel()+"connected");
                   }
                   if(key.isReadable()){
                       //server.handle(key); //转交给worker处理IO和逻辑
                       ByteBuffer buffer = ByteBuffer.allocate(1024);
                       SocketChannel s = ((SocketChannel) key.channel());
                       s.read(buffer);
                       System.out.println(new String(buffer.array()));
                       buffer.flip();
                       s.write(buffer);
                   }

                   keyIterator.remove();
               }

           } catch (IOException e) {
               e.printStackTrace();
           }
       }

    }
}

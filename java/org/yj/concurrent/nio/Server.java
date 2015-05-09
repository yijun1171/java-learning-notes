package org.yj.concurrent.nio;

import java.nio.channels.SelectionKey;

/**
 * Created by yj on 15-5-2.
 */
public interface Server {

    void start();

    void stop();

    void handle(SelectionKey selectionKey);
}

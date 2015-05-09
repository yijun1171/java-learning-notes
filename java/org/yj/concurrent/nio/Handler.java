package org.yj.concurrent.nio;

import java.nio.ByteBuffer;
import java.nio.channels.Channel;

/**
 * Created by yj on 15-5-2.
 */
public interface Handler<T> {

    T handle(Channel channel,ByteBuffer buffer);

}

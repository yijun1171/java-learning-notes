package org.yj.plugin.runtime;

import org.yj.plugin.PluginParam;

/**
 * Created by yijun.yj on 2015/8/5.
 * 默认worker实现类
 */
public class DefaultPluginWorker extends PluginWorker implements Runnable{

    private RuntimeMetaData metaData;

    @Override
    public int init() {
        return 0;
    }

    @Override
    public int prepare(PluginParam param) {
        return 0;
    }

    @Override
    public int execute(PluginParam param) {
        return 0;
    }

    @Override
    public int cleanUp() {
        return 0;
    }

    public void run() {

    }
}

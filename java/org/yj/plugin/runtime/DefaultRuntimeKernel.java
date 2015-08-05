package org.yj.plugin.runtime;

import org.yj.plugin.PluginManager;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by yijun.yj on 2015/8/5.
 */
public class DefaultRuntimeKernel implements RuntimeKernel{

    private PluginManager context;

    ThreadPoolExecutor executors;

    public void start() {

    }
}

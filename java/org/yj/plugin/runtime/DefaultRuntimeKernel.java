package org.yj.plugin.runtime;

import org.yj.plugin.DefaultPluginManager;
import org.yj.plugin.Plugin;
import org.yj.plugin.PluginManager;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yijun.yj on 2015/8/5.
 */
public class DefaultRuntimeKernel implements RuntimeKernel{

    private PluginManager manager;

    ExecutorService executors;

    public void init(){
        manager = new DefaultPluginManager();
        manager.init();
        executors = Executors.newFixedThreadPool(5);
    }

    public void start() {
        List<Plugin> plugins = manager.getPlugins();
        for(Plugin plugin : plugins){
            executors.submit(new DefaultPluginWorker(new PrintWork(), plugin));
        }
    }
}

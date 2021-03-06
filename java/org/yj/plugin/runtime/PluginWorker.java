package org.yj.plugin.runtime;

import org.yj.plugin.Plugin;
import org.yj.plugin.PluginParam;

import java.lang.reflect.Method;

/**
 * Created by yijun.yj on 2015/8/5.
 * 每个插件对应一个Worker
 */
public abstract class PluginWorker {


    private Plugin plugin;

    public abstract int init();

    public abstract int prepare(PluginParam param);

    public abstract int execute(PluginParam param);

    public abstract int cleanUp();

}

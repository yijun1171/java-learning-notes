package org.yj.plugin.runtime;

import org.yj.plugin.AbstractPlugin;
import org.yj.plugin.DefaultPluginParam;
import org.yj.plugin.Plugin;

/**
 * Created by yijun.yj on 2015/8/6.
 */
public class PrintWork implements Work{

    DefaultPluginParam param = new DefaultPluginParam();

    public void init(){
        param.putValue("worker", "PrintWorker");
    }

    public void execute(Plugin plugin) {
        System.out.println("This is the main work flow");
        AbstractPlugin p = (AbstractPlugin) plugin;
        p.before(param);
        System.out.println("Invoke plugin");

        plugin.execute(param);

        p.after(param);
    }
}

package org.yj.plugin.impl;

import org.yj.plugin.AbstractPlugin;
import org.yj.plugin.PluginParam;

/**
 * Created by yijun.yj on 2015/8/5.
 * 插件实现类
 */
public class DefaultPlugin extends AbstractPlugin {


    @Override
    public Object before(PluginParam param) {
        System.out.println("=====before=====");
        return null;
    }

    @Override
    public Object after(PluginParam param) {
        System.out.println("=====after=====");
        return null;
    }

    @Override
    public PluginParam getPluginParam() {
        return null;
    }

    public String say(){
        return "default";
    }
}

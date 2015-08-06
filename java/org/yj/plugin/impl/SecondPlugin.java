package org.yj.plugin.impl;

import org.yj.plugin.AbstractPlugin;
import org.yj.plugin.PluginParam;

/**
 * Created by yijun.yj on 2015/8/6.
 */
public class SecondPlugin extends AbstractPlugin{
    @Override
    public Object before(PluginParam param) {
        System.out.println("=====second plugin before==============");
        return null;
    }

    @Override
    public Object after(PluginParam param) {
        System.out.println("=====second plugin after==============");
        return null;
    }

    @Override
    public PluginParam getPluginParam() {
        return null;
    }

    public String say(){
        return "second";
    }
}

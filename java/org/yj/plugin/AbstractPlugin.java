package org.yj.plugin;

import org.yj.plugin.runtime.RuntimeMetaData;

/**
 * Created by yijun.yj on 2015/8/5.
 * 插件抽象类
 */
public abstract class AbstractPlugin implements Plugin{

    private RuntimeMetaData metaData;

    public Object execute(PluginParam param) {
        return null;
    }

    public abstract Object before(PluginParam param);

    public abstract Object after(PluginParam param);

    public abstract PluginParam getPluginParam();

    public RuntimeMetaData getMetaData(){
        return this.metaData;
    }
}

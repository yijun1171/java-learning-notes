package org.yj.plugin;

import org.yj.plugin.config.PluginConfig;

/**
 * Created by yijun.yj on 2015/8/5.
 * 插件管理器 默认实现类
 */
public class DefaultPluginManager extends PluginManager{


    @Override
    public void loadPlugin() {

    }

    @Override
    public void init() {

    }

    @Override
    public Plugin getPlugin(PluginConfig config) {
        return null;
    }

    /**
     * 注册元信息
     * @param config
     * @return
     */
    public Plugin register(PluginConfig config){
        return new Plugin() {
            public Object execute(PluginParam param) {
                return null;
            }
        };
    }

    /**
     * 卸载已注册的插件信息
     * @param config
     */
    public void unistall(PluginConfig config){}
}

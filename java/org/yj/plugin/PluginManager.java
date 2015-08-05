package org.yj.plugin;

import org.yj.plugin.config.PluginConfig;
import org.yj.plugin.config.PluginConfigContext;

import java.util.List;

/**
 * Created by yijun.yj on 2015/8/5.
 * 插件管理器
 */
public abstract class PluginManager {

    /**
     * 元信息上下文
     */
    private PluginConfigContext context;

    /**
     * 已加载的插件
     */
    private List<Plugin> plugins;

    /**
     * 从插件注册上下文加载插件
     */
    public abstract void loadPlugin();

    /**
     * 初始化
     */
    public abstract void init();

    /**
     * 获取已加载的插件
     * @param config
     * @return
     */
    public abstract Plugin getPlugin(PluginConfig config);
}

package org.yj.plugin;

import org.yj.plugin.config.JarLoader;
import org.yj.plugin.config.PluginConfig;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yijun.yj on 2015/8/5.
 * 插件管理器 默认实现类
 */
public class DefaultPluginManager extends PluginManager{


    @Override
    public void loadPlugin() {
        List<PluginConfig> configs = context.getConfigs();

        List<String> paths = new LinkedList<String>();
        for(PluginConfig config : configs){
            paths.add(config.getPath());
        }

        JarLoader loader = new JarLoader(JarLoader.getUrls(paths.toArray(new String[paths.size()])));
        for (PluginConfig config : configs){
            try {
                Class<?> clazz = loader.loadClass(config.getClassName());
                Plugin plugin = (Plugin) clazz.newInstance();
                plugins.put(config.getClassName(), plugin);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init() {
        super.init();
        loadPlugin();
    }

    public Plugin getPlugin(String className){
        PluginConfig cfg = new PluginConfig();
        cfg.setClassName(className);
        return getPlugin(cfg);
    }

    /**
     * 获取指定插件
     * @param config
     * @return
     */
    @Override
    public Plugin getPlugin(PluginConfig config){

        Plugin newPlugin = plugins.get(config.getClassName());
        if(newPlugin != null){
            return newPlugin;
        }

        newPlugin = loadPlugin(config);
        plugins.put(config.getClassName(), newPlugin);
        return newPlugin;
    }

    public List<Plugin> getPlugins(){
        return new LinkedList<Plugin>(plugins.values());
    }

    /**
     * 加载指定插件
     * @param config
     * @return
     */
    private Plugin loadPlugin(PluginConfig config) {
        URL[] urls = JarLoader.getUrl(config.getPath());
        JarLoader loader = new JarLoader(urls);
        Plugin plugin = null;
        try {
            Class<?> clazz = loader.loadClass(config.getClassName());
            plugin = (Plugin) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return plugin;
    }

    /**
     * 注册元信息
     * @param config
     * @return
     */
    public Plugin register(PluginConfig config){
        context.addPluginConfig(config);
        Plugin plugin = getPlugin(config);
        plugins.put(config.getClassName(), plugin);
        return plugin;
    }

    /**
     * 卸载已注册的插件信息
     * @param config
     */
    public void unistall(PluginConfig config){
        context.removePluginConfig(config);
        plugins.remove(config.getClassName());
    }
}

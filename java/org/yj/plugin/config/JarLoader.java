package org.yj.plugin.config;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by yijun.yj on 2015/8/5.
 * 插件 类加载器
 */
public class JarLoader extends URLClassLoader{

    public JarLoader(URL[] urls, ClassLoader classLoader) {
        super(urls, classLoader);
    }

    public JarLoader(URL[] urls) {
        super(urls);
    }

    public URL[] getUrl(String path){
        return new URL[]{};
    }

    public URL[] getUrls(String[] paths){
        return new URL[]{};
    }
}

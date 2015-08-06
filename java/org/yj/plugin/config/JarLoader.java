package org.yj.plugin.config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yijun.yj on 2015/8/5.
 * 插件 类加载器
 */
public class JarLoader extends URLClassLoader {

    public JarLoader(URL[] urls, ClassLoader classLoader) {
        super(urls, classLoader);
    }

    public JarLoader(URL[] urls) {
        super(urls);
    }

    public static URL[] getUrl(String path) {
        try {
            return new URL[]{new File(path).toURI().toURL()};
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static URL[] getUrls(String[] paths) {
        List<URL> list = new LinkedList<URL>();
        for (String path : paths) {
            try {
                URL url = new File(path).toURI().toURL();
                list.add(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return list.toArray(new URL[]{});
    }
}

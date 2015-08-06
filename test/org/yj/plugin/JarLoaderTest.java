package org.yj.plugin;

import org.junit.Test;
import org.yj.plugin.config.JarLoader;
import org.yj.plugin.impl.DefaultPlugin;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by yijun.yj on 2015/8/6.
 */
public class JarLoaderTest {

    private JarLoader loader;

    @Test
    public void getUrlTest(){
        URL[] urls =  JarLoader.getUrl("D:\\hsf");
        assertTrue(urls.length != 0);

        urls = JarLoader.getUrls(new String[]{"D:\\hsf", "D:\\code"});
        assertEquals(2, urls.length);
    }

    @Test
    public void loadClassTest(){
        URL[] urls = JarLoader.getUrl("D:\\");
        loader = new JarLoader(urls);
        try {
            Class<?> pluginClass = loader.loadClass("org.yj.plugin.impl.DefaultPlugin");
            DefaultPlugin plugin = (DefaultPlugin) pluginClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

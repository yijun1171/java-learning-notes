package org.yj.plugin;

import org.junit.Before;
import org.junit.Test;
import org.yj.plugin.impl.DefaultPlugin;
import org.yj.plugin.impl.SecondPlugin;

import static org.junit.Assert.assertEquals;

/**
 * Created by yijun.yj on 2015/8/6.
 */
public class PluginManagerTest {

    DefaultPluginManager manager = new DefaultPluginManager();

    @Before
    public void init(){
        manager.init();
    }


    @Test
    public void loadTest(){
        DefaultPlugin plugin = (DefaultPlugin) manager.getPlugin("org.yj.plugin.impl.DefaultPlugin");
        SecondPlugin splugin = (SecondPlugin) manager.getPlugin("org.yj.plugin.impl.SecondPlugin");

        assertEquals("default", plugin.say());
        assertEquals("second", splugin.say());
    }
}

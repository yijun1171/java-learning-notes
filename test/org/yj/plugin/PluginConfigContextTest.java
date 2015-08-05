package org.yj.plugin;

import org.junit.Before;
import org.junit.Test;
import org.yj.plugin.config.PluginConfig;
import org.yj.plugin.config.PluginConfigContext;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by yijun.yj on 2015/8/5.
 */
public class PluginConfigContextTest {

    PluginConfigContext configContext;

    @Before
    public void init(){
        configContext = new PluginConfigContext();
    }

    @Test
    public void parseXmlTest(){
        List<PluginConfig> configs = configContext.loadPluginConfig();
        assertEquals(configs.size(), 1);
    }
}

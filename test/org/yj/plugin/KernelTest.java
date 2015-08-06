package org.yj.plugin;

import org.junit.Test;
import org.yj.plugin.runtime.DefaultRuntimeKernel;
import org.yj.plugin.runtime.RuntimeKernel;

/**
 * Created by yijun.yj on 2015/8/6.
 */
public class KernelTest {

    DefaultRuntimeKernel kernel = new DefaultRuntimeKernel();

    @Test
    public void startTest(){
        kernel.init();
        kernel.start();
    }
}

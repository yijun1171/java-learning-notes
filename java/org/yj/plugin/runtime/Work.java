package org.yj.plugin.runtime;

import org.yj.plugin.Plugin;

/**
 * Created by yijun.yj on 2015/8/6.
 * 业务流程接口
 */
public interface Work {

    public void init();

    public void execute(Plugin plugin);

}

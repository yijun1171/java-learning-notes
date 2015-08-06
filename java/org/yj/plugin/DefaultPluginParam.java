package org.yj.plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yijun.yj on 2015/8/6.
 */
public class DefaultPluginParam implements PluginParam{

    Map<Object, Object> kvs = new HashMap<Object, Object>();

    public Object getValue(Object key) {
        return kvs.get(key);
    }

    public Object putValue(Object key, Object value){
        return kvs.put(key, value);
    }
}

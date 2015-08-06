package org.yj.plugin.runtime;

import java.util.HashMap;

/**
 * Created by yijun.yj on 2015/8/5.
 * 运行时元数据
 */
public class RuntimeMetaData {

    private HashMap<Object, Object> kvs = new HashMap<Object, Object>();

    public Object getValue(Object key){
        return kvs.get(key);
    }
}

package org.yj.system;

import org.junit.Test;

/**
 * Created by yijun.yj on 2015/8/5.
 * System相关api测试
 */
public class SystemTest {

    @Test
    public void getPropertiesTest(){
        //获取项目的根路径
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
    }
}

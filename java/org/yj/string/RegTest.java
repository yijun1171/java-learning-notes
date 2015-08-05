package org.yj.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yijun.yj on 2015/7/31.
 */
public class RegTest {

    static Pattern pattern = Pattern.compile("\\((fd\\w+a)\\)fds");

    public static void main(String[] args){
        Matcher m = pattern.matcher("(fdfffa)fds");

        //使用分组捕获 一定要先调用matches
        System.out.println(m.matches());
        System.out.println(m.group(0));
        System.out.println(m.group(1));
    }
}

package org.yj.concurrent.io;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by yj on 15-5-1.
 */
public class EncodeTest {

    public static void main(String[] args){
        String name = "hello你好";
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(1024);
        OutputStreamWriter out = new OutputStreamWriter(buffer, Charset.forName("utf-8"));
        try {
            out.write(name);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes = buffer.toByteArray();
        System.out.println(bytes.length);

        InputStreamReader isr = new InputStreamReader(
                new ByteArrayInputStream(bytes),Charset.forName("utf-8"));
        char[] c_buffer = new char[1024];
        try {
            isr.read(c_buffer);
            String name2 = new String(c_buffer);
            System.out.println(name2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

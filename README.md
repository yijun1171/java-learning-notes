# java学习笔记的测试代码

## Apache commons



### Lang
加强java.lang的核心api，提供一些操纵String，数值的方法，反射，并发，创建对象和序列化和系统参数。还有有一些额外的工具帮助构造hashCode，toString，equals这样的核心方法

Lang3的包结构
1. lang3:增强jdk中lang包的核心api
2. lang3.builder:协助构建一致的 equals(Object), toString(), hashCode(),compareTo(Object)方法
3. lang3.concurrent:多线程支持
4. lang3.event:提供一些基于事件的工具
5. lang3.exception
6. lang3.math:扩展java.math
7. lang3.mutable:提供对基本值和基本对象的可变wrapper
8. lang3.reflect:聚集一些反射的高级用法
9. lang3.text
10. lang3.time
11. lang3.tuple


#### ArraysUtils
主要是在 java.util.Arrays 基础上提供对null的数组提供了安全的操作,操作数组是null的话不会抛出异常

#### Range
补充java中没有的range这一数据结构，基于comparable接口实现，或者提供Comparator

#### SerializationUtils
支持以byte数组和字节流作为输入输出的序列化，roundtrip方法可以将对象序列化后立即反序列化，方便用于测试序列化测试

#### StringUtils
最常用的工具类,保证null安全，在String类上增加了新的字符串操纵方法

###

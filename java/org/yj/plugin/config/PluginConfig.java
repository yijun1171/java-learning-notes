package org.yj.plugin.config;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by yijun.yj on 2015/8/5.
 * 插件基本配置，描述插件的元数据
 */
public class PluginConfig {

    private String version;

    private String name;

    /**
     * jar包路径
     */
    private String path;

    /**
     * 权限定类名
     */
    private String className;

    /**
     * 类型 class interface
     */
    private String type;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PluginConfig that = (PluginConfig) o;

        return new EqualsBuilder()
                .append(version, that.version)
                .append(name, that.name)
                .append(path, that.path)
                .append(className, that.className)
                .append(type, that.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(version)
                .append(name)
                .append(path)
                .append(className)
                .append(type)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "PluginConfig{" +
                "version=" + version +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", className='" + className + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

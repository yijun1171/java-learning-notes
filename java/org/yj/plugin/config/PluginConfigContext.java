package org.yj.plugin.config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.yj.xml.XmlUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by yijun.yj on 2015/8/5.
 * 插件注册上下文
 */
public class PluginConfigContext {

    /**
     * 已注册的插件元信息
     */
    private List<PluginConfig> pluginConfigs;

    private static final String cfg = System.getProperty("user.dir") + File.separator + "resource" + File.separator + "plugin.xml";

    private volatile boolean init = false;

    public void init() {
        if (!init) {
            pluginConfigs = loadPluginConfig();
            init = true;
        }
    }

    /**
     * 从配置文件加载,解析xml
     *
     * @return
     */
    public List<PluginConfig> loadPluginConfig() {
        List<PluginConfig> ret = new LinkedList<PluginConfig>();
        Document doc = XmlUtils.parse(cfg);

        Element root = doc.getDocumentElement();
        NodeList plugins = root.getChildNodes();
        for (int i = 0; i < plugins.getLength(); i++) {
            Node node = plugins.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                //plugin
                Element child = (Element) node;
                ret.add(buildConfigFromElement(child));
            }
        }

        return ret;
    }

    /**
     * 从plugin Element构造config
     *
     * @param e
     * @return
     */
    private PluginConfig buildConfigFromElement(Element e) {
        NodeList list = e.getChildNodes();
        PluginConfig config = new PluginConfig();

        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element childEle = (Element) node;
                String name = childEle.getNodeName();

                if (name.equals("version")) {
                    config.setVersion(childEle.getTextContent());
                } else if(name.equals("name")){
                    config.setName(childEle.getTextContent());
                } else if(name.equals("path")){
                    config.setPath(childEle.getTextContent());
                } else if(name.equals("className")){
                    config.setClassName(childEle.getTextContent());
                } else if(name.equals("type")){
                    config.setType(childEle.getTextContent());
                } else {
                    throw new NoSuchElementException("No such element, Node name = " + name);
                }

            }
        }
        return config;
    }

    /**
     * 增加
     *
     * @param config
     */
    public void addPluginConfig(PluginConfig config) {
        pluginConfigs.add(config);
    }

    /**
     * 删除
     *
     * @param config
     */
    public void removePluginConfig(PluginConfig config) {
        pluginConfigs.remove(config);
    }

    public List<PluginConfig> getConfigs(){
        return pluginConfigs;
    }

}

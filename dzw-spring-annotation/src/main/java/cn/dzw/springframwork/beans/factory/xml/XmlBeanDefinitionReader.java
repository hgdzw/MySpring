package cn.dzw.springframwork.beans.factory.xml;

import cn.dzw.springframwork.beans.PropertyValue;
import cn.dzw.springframwork.beans.factory.config.BeanDefinition;
import cn.dzw.springframwork.beans.factory.config.BeanReference;
import cn.dzw.springframwork.beans.factory.support.AbstractBeanDefinitionReader;
import cn.dzw.springframwork.beans.factory.support.BeanDefinitionRegistry;
import cn.dzw.springframwork.context.annotation.ClassPathBeanDefinitionScanner;
import cn.dzw.springframwork.core.io.Resource;
import cn.dzw.springframwork.core.io.ResourceLoader;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;

/**
 * @Classname XmlBeanDefinitionReader
 * @Description 真正加载 xml 的时候 用到的 类
 * @Date 2022/8/30 14:48
 * @Created by dongzhiwei
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {


    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry,ResourceLoader resourceLoader) {
        super(resourceLoader, beanDefinitionRegistry);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {
        try {
            InputStream inputStream = resource.getInputStream();
            doLoadBeanDefinitions(inputStream);
        } catch (Exception e) {
            System.out.println("加载bean错误"+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 加载 xml 里面的 并且注册到bean
     * @param inputStream
     */
    public void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        //注册bean
        for (int i = 0; i < childNodes.getLength(); i++) {
            // 判断元素
            if (!(childNodes.item(i) instanceof Element)) continue;

            //包扫描的
            if ("component-scan".equals(childNodes.item(i).getNodeName())) {
                Element bean = (Element) childNodes.item(i);
                String packName = bean.getAttribute("base-package");
                if (StrUtil.isEmpty(packName)) {
                    System.out.println("包名不能为空");
                    return;
                }
                scanBase(packName);
            }

            // 判断对象
            if ("bean".equals(childNodes.item(i).getNodeName())) {
                // 解析标签
                Element bean = (Element) childNodes.item(i);
                String id = bean.getAttribute("id");
                String name = bean.getAttribute("name");
                String className = bean.getAttribute("class");
                String scope = bean.getAttribute("scope");
                // 获取 Class，方便获取类中的名称
                Class<?> clazz = Class.forName(className);
                // 优先级 id > name
                String beanName = StrUtil.isNotEmpty(id) ? id : name;
                if (StrUtil.isEmpty(beanName)) {
                    beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                }

                // 定义Bean
                BeanDefinition beanDefinition = new BeanDefinition(clazz);
                if (StrUtil.isNotEmpty(scope)) {
                    beanDefinition.setScope(scope);
                }
                // 读取属性并填充
                for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                    if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
                    if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
                    // 解析标签：property
                    Element property = (Element) bean.getChildNodes().item(j);
                    String attrName = property.getAttribute("name");
                    String attrValue = property.getAttribute("value");
                    String attrRef = property.getAttribute("ref");
                    // 获取属性值：引入对象、值对象
                    Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                    // 创建属性信息
                    PropertyValue propertyValue = new PropertyValue(attrName, value);
                    beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                }
                if (getBeanDefinitionRegistry().containsBeanDefinition(beanName)) {
                    System.out.println("Duplicate beanName[" + beanName + "] is not allowed");
                }
                // 注册 BeanDefinition
                getBeanDefinitionRegistry().registryBeanDefinition(beanName, beanDefinition);
            }
        }
    }

    /**
     * 扫描包下的所有bean
     * @param packName
     */
    private void scanBase(String packName) {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getBeanDefinitionRegistry());
        scanner.doScan(packName);
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for (Resource resource : resources) {
            this.loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations){
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }
}

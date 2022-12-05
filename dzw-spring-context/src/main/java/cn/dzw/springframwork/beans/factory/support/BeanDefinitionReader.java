package cn.dzw.springframwork.beans.factory.support;

import cn.dzw.springframwork.core.io.Resource;
import cn.dzw.springframwork.core.io.ResourceLoader;

/**
 * @Classname BeanDefinitionReader
 * @Description bean 的读取
 * @Date 2022/8/30 14:38
 * @Created by dongzhiwei
 */
public interface BeanDefinitionReader {

    ResourceLoader getResourceLoader();

    BeanDefinitionRegistry getBeanDefinitionRegistry();

    void loadBeanDefinitions(Resource resource);

    void loadBeanDefinitions(Resource... resources);

    void loadBeanDefinitions(String location);

    void loadBeanDefinitions(String... location);


}

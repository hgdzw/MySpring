package cn.dzw.springframwork.beans.factory.support;

import cn.dzw.springframwork.beans.factory.ConfigurableListableBeanFactory;
import cn.dzw.springframwork.beans.factory.config.BeanDefinition;
import cn.dzw.springframwork.utils.StringValueResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname DefaultListableBeanFactory
 * @Description 核心类实现
 * @Date 2022/8/26 14:13
 * @Created by dongzhiwei
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();



    @Override
    public Boolean containsBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        return beanDefinition != null;
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) {
        List<String> beanNames = new ArrayList<>();
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            Class beanClass = entry.getValue().getBeanClass();
            if (requiredType.isAssignableFrom(beanClass)) {
                beanNames.add(entry.getKey());
            }
        }
        if (1 == beanNames.size()) {
            return getBean(beanNames.get(0), requiredType);
        }
        return null;
    }

    /**
     * 这个是真正的实现了
     * @param type
     * @param <T>
     * @return
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type)  {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((name,beanDefinition)->{
            Class beanClass = beanDefinition.getBeanClass();
            //如果是他的父级就返回
            if (type.isAssignableFrom(beanClass)) {
                result.put(name, (T) getBean(name));
            }
        });
        return result;
    }

    @Override
    public List<String> getBeanDefinitionNames() {
        ArrayList<String> nameList = new ArrayList<>();
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            nameList.add(entry.getKey());
        }
        return nameList;
    }
}

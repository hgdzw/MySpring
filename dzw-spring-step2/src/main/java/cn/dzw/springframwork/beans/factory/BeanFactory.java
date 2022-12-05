package main.java.cn.dzw.springframwork.factory;

import main.java.cn.dzw.springframwork.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname BeanFactory
 * @Description bean 工厂
 * @Date 2022/8/26 10:55
 * @Created by dongzhiwei
 */
public interface BeanFactory {


    Object getBean(String name);

    /**
     * 多个 参数的bean
     * @param beanName
     * @return
     */
    Object getBean(String beanName,Object... args);


}

package cn.dzw.springframwork.beans.factory.support;

import cn.dzw.springframwork.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname DefaultSingletonBeanRegistry
 * @Description 默认的 单例bean 注册 实现
 * @Date 2022/8/26 13:44
 * @Created by dongzhiwei
 */
abstract class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 盛放 bean 的容器
     */
    private Map<String, Object> singletonObjects = new HashMap<String, Object>();


    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 添加bean  不然去哪里获取
     * @param beanName
     * @param singletonObject
     */
    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }



}

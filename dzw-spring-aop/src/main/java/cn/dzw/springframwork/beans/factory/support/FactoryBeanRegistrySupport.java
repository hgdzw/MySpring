package cn.dzw.springframwork.beans.factory.support;

import cn.dzw.springframwork.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname FactoryBeanRegistrySupport
 * @Description 用户自己定义的bean 的注册类
 * @Date 2022/11/1 16:44
 * @Created by dongzhiwei
 */
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{

    private Map<String, Object> factoryBeanObjectCache = new HashMap<String, Object>();

    /**
     * 直接从缓存中读取 只需要名字
     * @param beanName
     * @return
     */
    protected Object getCachedObjectForFactoryBean(String beanName) {
        return this.factoryBeanObjectCache.get(beanName);
    }


    protected Object getObjectForFactoryBean(FactoryBean factoryBean,String beanName) {
        if (factoryBean.isSingleton()) {
            Object bean = this.getCachedObjectForFactoryBean(beanName);
            if (null == bean) {
                Object object = factoryBean.getObject();
                this.factoryBeanObjectCache.put(beanName, object);
                return object;
            }
            return bean;
        }else {
            return factoryBean.getObject();
        }
    }


}

package cn.dzw.springframwork.beans.factory.support;

import cn.dzw.springframwork.beans.factory.BeanFactory;
import cn.dzw.springframwork.beans.factory.FactoryBean;
import cn.dzw.springframwork.beans.factory.config.BeanDefinition;
import cn.dzw.springframwork.beans.factory.config.BeanPostProcessor;
import cn.dzw.springframwork.beans.factory.config.ConfigurableBeanFactory;
import cn.dzw.springframwork.utils.ClassUtils;
import cn.dzw.springframwork.utils.StringValueResolver;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.org.apache.bcel.internal.generic.RET;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Classname AbstractBeanFactory
 * @Description 抽象 bean 工厂  一个单例功能和获取bean的功能
 * @Date 2022/8/26 11:26
 * @Created by dongzhiwei
 */
abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {


    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();


    List<StringValueResolver> resolverList = new ArrayList<StringValueResolver>();

    /**
     * 创建bean
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args);

    @Override
    public Object getBean(String beanName) {
        //获取 单例bean 获取不到就创建
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return (T)getBean(name);
    }

    public Object doGetBean(String beanName, Object... args) {
        Object singleton = getSingleton(beanName);
        if (null != singleton) {
            //看是不是继承了 FactoryBean
            return getObjectForBeanInstance(singleton, beanName);
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        Object bean = createBean(beanName, beanDefinition, args);
        return getObjectForBeanInstance(bean, beanName);
    }

    /**
     * 获取是否是用户自定义的 bean
     * @param instance
     * @param beanName
     * @return
     */
    private Object getObjectForBeanInstance(Object instance,String beanName) {
        if (!(instance instanceof FactoryBean)) {
            return instance;
        }
        //是bean 就去找缓存
        Object bean = getCachedObjectForFactoryBean(beanName);
        if (bean == null) {
            FactoryBean factoryBean = (FactoryBean) instance;
            bean = getObjectForFactoryBean(factoryBean, beanName);
        }
        return bean;
    }

    /**
     * 获取bean 定义
     * @param beanName
     * @return
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        resolverList.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        if (CollUtil.isNotEmpty(resolverList)) {
            for (StringValueResolver stringValueResolver : resolverList) {
                String stringValue = stringValueResolver.resolveStringValue(value);
                if (StrUtil.isNotEmpty(stringValue)) {
                    return stringValue;
                }
            }
        }
        return null;
    }

    /**
     * 获取所有的
     * @return
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
}

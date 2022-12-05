package cn.dzw.springframwork.beans.factory.support;

import cn.dzw.springframwork.beans.PropertyValue;
import cn.dzw.springframwork.beans.PropertyValues;
import cn.dzw.springframwork.beans.factory.config.BeanReference;
import cn.hutool.core.bean.BeanUtil;
import cn.dzw.springframwork.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @Classname AbstractAutowireCapableBeanFactory
 * @Description 是一个抽象类
 * @Date 2022/8/26 11:28
 * @Created by dongzhiwei
 */
abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object... args) {
        Object bean = null;
        try {
            //如果有
//            bean = beanDefinition.getBeanClass().newInstance();
            bean = createBeanInstance(beanDefinition,beanName,args);
            //进行属性填充
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //添加到 单例bean 列表里面去
        addSingleton(beanName, bean);
        return bean;
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        //属性还没放进去呢
        List<PropertyValue> propertyValueList = propertyValues.getPropertyValueList();
        for (PropertyValue propertyValue : propertyValueList) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            //可能是引用的bean
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }
            BeanUtil.setProperty(bean, name, value);
        }
    }


    /**
     * 创建实例
     * @param beanDefinition
     * @param beanName
     * @param args
     */
    public Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);

    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}

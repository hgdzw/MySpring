package cn.dzw.springframwork.beans.factory.support;

import cn.dzw.springframwork.beans.PropertyValue;
import cn.dzw.springframwork.beans.PropertyValues;
import cn.dzw.springframwork.beans.factory.Aware;
import cn.dzw.springframwork.beans.factory.BeanClassLoaderAware;
import cn.dzw.springframwork.beans.factory.BeanFactoryAware;
import cn.dzw.springframwork.beans.factory.BeanNameAware;
import cn.dzw.springframwork.beans.factory.config.AutowireCapableBeanFactory;
import cn.dzw.springframwork.beans.factory.config.BeanPostProcessor;
import cn.dzw.springframwork.beans.factory.config.BeanReference;
import cn.hutool.core.bean.BeanUtil;
import cn.dzw.springframwork.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @Classname AbstractAutowireCapableBeanFactory
 * @Description 是一个抽象类  每一个bean的具体操作都在这里了
 * @Date 2022/8/26 11:28
 * @Created by dongzhiwei
 */
abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object... args) {
        Object bean = null;
        try {
            //

            //如果有
//            bean = beanDefinition.getBeanClass().newInstance();
            bean = createBeanInstance(beanDefinition,beanName,args);
            //进行属性填充
            applyPropertyValues(beanName, bean, beanDefinition);
            //执行初始化方法 和beanPostProcessor 的前置和后置处理 方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果是单例的话 添加到 单例bean 列表里面去
        if (beanDefinition.isSingleton()) {
            registerSingleton(beanName, bean);
        }
        return bean;
    }


    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition){
        //将用户自定义bean 需要的给设置进去
        if (bean instanceof Aware) {
            //名称
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
            if (bean instanceof BeanClassLoaderAware) {
                ((BeanClassLoaderAware) bean).setClassLoader(getBeanClassLoader());
            }
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
        }

        // 找到 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(beanName, bean);

        // todo 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 找到 BeanPostProcessor after 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }


    /**
     * 执行前置处理
     * @param beanName
     * @param bean
     */
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(String beanName, Object bean){
        // 找到所有自定义的bean before 执行
        Object result = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            //一个执行链  前一个的返回值当下一个的入参
            Object o = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (o == null) {
                return result;
            }else {
                result = o;
            }
        }
        return result;
    }

    /**
     * 后置处理
     * @param existingBean
     * @param beanName
     * @return
     */
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)  {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
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

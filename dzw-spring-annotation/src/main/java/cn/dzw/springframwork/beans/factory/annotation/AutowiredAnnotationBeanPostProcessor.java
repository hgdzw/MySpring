package cn.dzw.springframwork.beans.factory.annotation;

import cn.dzw.springframwork.beans.PropertyValues;
import cn.dzw.springframwork.beans.factory.BeanFactory;
import cn.dzw.springframwork.beans.factory.BeanFactoryAware;
import cn.dzw.springframwork.beans.factory.config.InstantiationAwareBeanPostProcessor;
import cn.dzw.springframwork.beans.factory.support.DefaultListableBeanFactory;
import cn.dzw.springframwork.utils.ClassUtils;
import cn.hutool.core.bean.BeanUtil;

import java.lang.reflect.Field;

/**
 * @Classname AutowiredAnnotationBeanPostProcessor
 * @Description Autowire 注解的逻辑调用实现
 * @Date 2022/11/25 13:44
 * @Created by dongzhiwei
 */
public class AutowiredAnnotationBeanPostProcessor implements BeanFactoryAware, InstantiationAwareBeanPostProcessor {

    private DefaultListableBeanFactory beanFactory;


    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) {
        Class<?> clazz = bean.getClass();
        //是否为 CGlib 创建对象，否则是不能正确拿到类信息的
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        Field[] fields = clazz.getDeclaredFields();
        //属性注入
        for (Field field : fields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (null != valueAnnotation) {
                String value = valueAnnotation.value();
                String embeddedValue = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setFieldValue(bean, field.getName(), embeddedValue);
            }
        }

        // 对象注入
        for (Field field : fields) {
            Autowired autowired = field.getAnnotation(Autowired.class);
            if (null != autowired) {
                Class<?> fieldType = field.getType();
                Object dependentBean = null;
                // 这两个注解一般都是一起的
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                if (null != qualifierAnnotation) {
                    dependentBean = beanFactory.getBean(qualifierAnnotation.value(), fieldType);
                }else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }
        return pvs;
    }

    /**
     * 代理的对象逻辑不做处理
     * @param beanClass
     * @param beanName
     * @return
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        return null;
    }


    /**
     * 两个前置 和后置方法
     * @param result 执行上一个的返回值
     * @param beanName 执行当前bean的名称
     * @return
     */
    @Override
    public Object postProcessBeforeInitialization(Object result, String beanName) {
        return result;
    }

    @Override
    public Object postProcessAfterInitialization(Object result, String beanName) {
        return result;
    }
}

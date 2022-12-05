package cn.dzw.springframwork.beans.factory.config;

import cn.dzw.springframwork.beans.PropertyValues;

/**
 * @Classname InstantiationAwareBeanPostProcessor
 * @Description 对bean 执行 实例化 之前执行
 * @Date 2022/11/23 10:49
 * @Created by dongzhiwei
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * 这个是 aop 返回代理对象的方法
     * @param beanClass
     * @param beanName
     * @return
     */
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName);

    /**
     * 对有这几个注解的 进行属性填充
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     */
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean
            , String beanName);

}

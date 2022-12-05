package cn.dzw.springframwork.beans.factory.config;

/**
 * @Classname InstantiationAwareBeanPostProcessor
 * @Description 对bean 执行 实例化 之前执行
 * @Date 2022/11/23 10:49
 * @Created by dongzhiwei
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{


    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName);
}

package cn.dzw.springframwork.beans.factory.config;

/**
 * @Classname BeanPostProcessor
 * @Description 在bean 实例化之后的扩展
 * @Date 2022/10/19 16:33
 * @Created by dongzhiwei
 */
public interface BeanPostProcessor {

    /**
     * 在对象执行初始化之前 执行此方法
     * @param result 执行上一个的返回值
     * @param beanName 执行当前bean的名称
     */
    Object postProcessBeforeInitialization(Object result,String beanName);

    /**
     * 在对象执行初始化之后 执行此方法
     * @param result
     * @param beanName
     */
    Object postProcessAfterInitialization(Object result, String beanName);

}

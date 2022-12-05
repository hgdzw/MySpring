package cn.dzw.springframwork.beans.factory;

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

    /**
     * 根据类型获取bean
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     */
    <T> T getBean(String name, Class<T> requiredType);
}

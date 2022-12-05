package cn.dzw.springframwork.beans.factory;

/**
 * @Classname FactoryBean
 * @Description 用户继承这个 可以自己 自定义创建 bean 实例
 * @Date 2022/11/1 16:54
 * @Created by dongzhiwei
 */
public interface FactoryBean<T> {

    /**
     * 返回自己实例化之后 的bean
     * @return
     */
    T getObject();

    /**
     * 获取类型
     * @return
     */
    Class<?> getObjectType();

    /**
     * 是否是单例
     * @return
     */
    Boolean isSingleton();

}

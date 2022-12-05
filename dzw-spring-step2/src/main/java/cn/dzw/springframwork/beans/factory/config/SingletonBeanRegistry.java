package main.java.cn.dzw.springframwork.factory.config;

/**
 * @Classname SingletonBeanRegistry
 * @Description 单例bean 的注册
 * @Date 2022/8/26 13:29
 * @Created by dongzhiwei
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}

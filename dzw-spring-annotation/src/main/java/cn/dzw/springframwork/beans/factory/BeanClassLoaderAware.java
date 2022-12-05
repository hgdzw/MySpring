package cn.dzw.springframwork.beans.factory;

/**
 * @Classname BeanClassLoaderAware
 * @Description 实现此接口，既能感知到所属的 ClassLoader
 * @Date 2022/11/1 15:07
 * @Created by dongzhiwei
 */
public interface BeanClassLoaderAware extends Aware {

    void setClassLoader(ClassLoader classLoader);
}

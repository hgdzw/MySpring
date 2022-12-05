package cn.dzw.springframwork.beans.factory;

import cn.dzw.springframwork.beans.factory.config.AutowireCapableBeanFactory;
import cn.dzw.springframwork.beans.factory.config.BeanDefinition;
import cn.dzw.springframwork.beans.factory.config.ConfigurableBeanFactory;
import cn.dzw.springframwork.utils.StringValueResolver;

/**
 * @Classname ConfigurableListableBeanFactory
 * @Description 提供分析和修改 Bean 以及预先实例化的操作接
 * 口
 * @Date 2022/10/24 15:42
 * @Created by dongzhiwei
 */
public interface ConfigurableListableBeanFactory extends  ListableBeanFactory, ConfigurableBeanFactory, AutowireCapableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName);

}

package cn.dzw.springframwork.beans.factory;

import java.util.List;
import java.util.Map;

/**
 * @Classname ListableBeanFactory
 * @Description 扩展bean 接口
 * @Date 2022/10/24 15:29
 * @Created by dongzhiwei
 */
public interface ListableBeanFactory extends BeanFactory {


    /**
     * 根据类型获取bean
     * @param type
     * @param <T>
     */
    <T> Map<String,T> getBeansOfType(Class<T> type);

    /**
     * 获取bean 名称
     * @return
     */
    List<String> getBeanDefinitionNames();

}

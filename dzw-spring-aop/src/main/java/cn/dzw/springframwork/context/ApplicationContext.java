package cn.dzw.springframwork.context;

import cn.dzw.springframwork.beans.factory.HierarchicalBeanFactory;
import cn.dzw.springframwork.beans.factory.ListableBeanFactory;
import cn.dzw.springframwork.core.io.ResourceLoader;

/**
 * @Classname ApplicationContext
 * @Description 应用上下文 做整合用
 * @Date 2022/10/22 16:24
 * @Created by dongzhiwei
 */
public interface ApplicationContext extends ListableBeanFactory,ApplicationEventPublisher, HierarchicalBeanFactory, ResourceLoader {


}

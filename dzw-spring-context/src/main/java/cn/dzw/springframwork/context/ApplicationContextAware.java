package cn.dzw.springframwork.context;

import cn.dzw.springframwork.beans.factory.Aware;

/**
 * @Classname ApplicationContextAware
 * @Description 实现此接口，既能感知到所属的 ApplicationContext
 * @Date 2022/11/1 14:49
 * @Created by dongzhiwei
 */
public interface ApplicationContextAware extends Aware {

    /**
     * 设置这个比较麻烦 不能直接在 createBean 里面设置 因为那里也没有  要单独用一个类 处理
     * @param applicationContext
     */
    void setApplicationContext(ApplicationContext applicationContext);

}

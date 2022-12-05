package cn.dzw.springframwork.context;

/**
 * @Classname ConfigurableApplicationContext
 * @Description
 * @Date 2022/10/24 14:47
 * @Created by dongzhiwei
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器  核心方法
     */
    void refresh();

}

package cn.dzw.springframwork.context;

import java.util.EventListener;

/**
 * @Classname ApplicationListener
 * @Description 监听事件的监听器
 * @Date 2022/11/3 16:49
 * @Created by dongzhiwei
 */
public interface ApplicationListener<T extends ApplicationEvent> extends EventListener {

    /**
     * 这里应该定义一个方法  只要是想监听的 都实现这个方法
     */
    void onApplicationEvent(T event);


}

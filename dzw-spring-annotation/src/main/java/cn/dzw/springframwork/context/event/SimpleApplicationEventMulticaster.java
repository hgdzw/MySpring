package cn.dzw.springframwork.context.event;

import cn.dzw.springframwork.context.ApplicationEvent;
import cn.dzw.springframwork.context.ApplicationListener;

/**
 * @Classname SimpleApplicationEventMulticaster
 * @Description 事件发送器的简单实现
 * @Date 2022/11/3 17:23
 * @Created by dongzhiwei
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {


    /**
     *
     * @param event the event to multicast
     */
    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}

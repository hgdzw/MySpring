package cn.dzw.springframwork.context;

/**
 * @Classname ApplicationEventPublisher
 * @Description 发送事件的发送器
 * @Date 2022/11/3 16:49
 * @Created by dongzhiwei
 */
public interface ApplicationEventPublisher {


    /**
     * 只有一个方法 就是发送事件
     */
    void pushEvent(ApplicationEvent applicationEvent);

}

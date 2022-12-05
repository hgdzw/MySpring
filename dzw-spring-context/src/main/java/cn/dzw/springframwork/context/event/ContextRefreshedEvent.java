package cn.dzw.springframwork.context.event;

/**
 * @Classname ContextRefreshedEvent
 * @Description 容器完成事件
 * @Date 2022/11/3 18:13
 * @Created by dongzhiwei
 */
public  class ContextRefreshedEvent extends ApplicationContextEvent {


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}

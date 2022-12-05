package cn.dzw.springframwork.context;

import java.util.EventObject;

/**
 * @Classname ApplicationEvent
 * @Description 所有的自定义事件都要继承这个 事件
 * @Date 2022/11/3 16:48
 * @Created by dongzhiwei
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }


}

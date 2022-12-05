package test.java.cn.dzw.springframwork.test.event;

import cn.dzw.springframwork.context.event.ApplicationContextEvent;

/**
 * @Classname MyEvent
 * @Description 自己的事件
 * @Date 2022/11/5 10:09
 * @Created by dongzhiwei
 */
public class MyEvent extends ApplicationContextEvent {

    private String name;

    private Integer age;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MyEvent(Object source,String name,Integer age) {
        super(source);
        this.name = name;
        this.age = age;
    }
}

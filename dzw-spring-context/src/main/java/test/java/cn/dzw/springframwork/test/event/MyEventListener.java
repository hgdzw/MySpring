package test.java.cn.dzw.springframwork.test.event;

import cn.dzw.springframwork.context.ApplicationListener;

/**
 * @Classname MyEventListener
 * @Description 自己的监听器
 * @Date 2022/11/5 10:11
 * @Created by dongzhiwei
 */
public class MyEventListener implements ApplicationListener<MyEvent> {


    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("监听到事件:" + event);
    }
}

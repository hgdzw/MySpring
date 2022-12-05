package test.java.cn.dzw.springframwork.test.bean;

import cn.dzw.springframwork.beans.factory.config.BeanPostProcessor;

/**
 * @Classname UserPostPrecoss
 * @Description 自己的实现类
 * @Date 2022/10/22 16:11
 * @Created by dongzhiwei
 */
public class MyUserPostProcess implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object result, String beanName) {
        if (beanName.equals("userService")) {
            UserService userService = (UserService) result;
            userService.setUid("1002");
            System.out.println("自己的BeanPostProcessor 前置方法");
        }
        return result;
    }

    @Override
    public Object postProcessAfterInitialization(Object result, String beanName) {
        if (beanName.equals("userService")) {
            System.out.println("userService 的BeanPostProcessor 后置方法");
        }
        return result;
    }
}

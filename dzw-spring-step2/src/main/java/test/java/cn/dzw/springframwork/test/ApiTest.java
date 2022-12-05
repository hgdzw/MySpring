package test.java.cn.dzw.springframwork.test;

import cn.dzw.springframwork.PropertyValue;
import cn.dzw.springframwork.PropertyValues;
import cn.dzw.springframwork.beans.factory.config.BeanReference;
import main.java.cn.dzw.springframwork.factory.config.BeanDefinition;
import cn.dzw.springframwork.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;
import test.java.cn.dzw.springframwork.test.bean.UserDao;
import test.java.cn.dzw.springframwork.test.bean.UserService;

/**
 * @Classname ApiTest
 * @Description TODO
 * @Date 2022/8/26 11:07
 * @Created by dongzhiwei
 */
public class ApiTest {

    @Test
    public void testFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService","123");
        userService.queryUserInfo();
        //第二次是来自 singleton 中
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }

    @Test
    public void testFactory2() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserDao.class);
        beanFactory.registryBeanDefinition("userDao", beanDefinition);

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid", "1001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        BeanDefinition userBbeanDefinition = new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registryBeanDefinition("userService", userBbeanDefinition);


        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        //第二次是来自 singleton 中
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }


}

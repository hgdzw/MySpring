package test.java.cn.dzw.springframwork.test;

import cn.dzw.springframwork.beans.PropertyValue;
import cn.dzw.springframwork.beans.PropertyValues;
import cn.dzw.springframwork.beans.factory.config.BeanReference;
import cn.dzw.springframwork.beans.factory.config.BeanDefinition;
import cn.dzw.springframwork.context.support.ClassPathXmlApplicationContext;
import cn.dzw.springframwork.beans.factory.support.DefaultListableBeanFactory;
import cn.dzw.springframwork.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.Test;
import test.java.cn.dzw.springframwork.test.bean.MyUserPostProcess;
import test.java.cn.dzw.springframwork.test.bean.UserDao;
import test.java.cn.dzw.springframwork.test.bean.UserService;
import test.java.cn.dzw.springframwork.test.event.MyEvent;

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
        propertyValues.addPropertyValue(new PropertyValue("uId", "1001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        BeanDefinition userBbeanDefinition = new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registryBeanDefinition("userService", userBbeanDefinition);


        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        //第二次是来自 singleton 中
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }


    @Test
    public void testFactory3() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //将所有的bean 加载进去
        reader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        //第二次是来自 singleton 中
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }

    @Test
    public void testFactory4() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //将所有的bean 加载进去
        reader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        //第二次是来自 singleton 中
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }

    /**
     * 加前置和后置
     */
    @Test
    public void testFactory5() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //将所有的bean 加载进去
        reader.loadBeanDefinitions("classpath:spring.xml");

        MyUserPostProcess myUserPostProcess = new MyUserPostProcess();
        beanFactory.addBeanPostProcessor(myUserPostProcess);

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        //第二次是来自 singleton 中
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }

    /**
     * 整合了 上下文 添加了扩展
     */
    @Test
    public void testFactory6() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
        System.out.println(userService.getUid());
    }

    /**
     * 加了一些aware 接口
     */
    @Test
    public void testFactory7() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
        System.out.println(userService.getUid());
    }

    /**
     * 加了 factoryBean 接口
     */
    @Test
    public void testFactory8() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factoryBean.xml");
        UserDao userDao = applicationContext.getBean("myFactoryBean", UserDao.class);
        System.out.println(userDao.getId());
    }

    /**
     *
     * 加了事件
     */
    @Test
    public void testFactory9() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factoryBean.xml");
        applicationContext.pushEvent(new MyEvent(this, "张三", 13));
        
    }


}

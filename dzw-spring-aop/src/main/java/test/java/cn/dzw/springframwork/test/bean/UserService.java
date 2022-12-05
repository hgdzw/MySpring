package test.java.cn.dzw.springframwork.test.bean;

import cn.dzw.springframwork.beans.factory.BeanClassLoaderAware;
import cn.dzw.springframwork.beans.factory.BeanFactory;
import cn.dzw.springframwork.beans.factory.BeanFactoryAware;
import cn.dzw.springframwork.beans.factory.BeanNameAware;
import cn.dzw.springframwork.context.ApplicationContext;
import cn.dzw.springframwork.context.ApplicationContextAware;

/**
 * @Classname UserService
 * @Description 用来注册的bean
 * @Date 2022/8/26 11:06
 * @Created by dongzhiwei
 */

public class UserService implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, ApplicationContextAware {

    private UserDao userDao;

    private String uId;


    public void queryUserInfo() {
        System.out.println("查询用户信息" + userDao.queryName(uId));
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getUid() {
        return uId;
    }

    public void setUid(String uId) {
        this.uId = uId;
    }

    @Override
    public void setClassLoader(ClassLoader classLoader) {
        System.out.println("classLoader是 :"+classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        System.out.println("beanFactory是:"+beanFactory);

    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("名字是:"+beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("context:"+applicationContext);

    }
}

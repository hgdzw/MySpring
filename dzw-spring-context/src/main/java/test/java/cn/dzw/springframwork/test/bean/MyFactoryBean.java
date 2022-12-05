package test.java.cn.dzw.springframwork.test.bean;

import cn.dzw.springframwork.beans.factory.FactoryBean;

/**
 * @Classname MyFactoryBean
 * @Description 自己实例化bean
 * @Date 2022/11/1 17:35
 * @Created by dongzhiwei
 */
public class MyFactoryBean implements FactoryBean<UserDao> {


    @Override
    public UserDao getObject() {
        UserDao userDao = new UserDao();
        userDao.setId("542124");
        return userDao;
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    @Override
    public Boolean isSingleton() {
        return true;
    }
}

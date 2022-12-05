package test.java.cn.dzw.springframwork.test.bean;

/**
 * @Classname UserService
 * @Description 用来注册的bean
 * @Date 2022/8/26 11:06
 * @Created by dongzhiwei
 */

public class UserService {

    private UserDao userDao;

    private String uid;


    public void queryUserInfo() {
        System.out.println("查询用户信息" + userDao.queryName(uid));
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

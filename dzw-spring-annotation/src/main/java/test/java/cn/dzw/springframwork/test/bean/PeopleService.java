package test.java.cn.dzw.springframwork.test.bean;

import cn.dzw.springframwork.stereotype.Component;

import java.util.Random;

/**
 * @Classname PeopleService
 * @Description 服务类
 * @Date 2022/11/5 11:29
 * @Created by dongzhiwei
 */
@Component
public class PeopleService implements IPeopleService {

    private String userName = "小明";

    @Override
    public String getUserName() {

        return userName;
    }

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
            System.out.println("这里是真正执行了queryUserInfo 方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "这是个人信息";
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }

}

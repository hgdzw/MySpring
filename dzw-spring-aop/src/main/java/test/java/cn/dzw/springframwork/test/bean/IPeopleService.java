package test.java.cn.dzw.springframwork.test.bean;

/**
 * @Classname IPeopleService
 * @Description 接口
 * @Date 2022/11/5 11:29
 * @Created by dongzhiwei
 */
public interface IPeopleService {


    String getUserName();

    String queryUserInfo();
    String register(String name);
}

package test.java.cn.dzw.springframwork.test.bean;

import cn.dzw.springframwork.beans.factory.annotation.Autowired;
import cn.dzw.springframwork.beans.factory.annotation.Value;
import cn.dzw.springframwork.stereotype.Component;

/**
 * @Classname PeopleAutoService
 * @Description TODO
 * @Date 2022/11/25 15:53
 * @Created by dongzhiwei
 */
@Component
public class PeopleAutoService implements IPeopleService {

    @Value("${token}")
    private String token;

    @Autowired
    private UserDao userDao;

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String queryUserInfo() {
        return userDao.queryName("1001") + token;
    }

    @Override
    public String register(String name) {
        return null;
    }
}

package test.java.cn.dzw.springframwork.test.bean;

import cn.dzw.springframwork.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname UserDao
 * @Description TODO
 * @Date 2022/8/30 13:43
 * @Created by dongzhiwei
 */
@Component
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<String, String>();

    private String id;

    static {
        hashMap.put("1001", "小王");
        hashMap.put("1002", "小李");
        hashMap.put("1003", "小戴");
        hashMap.put("1004", "小赵");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String queryName(String uid) {
        return hashMap.get(uid);
    }
}

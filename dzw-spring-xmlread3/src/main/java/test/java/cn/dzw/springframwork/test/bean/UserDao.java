package test.java.cn.dzw.springframwork.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname UserDao
 * @Description TODO
 * @Date 2022/8/30 13:43
 * @Created by dongzhiwei
 */
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<String, String>();

    static {
        hashMap.put("1001", "小王");
        hashMap.put("1002", "小李");
        hashMap.put("1003", "小戴");
        hashMap.put("1004", "小赵");
    }


    public String queryName(String uid) {
        return hashMap.get(uid);
    }
}

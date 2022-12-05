package cn.dzw.springframwork.core.io;

import cn.hutool.core.lang.Assert;

/**
 * @Classname DefaultResourceLoader
 * @Description 默认的资源加载器
 * @Date 2022/8/30 15:20
 * @Created by dongzhiwei
 */
public class DefaultResourceLoader implements ResourceLoader{


    @Override
    public Resource getResource(String location) {

        Assert.notNull(location, "location can not be null!");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            //系统文件
            return new ClassPathResource(location);
        }
        return null;
    }
}

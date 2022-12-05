package cn.dzw.springframwork.core.io;

/**
 * @Classname ResourceLoader
 * @Description 资源加载器
 * @Date 2022/8/30 14:33
 * @Created by dongzhiwei
 */
public interface ResourceLoader {


    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 获取资源
     * @param location
     * @return
     */
    Resource getResource(String location);

}

package cn.dzw.springframwork.aop;

/**
 * @Classname ClassFilter
 * @Description 类的过滤
 * @Date 2022/11/7 16:01
 * @Created by dongzhiwei
 */
public interface ClassFilter {


    boolean matches(Class<?> clazz);



}

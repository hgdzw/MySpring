package cn.dzw.springframwork.aop;

import cn.dzw.springframwork.utils.ClassUtils;

/**
 * @Classname TargetSource
 * @Description 目标类
 * @Date 2022/11/9 16:48
 * @Created by dongzhiwei
 */
public class TargetSource {

    /**
     * 目标方法
     */
    private Object target;


    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }


    public Object getTarget() {
        return this.target;
    }

}

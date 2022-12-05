package cn.dzw.springframwork.beans.factory;

/**
 * @Classname BeanNameAware
 * @Description 实现此接口，既能感知到所属的 BeanName
 * @Date 2022/11/1 14:47
 * @Created by dongzhiwei
 */
public interface BeanNameAware extends Aware {

    /**
     * 设置bean name
     * @param beanName
     */
    public void setBeanName(String beanName);
}

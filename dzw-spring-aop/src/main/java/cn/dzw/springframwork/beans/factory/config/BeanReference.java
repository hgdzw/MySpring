package cn.dzw.springframwork.beans.factory.config;

/**
 * @Classname BeanReference
 * @Description 这个作为value 说明也放在ioc 里面
 * @Date 2022/8/26 17:32
 * @Created by dongzhiwei
 */
public class BeanReference {


    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    /**
     * bean name
     */
    private String beanName;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}

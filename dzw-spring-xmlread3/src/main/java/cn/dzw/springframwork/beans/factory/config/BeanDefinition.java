package cn.dzw.springframwork.beans.factory.config;

import cn.dzw.springframwork.beans.PropertyValues;

/**
 * @Classname BeanDefinition
 * @Description 定义 bean 的属性信息
 * @Date 2022/8/26 11:01
 * @Created by dongzhiwei
 */
public class BeanDefinition {

    /**
     * 这里用class  可以将实例化操作交给容器
     */
    private Class bean;

    /**
     * 这个实例的属性
     */
    private PropertyValues propertyValues;

    public BeanDefinition(Class bean) {
        this.bean = bean;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class bean, PropertyValues propertyValues) {
        this.bean = bean;
        this.propertyValues = propertyValues == null ? new PropertyValues() : propertyValues;
    }

    public Class getBeanClass() {
        return bean;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}

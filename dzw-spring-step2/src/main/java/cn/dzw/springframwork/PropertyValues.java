package cn.dzw.springframwork;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname PropertyValues
 * @Description 对象的属性信息
 * @Date 2022/8/26 17:12
 * @Created by dongzhiwei
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();


    public void addPropertyValue(PropertyValue pv) {
        propertyValueList.add(pv);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyName.equals(propertyValue.getName())) {
                return propertyValue;
            }
        }
        return null;
    }


}

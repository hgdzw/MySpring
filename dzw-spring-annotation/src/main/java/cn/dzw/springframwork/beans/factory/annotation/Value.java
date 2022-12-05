package cn.dzw.springframwork.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * 用于属性注入的注解
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    String value();
}

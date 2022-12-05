package cn.dzw.springframwork.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * 属性上面注入
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.
        TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {
    String value() default "";
}


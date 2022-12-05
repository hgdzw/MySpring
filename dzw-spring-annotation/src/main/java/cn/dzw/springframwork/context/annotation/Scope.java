package cn.dzw.springframwork.context.annotation;

import java.lang.annotation.*;

/**
 * 配置bean 的作用域
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {
    /**
     * 默认单例的
     * @return
     */
    String value() default "singleton";
}



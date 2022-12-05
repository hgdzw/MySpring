package cn.dzw.springframwork.stereotype;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {

    String value() default "";
}

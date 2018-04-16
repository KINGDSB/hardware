package indi.dsb.hardware.common;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/12/8.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Log {
    String module() default "";  //模块名称
    String method() default "";  //方法名
}

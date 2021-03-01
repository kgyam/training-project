package org.example.web.mvc.controller;

import java.lang.annotation.*;

/**
 * @author kg yam
 * @date 2021-03-01 15:53
 * @since
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMethod {
    HttpMethod[] value() default HttpMethod.GET;
}

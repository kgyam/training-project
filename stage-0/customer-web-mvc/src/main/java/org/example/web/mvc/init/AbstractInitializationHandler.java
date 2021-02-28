package org.example.web.mvc.init;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.example.web.mvc.controller.Controller;
import org.example.web.mvc.controller.RequestMehodInfo;
import org.example.web.mvc.controller.RequestPath;
import org.example.web.mvc.servlet.DispatcherServlet;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

abstract class AbstractInitializationHandler implements InitializationHandler {
    DispatcherServlet dispatcherServlet;

    void initController(Iterator<? extends Controller> iterator) {
        Map<String, Object> controllerMap = dispatcherServlet.getControllerMap();
        Map<String, RequestMehodInfo> methodMap = dispatcherServlet.getMethodMap();
        iterator.forEachRemaining(controller -> {
            RequestPath requestPath = controller.getClass().getDeclaredAnnotation(RequestPath.class);
            if (requestPath != null) {
                String controllerPathValue = requestPath.value();
                controllerMap.putIfAbsent(controllerPathValue, controller);
                Method[] methods = controller.getClass().getDeclaredMethods();
                if (ArrayUtils.isEmpty(methods)) {
                    return;
                }
                /**
                 * 处理对应控制器的请求处理方法，将其封装到RequestMethodInfo并缓存到dispatcherServlet的集合中
                 */
                for (Method method : methods) {
                    RequestPath methodPath = method.getAnnotation(RequestPath.class);
                    if (methodPath != null && StringUtils.isNotBlank(methodPath.value())) {
                        String methodPathValue = methodPath.value();
                        methodMap.put(methodPathValue, new RequestMehodInfo(methodPathValue, method, controller));
                    }
                }
            }
        });
    }
}

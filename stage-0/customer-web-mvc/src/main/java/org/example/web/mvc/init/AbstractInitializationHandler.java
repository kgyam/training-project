package org.example.web.mvc.init;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.example.web.mvc.controller.*;
import org.example.web.mvc.servlet.DispatcherServlet;

import javax.servlet.ServletConfig;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

abstract class AbstractInitializationHandler implements InitializationHandler {
    DispatcherServlet dispatcherServlet;
    ServletConfig config;

    void initController(Iterator<? extends Controller> iterator) {
        Map<String, Object> controllerMap = dispatcherServlet.getControllerMap();
        Map<String, RequestMethodInfo> methodMap = dispatcherServlet.getMethodMap();
        iterator.forEachRemaining(controller -> {
            RequestMapping requestPath = controller.getClass().getDeclaredAnnotation(RequestMapping.class);
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
                    RequestMapping methodPath = method.getAnnotation(RequestMapping.class);
                    RequestMethod requestMethod = method.getAnnotation(RequestMethod.class);
                    if (methodPath != null && StringUtils.isNotBlank(methodPath.value())
                            && requestMethod != null) {

                        String methodPathValue = methodPath.value();
                        String path = controllerPathValue + "/" + methodPathValue;
                        path = path.replace("//", "/");
                        if (methodMap.containsKey(path)) {
                            throw new RuntimeException("path is exists :" + path);
                        }
                        HttpMethod[] httpMethods = requestMethod.value();
                        methodMap.put(path, new RequestMethodInfo(path, httpMethods, method, controller));
                    }
                }
            }
        });
    }
}

package org.example.web.mvc.controller;

import java.lang.reflect.Method;

public class RequestMehodInfo {

    public RequestMehodInfo(String requestPath, Method requestMethod, Controller controller) {
        this.requestPath = requestPath;
        this.requestMethod = requestMethod;
        this.controller = controller;
    }

    /*
        请求路径
         */
    private String requestPath;

    /*
    控制器处理请求方法
     */
    private Method requestMethod;

    /*
    控制器实例
     */
    private Controller controller;


    public String getRequestPath() {
        return requestPath;
    }

    public Method getRequestMethod() {
        return requestMethod;
    }

    public Controller getController() {
        return controller;
    }
}

package org.example.web.mvc.controller;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

public class RequestMethodInfo {

    public RequestMethodInfo(String requestPath, HttpMethod[] httpMethods, Method requestMethod, Controller controller) {
        this.requestPath = requestPath;
        this.httpMethods = httpMethods;
        this.requestMethod = requestMethod;
        this.controller = controller;
    }

    /*
        请求路径
         */
    private String requestPath;


    private HttpMethod[] httpMethods;

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

    public HttpMethod[] getHttpMethods() {
        return httpMethods;
    }

    /**
     * 校验是否支持该http方法类型
     *
     * @param httpMethod
     * @return
     */
    public boolean isSupportHttpMethod(String httpMethod) {
        if (StringUtils.isBlank (httpMethod)) {
            return false;
        }
        for (HttpMethod method : httpMethods) {
            if (method.name ().equalsIgnoreCase (httpMethod.trim ())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "RequestMethodInfo{" +
                "requestPath='" + requestPath + '\'' +
                ", httpMethods=" + Arrays.toString (httpMethods) +
                ", requestMethod=" + requestMethod +
                ", controller=" + controller +
                '}';
    }
}

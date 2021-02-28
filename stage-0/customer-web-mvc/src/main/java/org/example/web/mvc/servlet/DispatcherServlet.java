package org.example.web.mvc.servlet;

import org.example.web.mvc.controller.RequestMehodInfo;
import org.example.web.mvc.init.InitializationHandler;
import org.example.web.mvc.init.SpiInitializationHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DispatcherServlet extends HttpServlet {

    private final Map<String, Object> controllerMap = new ConcurrentHashMap<>();
    private final Map<String, RequestMehodInfo> methodMap = new ConcurrentHashMap<>();

    @Override
    public void init() throws ServletException {
        initHandleMethods();
    }

    private void initHandleMethods() {
        //清空缓存
        controllerMap.clear();
        methodMap.clear();
        synchronized (this) {
            //初始化controller方法
            //spi加载 or 扫描
            InitializationHandler initializationHandler = new SpiInitializationHandler(this);
            initializationHandler.init();
        }

    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  请求相关处理

    }


    public Map<String, RequestMehodInfo> getMethodMap() {
        return methodMap;
    }

    public Map<String, Object> getControllerMap() {
        return controllerMap;
    }
}

package org.example.web.mvc.servlet;

import org.apache.commons.lang.ObjectUtils;
import org.example.web.mvc.controller.Controller;
import org.example.web.mvc.controller.PageController;
import org.example.web.mvc.controller.RequestMethodInfo;
import org.example.web.mvc.init.CommonInitializationHandler;
import org.example.web.mvc.init.InitializationHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

//@WebServlet(loadOnStartup = 1, urlPatterns = "/*")
public class DispatcherServlet extends HttpServlet {

    private final Map<String, Object> controllerMap = new ConcurrentHashMap<> ();
    private final Map<String, RequestMethodInfo> methodMap = new ConcurrentHashMap<> ();
    private ServletConfig servletConfig;
    private static final Logger LOGGER = Logger.getLogger (DispatcherServlet.class.getName ());


    @Override
    public void init(ServletConfig config) {
        this.servletConfig = config;
        initHandleMethods ();
//        try {
//            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer ();
//            ObjectName name = new ObjectName ("org.example.web.mvc.jmx:type=DispatchServletManager");
//            DispatcherServletManager mBean = new DispatcherServletManager (this);
//            mbs.registerMBean (mBean, name);
//        } catch (Exception e) {
//            throw new RuntimeException (e);
//        }
    }


    /**
     * 初始化控制器并缓存到集合中
     */
    private void initHandleMethods() {
        //清空缓存
        controllerMap.clear ();
        methodMap.clear ();
        //初始化controller方法
        InitializationHandler initializationHandler = new CommonInitializationHandler (this.servletConfig, this);
        initializationHandler.init ();
        methodMap.forEach ((k, v) -> {
            LOGGER.info ("key:"+k);
            LOGGER.info ("value:"+v.toString ());
            LOGGER.info ("---------------------------------");
        });
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  请求相关处理
        // requestURI = /a/hello/world
        String requestURI = request.getRequestURI ();
        // contextPath  = /a or "/" or ""
        String servletContextPath = request.getContextPath ();
        String prefixPath = servletContextPath;
        LOGGER.info ("requestURI : " + requestURI);
        LOGGER.info ("servletContextPath : " + servletContextPath);
        LOGGER.info ("prefixPath : " + prefixPath);
        try {
            RequestMethodInfo requestMethodInfo = methodMap.get (requestURI);
            if (ObjectUtils.notEqual (requestMethodInfo, null)) {
                Controller controller = requestMethodInfo.getController ();
                Method requestMethod = requestMethodInfo.getRequestMethod ();
                String httpMethod = request.getMethod ();
                if (!requestMethodInfo.isSupportHttpMethod (httpMethod)) {
                    //HTTP 方法不支持
                    LOGGER.info ("不支持该HTTP方法");
                    response.setStatus (HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    return;
                }

                LOGGER.info ("调用controller方法");
                Object result = requestMethod.invoke (controller, request, response);
                if (controller instanceof PageController) {
                    if (!requestMethod.getReturnType ().isAssignableFrom (String.class)) {
                        throw new RuntimeException ("can not forward the page");
                    }
                    String viewPath = String.valueOf (result);
                    ServletContext servletContext = request.getServletContext ();
                    if (!viewPath.startsWith ("/")) {
                        viewPath = "/" + viewPath;
                    }
                    RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher (viewPath);
                    requestDispatcher.forward (request, response);
                } else {
                    // TODO: 2021/3/2
                    LOGGER.info ("not finish part");
                }

            }
        } catch (Exception e) {
            LOGGER.log (Level.SEVERE, e.getMessage (), e);
        }
    }


    public Map<String, RequestMethodInfo> getMethodMap() {
        return methodMap;
    }

    public Map<String, Object> getControllerMap() {
        return controllerMap;
    }


    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }
}

//package org.example.user.web.context;
//
//import org.example.user.web.destroy.DestroyedComponent;
//import org.example.user.web.init.DisposableComponent;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import javax.annotation.Resource;
//import javax.naming.*;
//import javax.servlet.ServletContext;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.logging.Logger;
//
///**
// * 组件上下文，用于获取在servlet中注册的组件
// *
// * @author kg yam
// * @date 2021-03-05 16:33
// * @since
// */
//public class ComponentContext {
//
//    private static final Logger logger = Logger.getLogger (ComponentContext.class.getName ());
//
//    private Context context;
//    public static final String COMPONENT_CONTEXT = ComponentContext.class.getName ();
//    private static final String ENV_NAME = "java:comp/env";
//    private static ServletContext servletContext;
//
//    private ClassLoader classLoader;
//
//    private Map<String, Object> componentContainer = new ConcurrentHashMap<> (128);
//
//    private boolean isFinish = false;
//
//    public ComponentContext(ServletContext servletContext) {
//        init (servletContext);
//    }
//
//    private void init(ServletContext servletContext) {
//
//        initContext (servletContext);
//        instantiateComponent ();
//        initializeComponent ();
//        isFinish = true;
//    }
//
//
//    /**
//     * 初始化上下文
//     */
//    private void initContext(ServletContext servletContext) {
//        try {
//            logger.info ("initContext...");
//            Context context = (Context) new InitialContext ().lookup (ENV_NAME);
//            this.context = context;
//            servletContext.setAttribute (COMPONENT_CONTEXT, this);
//            this.classLoader = servletContext.getClassLoader ();
//            this.servletContext = servletContext;
//        } catch (NamingException e) {
//            throw new RuntimeException (e);
//        }
//    }
//
//    /**
//     * 实例化组件
//     */
//    private void instantiateComponent() {
//        if (context == null) {
//            throw new RuntimeException ("javax.naming.Context is null");
//        }
//        List<String> componentNameList = listAllComponentName ();
//        componentNameList.forEach (componentName -> {
//            try {
//                Object component = context.lookup (componentName);
//                componentContainer.put (componentName, component);
//            } catch (NamingException e) {
//                e.printStackTrace ();
//            }
//        });
//    }
//
//
//    private <C> C lookupComponent(String name) {
//        try {
//            return (C) context.lookup (name);
//        } catch (NamingException e) {
//            throw new RuntimeException (e);
//        }
//
//    }
//
//
//    private List<String> listAllComponentName() {
//        return listComponentName ("/");
//    }
//
//    private List<String> listComponentName(String conetxtName) {
//        try {
//            NamingEnumeration<NameClassPair> nameClassPairNamingEnumeration = context.list (conetxtName);
//            if (nameClassPairNamingEnumeration == null) {
//                return Collections.EMPTY_LIST;
//            }
//
//            List<String> componentNameList = new LinkedList<> ();
//            while (nameClassPairNamingEnumeration.hasMoreElements ()) {
//                NameClassPair nameClassPair = nameClassPairNamingEnumeration.next ();
//                Class clazz = classLoader.loadClass (nameClassPair.getClassName ());
//                /*
//                如果遍历的类型也是一个javax.naming.Context,那么递归遍历这个Context下的元素
//                 */
//                if (Context.class.isAssignableFrom (clazz)) {
//                    componentNameList.addAll (listComponentName (nameClassPair.getName ()));
//                } else {
//                    String name = conetxtName.startsWith ("/") ? nameClassPair.getName () : conetxtName + "/" + nameClassPair.getName ();
//                    componentNameList.add (name);
//                }
//            }
//            return componentNameList;
//        } catch (Exception e) {
//            throw new RuntimeException (e);
//        }
//    }
//
//    /**
//     * 初始化组件：赋值、注入、初始化方法回调
//     */
//    private void initializeComponent() {
//        componentContainer.forEach ((componentName, component) -> {
//            injectComponent (component);
//            invokeDisposable (component);
//            initializeCallback (component);
//        });
//    }
//
//    private void invokeDisposable(Object component) {
//        if (component instanceof DisposableComponent) {
//            ((DisposableComponent) component).init ();
//        }
//    }
//
//    /**
//     * 回调PostConstruct标记的初始化方法
//     *
//     * @param component
//     */
//    private void initializeCallback(Object component) {
//        Method[] methods = component.getClass ().getDeclaredMethods ();
//        Arrays.stream (methods).forEach (m -> {
//            if (m.isAnnotationPresent (PostConstruct.class)) {
//                try {
//                    m.setAccessible (true);
//                    m.invoke (component);
//                } catch (Exception e) {
//                    throw new RuntimeException (e);
//                }
//            }
//        });
//    }
//
//
//    /**
//     * 注入组件
//     *
//     * @param component
//     */
//    private void injectComponent(Object component) {
//        Field[] fields = component.getClass ().getDeclaredFields ();
//        for (Field field : fields) {
//            if (field.isAnnotationPresent (Resource.class)) {
//                field.setAccessible (true);
//                Resource resource = field.getAnnotation (Resource.class);
//                String resourceName = resource.name ();
//                Object o = lookupComponent (resourceName);
//                if (o != null) {
//                    try {
//                        field.set (component, o);
//                    } catch (IllegalAccessException e) {
//                        throw new RuntimeException (e);
//                    }
//                }
//            }
//        }
//    }
//
//
//    /**
//     * 返回当前servlet环境中组件上下文对象
//     *
//     * @return
//     */
//    public static ComponentContext getInstance() {
//        if (servletContext == null) {
//            throw new RuntimeException ("servletContext is null");
//        }
//        return (ComponentContext) servletContext.getAttribute (COMPONENT_CONTEXT);
//    }
//
//    /**
//     * 依赖查找
//     *
//     * @param name
//     * @param <C>
//     * @return
//     */
//    public <C> C getComponent(String name) {
//        return lookupComponent (name);
//    }
//
//    public void destroy() throws RuntimeException {
//        doDestroy ();
//    }
//
//
//    private void doDestroy() {
//        componentContainer.forEach ((componentName, component) -> {
//            if (component instanceof DestroyedComponent) {
//                ((DestroyedComponent) component).destroy ();
//            }
//
//
//            Arrays.stream (component.getClass ().getDeclaredMethods ()).forEach (m -> {
//                if (m.isAnnotationPresent (PreDestroy.class)) {
//                    try {
//                        m.invoke (component);
//                    } catch (Exception e) {
//                        throw new RuntimeException (e);
//                    }
//                }
//            });
//        });
//
//    }
//
//}

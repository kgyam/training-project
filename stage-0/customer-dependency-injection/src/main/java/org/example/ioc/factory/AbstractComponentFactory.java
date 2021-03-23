package org.example.ioc.factory;

import org.apache.commons.lang.StringUtils;
import org.example.ioc.aware.ComponentFactoryAware;
import org.example.ioc.destroy.DestroyedComponent;
import org.example.ioc.init.DisposableComponent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kg yam
 * @date 2021-03-10 15:39
 * @since
 */
public abstract class AbstractComponentFactory implements ComponentFactory {

    public Map<String, Object> componentContainer = new ConcurrentHashMap<>(128);


    public AbstractComponentFactory() {
        init();
    }

    final void init() {
        componentContainer.clear();
        initEnv();
        instantiateComponent();
        initializeComponent();
    }

    abstract void initEnv();

    abstract void instantiateComponent();


    @Override
    public <T> List<T> getComponents(Class<T> componentType) {
        if (componentType == null) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>();
        componentContainer.forEach((componentName, component) -> {
            if (componentType.isAssignableFrom(component.getClass())) {
                result.add((T) component);
            }
        });
        return result;
    }

    @Override
    public <C> C getComponent(String findComponentName, Class<C> componentType) {
        if (StringUtils.isBlank(findComponentName) || componentType == null) {
            return null;
        }
        if (componentContainer.containsKey(findComponentName)) {
            Object component = componentContainer.get(findComponentName);
            if (component.getClass().isAssignableFrom(componentType)) {
                return (C) component;
            }
        }
        return null;
    }

    @Override
    public <C> C getComponent(String findComponentName) {
        return lookupComponent(findComponentName);
    }


    public void destroy() throws RuntimeException {
        doDestroy();
    }


    /**
     * 初始化组件：赋值、注入、初始化方法回调
     */
    private void initializeComponent() {
        componentContainer.forEach((componentName, component) -> {
            invokeAware(component);
            component = injectComponent(component);
            component = invokeDisposable(component);
            component = initializeCallback(component);
            componentContainer.put(componentName, component);
        });
    }

    /**
     * @param component
     * @param <C>
     */
    private <C> void invokeAware(C component) {
        if (component instanceof ComponentFactoryAware) {
            ((ComponentFactoryAware) component).setComponentFactory(this);
        }
    }

    /**
     * 初始化方法回调
     *
     * @param component
     * @return
     */
    private Object invokeDisposable(Object component) {
        if (component instanceof DisposableComponent) {
            ((DisposableComponent) component).init();
        }
        return component;
    }

    /**
     * 回调PostConstruct标记的初始化方法
     *
     * @param component
     */
    private Object initializeCallback(Object component) {
        Method[] methods = component.getClass().getDeclaredMethods();
        Arrays.stream(methods).forEach(m -> {
            if (m.isAnnotationPresent(PostConstruct.class)) {
                try {
                    m.setAccessible(true);
                    m.invoke(component);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return component;
    }

    private <C> C lookupComponent(String findComponentName) {
        if (StringUtils.isBlank(findComponentName)) {
            return null;
        }
        return (C) componentContainer.get(findComponentName);
    }

    /**
     * 注入组件
     *
     * @param component
     */
    private Object injectComponent(Object component) {
        Field[] fields = component.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Resource.class)) {
                field.setAccessible(true);
                Resource resource = field.getAnnotation(Resource.class);
                String resourceName = resource.name();
                Object o = lookupComponent(resourceName);
                if (o != null) {
                    try {
                        field.set(component, o);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return component;
    }


    private void doDestroy() {
        componentContainer.forEach((componentName, component) -> {
            if (component instanceof DestroyedComponent) {
                ((DestroyedComponent) component).destroy();
            }


            Arrays.stream(component.getClass().getDeclaredMethods()).forEach(m -> {
                if (m.isAnnotationPresent(PreDestroy.class)) {
                    try {
                        m.invoke(component);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });

    }
}



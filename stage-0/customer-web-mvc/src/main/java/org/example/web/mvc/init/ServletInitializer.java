package org.example.web.mvc.init;

import org.example.web.mvc.listener.ComponentFactoryInitializationListener;
import org.example.web.mvc.listener.ConfigInitializationListener;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * @author kg yam
 * @date 2021-03-23 11:11
 * @since
 */
public class ServletInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) {
        //添加listener
        servletContext.addListener (ConfigInitializationListener.class);
        servletContext.addListener (ComponentFactoryInitializationListener.class);
    }
}

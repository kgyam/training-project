package org.example.web.mvc.listener;

import org.example.ioc.factory.ComponentFactory;
import org.example.ioc.factory.JndiComponentFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author kg yam
 * @date 2021-03-23 11:12
 * @since
 */
@WebListener
public class ComponentFactoryInitializationListener implements ServletContextListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext ();
        this.servletContext = servletContext;
        ComponentFactory factory = new JndiComponentFactory ();
        servletContext.setAttribute (ComponentFactory.COMPONENT_FACTORY, factory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ComponentFactory factory = (ComponentFactory) servletContext.getAttribute (ComponentFactory.COMPONENT_FACTORY);
        servletContext.removeAttribute (ComponentFactory.COMPONENT_FACTORY);
        factory = null;
    }
}

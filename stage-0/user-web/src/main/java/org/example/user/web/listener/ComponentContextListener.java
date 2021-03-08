package org.example.user.web.listener;

import org.example.user.web.context.ComponentContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static org.example.user.web.context.ComponentContext.COMPONENT_CONTEXT;

/**
 * @author kg yam
 * @date 2021-03-05 16:22
 * @since
 */
public class ComponentContextListener implements ServletContextListener {
    ComponentContext componentContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        ComponentContext componentContext = new ComponentContext(servletContext);
        this.componentContext = componentContext;

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        componentContext.destroy();
    }
}

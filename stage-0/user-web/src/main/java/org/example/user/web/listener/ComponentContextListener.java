package org.example.user.web.listener;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * @author kg yam
 * @date 2021-03-05 16:22
 * @since
 */
public class ComponentContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}

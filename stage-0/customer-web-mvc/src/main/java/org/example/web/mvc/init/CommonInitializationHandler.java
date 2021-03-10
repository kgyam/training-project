package org.example.web.mvc.init;

import org.example.ioc.factory.ComponentFactory;
import org.example.web.mvc.controller.Controller;
import org.example.web.mvc.servlet.DispatcherServlet;

import javax.servlet.ServletConfig;
import java.util.List;

/**
 * @author kg yam
 * @date 2021-03-10 16:11
 * @since
 */
public class CommonInitializationHandler extends AbstractInitializationHandler {


    public CommonInitializationHandler(ServletConfig config, DispatcherServlet dispatcherServlet) {
        this.config = config;
        this.dispatcherServlet = dispatcherServlet;
    }

    @Override
    public void init() {
        ComponentFactory factory = (ComponentFactory) config.getServletContext().getAttribute(ComponentFactory.COMPONENT_FACTORY);
        if (factory != null) {
            List<Controller> result = factory.getComponents(Controller.class);
            initController(result.iterator());
        }
    }
}

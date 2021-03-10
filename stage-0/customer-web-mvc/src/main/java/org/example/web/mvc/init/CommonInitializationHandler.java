package org.example.web.mvc.init;

import com.sun.jndi.toolkit.ctx.ComponentContext;
import org.example.ioc.factory.ComponentFactory;
import org.example.web.mvc.controller.Controller;
import org.example.web.mvc.servlet.DispatcherServlet;

import java.util.List;

/**
 * @author kg yam
 * @date 2021-03-10 16:11
 * @since
 */
public class CommonInitializationHandler extends AbstractInitializationHandler {

    private DispatcherServlet dispatcherServlet;

    public CommonInitializationHandler(DispatcherServlet dispatcherServlet) {
        this.dispatcherServlet = dispatcherServlet;
    }

    @Override
    public void init() {
        ComponentFactory factory = (ComponentFactory) dispatcherServlet.getServletConfig ().getServletContext ().getAttribute (ComponentFactory.COMPONENT_CONTEXT);
        if (factory != null) {
            List<Controller> result = factory.getComponents (Controller.class);
            initController (result.iterator ());
        }
    }
}

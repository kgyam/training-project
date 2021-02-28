package org.example.web.mvc.init;

import org.apache.commons.lang.ObjectUtils;
import org.example.web.mvc.controller.Controller;
import org.example.web.mvc.servlet.DispatcherServlet;

import java.util.ServiceLoader;

public class SpiInitializationHandler extends AbstractInitializationHandler {


    public SpiInitializationHandler(DispatcherServlet dispatcherServlet) {
        this.dispatcherServlet = dispatcherServlet;
    }

    @Override
    public void init() {
        if (ObjectUtils.equals(dispatcherServlet, null)) {
            return;
        }
        initController(ServiceLoader.load(Controller.class).iterator());
    }
}

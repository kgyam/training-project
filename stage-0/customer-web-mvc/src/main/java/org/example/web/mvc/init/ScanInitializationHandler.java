package org.example.web.mvc.init;

import org.example.web.mvc.servlet.DispatcherServlet;

/**
 * @author kg yam
 * @date 2021-03-01 14:56
 * @since
 */
public class ScanInitializationHandler extends AbstractInitializationHandler {

    public ScanInitializationHandler(DispatcherServlet dispatcherServlet) {
        this.dispatcherServlet = dispatcherServlet;
    }

    @Override
    public void init() {
        // TODO: 2021/3/1
    }
}

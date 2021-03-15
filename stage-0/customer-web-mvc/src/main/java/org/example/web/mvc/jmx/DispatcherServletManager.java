package org.example.web.mvc.jmx;

import org.example.web.mvc.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


/**
 * @author kg yam
 * @date 2021-03-15 16:36
 * @since
 */
public class DispatcherServletManager implements DispatcherServletManagerMBean {
    private final DispatcherServlet dispatcherServlet;

    public DispatcherServletManager(DispatcherServlet dispatcherServlet) {
        this.dispatcherServlet = dispatcherServlet;

    }


    @Override
    public void setAttribute(String key, String value) {
        dispatcherServlet.getServletConfig ().getServletContext ().setAttribute (key, value);
    }

    @Override
    public String getAttribute(String key) {
        return (String) dispatcherServlet.getServletConfig ().getServletContext ().getAttribute (key);
    }


    @Override
    public String getServletInfo() {
        return dispatcherServlet.getServletInfo ();
    }

    @Override
    public void service() {

    }

}

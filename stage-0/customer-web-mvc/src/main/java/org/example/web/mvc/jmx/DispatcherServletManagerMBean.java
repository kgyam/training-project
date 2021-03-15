package org.example.web.mvc.jmx;

/**
 * @author kg yam
 * @date 2021-03-15 16:36
 * @since
 */
public interface DispatcherServletManagerMBean {

    void setAttribute(String key, String value);

    String getServletInfo();

    String getAttribute(String key);

    void service();
}

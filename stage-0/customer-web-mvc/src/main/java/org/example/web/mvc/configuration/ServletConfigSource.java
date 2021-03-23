package org.example.web.mvc.configuration;

import org.example.configuration.source.MapBasedConfigSource;

import javax.servlet.ServletContext;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author kg yam
 * @date 2021-03-23 11:31
 * @since
 */
public class ServletConfigSource extends MapBasedConfigSource {

    private ServletContext servletContext;

    public ServletConfigSource(ServletContext servletContext) {
        super ("ServletConfigSource", 500);
        this.servletContext = servletContext;
    }

    @Override
    public void getConfigData(Map data) throws Throwable {
        Map<String, String> servletAttributeData = new LinkedHashMap<> ();
        Enumeration<String> parameterNames = servletContext.getInitParameterNames ();
        while (parameterNames.hasMoreElements ()) {
            String parameterName = parameterNames.nextElement ();
            String parameterVal = (String) servletContext.getAttribute (parameterName);
            servletAttributeData.putIfAbsent (parameterName, parameterVal);
        }
        data = servletAttributeData;
    }
}

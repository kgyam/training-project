package org.example.web.mvc.listener;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.example.web.mvc.configuration.ServletConfigSource;

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
public class ConfigInitializationListener implements ServletContextListener {
    public static String CONFIG_PROVIDER_RESOLVER;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext ();
        ClassLoader classLoader = getClass ().getClassLoader ();
        ConfigProviderResolver configProviderResolver = ConfigProviderResolver.instance ();
        ConfigBuilder configBuilder = configProviderResolver.getBuilder ();
        Config config = configBuilder.forClassLoader (classLoader).addDefaultSources ().addDiscoveredSources ()
                .withSources (new ServletConfigSource (servletContext))
                .addDiscoveredConverters ().build ();
        configProviderResolver.registerConfig (config, classLoader);
        CONFIG_PROVIDER_RESOLVER = ConfigProviderResolver.class.getName ();
        servletContext.setAttribute (CONFIG_PROVIDER_RESOLVER, configProviderResolver);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Runtime.getRuntime ().addShutdownHook (new Thread (() -> {
            System.out.println ("shutdown");
        }));
    }
}

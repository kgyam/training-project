package org.example.configuration;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

/**
 * @author kg yam
 * @date 2021-03-23 17:57
 * @since
 */
public abstract class AbstractConfigProviderResolver extends ConfigProviderResolver {


    protected ClassLoader resolveClassLoader(ClassLoader classLoader) {
        return classLoader == null ? this.getClass ().getClassLoader () : classLoader;
    }


    protected ConfigBuilder newConfigBuilder(ClassLoader classLoader) {
        return new DefaultConfigBuilder (resolveClassLoader (classLoader));
    }

    protected Config newConfig(ClassLoader classLoader) {
        return newConfigBuilder (classLoader).build ();
    }
}

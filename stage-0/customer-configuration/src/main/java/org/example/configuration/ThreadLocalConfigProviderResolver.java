package org.example.configuration;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;

/**
 * @author kg yam
 * @date 2021-03-23 17:50
 * @since
 */
public class ThreadLocalConfigProviderResolver extends AbstractConfigProviderResolver {

    private static final ThreadLocal<Config> threadLocal = new ThreadLocal<> ();

    @Override
    public Config getConfig() {
        return getConfig (null);
    }

    @Override
    public Config getConfig(ClassLoader classLoader) {
        Config config = threadLocal.get ();
        if (config != null) {
            return config;
        }
        config = newConfig (classLoader);
        threadLocal.set (config);
        return config;
    }

    @Override
    public ConfigBuilder getBuilder() {
        return newConfigBuilder (null);
    }

    @Override
    public void registerConfig(Config config, ClassLoader classLoader) {
        threadLocal.set (config);
    }

    @Override
    public void releaseConfig(Config config) {
        threadLocal.remove ();
    }
}

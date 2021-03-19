package org.example.ioc.configuration;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

/**
 * @author kg yam
 * @date 2021-03-12 15:21
 * @since
 */
public class DefaultConfigProviderResolver extends ConfigProviderResolver {


    @Override
    public Config getConfig() {
        return getConfig (null);
    }

    @Override
    public Config getConfig(ClassLoader loader) {
        return null;
    }

    @Override
    public ConfigBuilder getBuilder() {
        return null;
    }

    @Override
    public void registerConfig(Config config, ClassLoader classLoader) {
        throw new UnsupportedOperationException ("registerConfig not support");
    }

    @Override
    public void releaseConfig(Config config) {
        config = null;
    }

}

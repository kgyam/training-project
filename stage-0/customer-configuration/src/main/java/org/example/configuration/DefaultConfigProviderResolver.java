package org.example.configuration;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author kg yam
 * @date 2021-03-12 15:21
 * @since
 */
public class DefaultConfigProviderResolver extends AbstractConfigProviderResolver {

    private ConcurrentMap<ClassLoader, Config> configsRepository = new ConcurrentHashMap<> ();


    @Override
    public Config getConfig() {
        return getConfig (null);
    }

    @Override
    public Config getConfig(ClassLoader loader) {
        return configsRepository.computeIfAbsent (loader, this::newConfig);
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

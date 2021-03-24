package org.example.configuration;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;

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
        return getConfig (getClass ().getClassLoader ());
    }

    @Override
    public Config getConfig(ClassLoader loader) {
        return configsRepository.computeIfAbsent (loader, this::newConfig);
    }

    @Override
    public ConfigBuilder getBuilder() {
        return newConfigBuilder (null);
    }

    @Override
    public void registerConfig(Config config, ClassLoader classLoader) {
        this.configsRepository.put (classLoader, config);
    }

    @Override
    public void releaseConfig(Config config) {
        config = null;
    }

}

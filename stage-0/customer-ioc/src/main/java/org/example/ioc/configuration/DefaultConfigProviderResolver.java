package org.example.ioc.configuration;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author kg yam
 * @date 2021-03-12 15:21
 * @since
 */
public class DefaultConfigProviderResolver extends ConfigProviderResolver {

    private ClassLoader classLoader;


    @Override
    public Config getConfig() {
        return getConfig(null);
    }

    @Override
    public Config getConfig(ClassLoader loader) {

        if (loader == null) {
            loader = Thread.currentThread().getContextClassLoader();
        }
        this.classLoader = loader;
        /**
         * 扩展SPI机制加载Config,如果没有SPI,返回一个DefaultConfig兜底
         */
        Iterator<Config> configIterator = ServiceLoader.load(Config.class, classLoader).iterator();
        return configIterator.hasNext() ? configIterator.next() : new DefaultConfig();
    }

    @Override
    public ConfigBuilder getBuilder() {
        return null;
    }

    @Override
    public void registerConfig(Config config, ClassLoader classLoader) {
        throw new UnsupportedOperationException("registerConfig not support");
    }

    @Override
    public void releaseConfig(Config config) {
        config = null;
    }
}

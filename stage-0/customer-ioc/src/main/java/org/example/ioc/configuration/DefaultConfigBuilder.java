package org.example.ioc.configuration;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;

/**
 * @author kg yam
 * @date 2021-03-19 15:28
 * @since
 */
public class DefaultConfigBuilder implements ConfigBuilder {
    @Override
    public ConfigBuilder addDefaultSources() {
        return null;
    }

    @Override
    public ConfigBuilder addDiscoveredSources() {
        return null;
    }

    @Override
    public ConfigBuilder addDiscoveredConverters() {
        return null;
    }

    @Override
    public ConfigBuilder forClassLoader(ClassLoader classLoader) {
        return null;
    }

    @Override
    public ConfigBuilder withSources(ConfigSource... configSources) {
        return null;
    }

    @Override
    public ConfigBuilder withConverters(Converter<?>... converters) {
        return null;
    }

    @Override
    public <T> ConfigBuilder withConverter(Class<T> aClass, int i, Converter<T> converter) {
        return null;
    }

    @Override
    public Config build() {
        return new DefaultConfig ();
    }
}

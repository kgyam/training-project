package org.example.configuration;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;
import org.example.configuration.converter.Converters;
import org.example.configuration.source.ConfigSources;

/**
 * @author kg yam
 * @date 2021-03-19 15:28
 * @since
 */
public class DefaultConfigBuilder implements ConfigBuilder {

    private final ConfigSources configSources;

    private final Converters converters;


    public DefaultConfigBuilder(ClassLoader classLoader) {
        this.configSources = new ConfigSources (classLoader);
        this.converters = new Converters (classLoader);
    }


    @Override
    public ConfigBuilder addDefaultSources() {

        configSources.addDefaultSources ();
        return this;
    }

    @Override
    public ConfigBuilder addDiscoveredSources() {
        configSources.addDiscoveredSources ();
        return this;
    }

    @Override
    public ConfigBuilder addDiscoveredConverters() {
        converters.addDiscoveredConverters ();
        return this;
    }

    @Override
    public ConfigBuilder forClassLoader(ClassLoader classLoader) {
        configSources.setClassLoader (classLoader);
        converters.setClassLoader (classLoader);
        return this;
    }

    @Override
    public ConfigBuilder withSources(ConfigSource... configSources) {
        this.configSources.addConfigSources (configSources);
        return this;
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
        Config config = new DefaultConfig (configSources, converters);
        return config;
    }
}

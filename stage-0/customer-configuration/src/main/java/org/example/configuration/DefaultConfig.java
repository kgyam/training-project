package org.example.configuration;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;
import org.example.configuration.converter.Converters;
import org.example.configuration.source.ConfigSources;

import java.util.*;

import static java.util.stream.StreamSupport.stream;

/**
 * ConfigSourceFacade，以集合方式存放多个层次的ConfigSource，对外部透明
 * <p>
 * 配置获取方式：优化获取外部化配置，以内部配置(hard code)作为兜底策略
 *
 * @author kg yam
 * @date 2021-03-12 15:28
 * @since
 */
public class DefaultConfig implements Config {

    private final ConfigSources configSources;

    private final Converters converters;

    DefaultConfig(ConfigSources configSources, Converters converters) {
        this.configSources = configSources;
        this.converters = converters;
    }


    @Override
    public <T> T getValue(String propertyName, Class<T> propertyType) {

        String propertyValue = getPropertyValue (propertyName);
        // String 转换成目标类型
        Converter<T> converter = doGetConverter (propertyType);
        return converter == null ? null : converter.convert (propertyValue);
    }


    @Override
    public ConfigValue getConfigValue(String s) {
        return null;
    }

    @Override
    public <T> Optional<T> getOptionalValue(String propertyName, Class<T> propertyType) {
        T value = getValue (propertyName, propertyType);
        return Optional.ofNullable (value);
    }

    @Override
    public Iterable<String> getPropertyNames() {
        return stream (configSources.spliterator (), false)
                .map (ConfigSource::getPropertyNames)
                .collect (LinkedHashSet::new, Set::addAll, Set::addAll);
    }

    @Override
    public Iterable<ConfigSource> getConfigSources() {
        return configSources;
    }

    @Override
    public <T> Optional<Converter<T>> getConverter(Class<T> forType) {
        Converter converter = doGetConverter (forType);
        return converter == null ? Optional.empty () : Optional.of (converter);
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }


    protected <T> Converter<T> doGetConverter(Class<T> forType) {
        List<Converter> converters = this.converters.getConverters (forType);
        return converters.isEmpty () ? null : converters.get (0);
    }

    private String getPropertyValue(String propertyName) {
        String propertyValue = null;
        for (ConfigSource configSource : configSources) {
            propertyValue = configSource.getValue (propertyName);
            if (propertyValue != null) {
                break;
            }
        }
        return propertyValue;
    }
}

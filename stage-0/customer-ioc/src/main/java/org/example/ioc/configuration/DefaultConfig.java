package org.example.ioc.configuration;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;

import java.util.*;

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

    private final List<ConfigSource> configSourceList = new LinkedList<> ();

    public DefaultConfig() {
    }


    @Override
    public <T> T getValue(String s, Class<T> aClass) {
        return null;
    }

    @Override
    public ConfigValue getConfigValue(String s) {
        return null;
    }

    @Override
    public <T> Optional<T> getOptionalValue(String s, Class<T> aClass) {
        return Optional.empty ();
    }

    @Override
    public Iterable<String> getPropertyNames() {
        return null;
    }

    @Override
    public Iterable<ConfigSource> getConfigSources() {
        return null;
    }

    @Override
    public <T> Optional<Converter<T>> getConverter(Class<T> aClass) {
        return Optional.empty ();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }
}

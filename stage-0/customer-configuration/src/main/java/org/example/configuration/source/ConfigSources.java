package org.example.configuration.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.*;
import java.util.stream.Stream;


/**
 * addConfigSource方法定义为public方法是为了能够让客户端调用，动态添加configSource实例
 * 添加的方法通过DynamicConfigSource
 *
 * @author kg yam
 * @date 2021-03-19 16:53
 * @since
 */
public class ConfigSources implements Iterable<ConfigSource> {

    private boolean addedDefaultConfigSources;

    private boolean addedDiscoveredConfigSources;

    private final List<ConfigSource> configSources = new LinkedList<> ();

    private ClassLoader classLoader;

    public ConfigSources(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }


    /**
     * 添加默认的ConfigSource
     */
    public void addDefaultSources() {
        if (addedDefaultConfigSources) {
            return;
        }
        addConfigSources (JavaSystemConfigSource.class, SystemConfigSource.class
                , MetaDataPropertyConfigSource.class);
        addedDefaultConfigSources = true;
    }


    /**
     * 添加自定义(SPI)的ConfigSources
     */
    public void addDiscoveredSources() {
        if (addedDiscoveredConfigSources) {
            return;
        }
        addConfigSources (ServiceLoader.load (ConfigSource.class, this.classLoader));
        addedDiscoveredConfigSources = true;
    }


    /**
     * 会根据传入的class类型实例化对象，调用overload方法
     *
     * @param configSourceClass
     */
    public void addConfigSources(Class<? extends ConfigSource>... configSourceClass) {
        addConfigSources (Stream.of (configSourceClass).map (this::newInstance).toArray (ConfigSource[]::new));
    }

    /**
     * 将入参的ConfigSource数组转换成一个迭代器对象Iterable<ConfigSource>
     *
     * @param configSourceArr
     */
    public void addConfigSources(ConfigSource... configSourceArr) {
        addConfigSources (Arrays.asList (configSourceArr));
    }

    /**
     * @param configSourceIterable
     */
    public void addConfigSources(Iterable<ConfigSource> configSourceIterable) {
        configSourceIterable.forEach (this.configSources::add);
        Collections.sort (this.configSources, ConfigSourceCompartor.INSTANCE);
    }


    public ConfigSource newInstance(Class<? extends ConfigSource> clazz) {
        ConfigSource instance = null;
        try {
            instance = clazz.newInstance ();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException (e);
        }
        return instance;
    }

    @Override
    public Iterator<ConfigSource> iterator() {
        return this.configSources.iterator ();
    }
}

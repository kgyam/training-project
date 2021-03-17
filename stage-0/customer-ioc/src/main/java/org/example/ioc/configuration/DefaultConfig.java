package org.example.ioc.configuration;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;
import org.example.ioc.configuration.configSource.JavaSystemConfigSource;
import org.example.ioc.configuration.configSource.SystemConfigSource;

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

    private static Comparator<ConfigSource> configSourceComparator = ((o1, o2) ->
            Integer.compare (o2.getOrdinal (), o1.getOrdinal ()));

    public DefaultConfig() {
        init ();
    }


    private void init() {
         /*
        加载spi下定义的所有ConfigSource,然后进行排序
        默认操作系统config和java启动参数config
         */
        ServiceLoader<ConfigSource> serviceLoader = ServiceLoader.load (ConfigSource.class, getClass ().getClassLoader ());
        configSourceList.add (new SystemConfigSource ());
        configSourceList.add (new JavaSystemConfigSource ());
        serviceLoader.forEach (configSourceList::add);
        configSourceList.sort (configSourceComparator);
    }

    @Override
    public <T> T getValue(String propertyName, Class<T> propertyType) {
        return (T) getPropertyValue (propertyName);
    }

    @Override
    public ConfigValue getConfigValue(String propertyName) {
        return null;
    }


    @Override
    public <T> Optional<T> getOptionalValue(String s, Class<T> aClass) {
        return Optional.empty ();
    }

    @Override
    public Iterable<String> getPropertyNames() {
        for (ConfigSource configSource : configSourceList) {
            Set<String> propertyNames = configSource.getPropertyNames ();
            if (!propertyNames.isEmpty ()) {
                return Collections.unmodifiableSet (propertyNames);
            }
        }
        return Collections.emptySet ();
    }


    @Override
    public Iterable<ConfigSource> getConfigSources() {
        return Collections.unmodifiableList (this.configSourceList);
    }

    @Override
    public <T> Optional<Converter<T>> getConverter(Class<T> aClass) {

        return Optional.empty ();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        throw new UnsupportedOperationException ("not support unwrap");
    }


    protected String getPropertyValue(String propertyName) {
        String propertyValue = null;
        for (ConfigSource configSource : configSourceList) {

            if ((propertyValue = configSource.getValue (propertyName)) != null) {
                break;
            }
        }
        return propertyValue;
    }
}

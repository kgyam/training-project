package org.example.configuration.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author kg yam
 * @date 2021-03-19 15:34
 * @since
 */
public abstract class MapBasedConfigSource implements ConfigSource {

    private final Map<String, String> properties;
    private final String name;
    private final int ordinal;

    public MapBasedConfigSource(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
        this.properties = getProperties ();
    }

    @Override
    public Set<String> getPropertyNames() {
        return properties.keySet ();
    }

    @Override
    public String getValue(String propertyName) {
        return properties.get (propertyName);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public final Map<String, String> getProperties() {
        Map<String, String> data = new HashMap<> ();
        try {
            getConfigData (data);
        } catch (Throwable throwable) {
            throw new IllegalStateException ("准备配置数据发生错误", throwable);
        }
        return Collections.unmodifiableMap (data);
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }


    public abstract void getConfigData(Map data) throws Throwable;
}

package org.example.ioc.configuration.configSource;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

/**
 * @author kg yam
 * @date 2021-03-15 10:04
 * @since
 */
public class MetaDataPropertyConfigSource implements ConfigSource {

    private final int ordinal = 3;
    private final String configSourceName = "MetaDataPropertyConfigSource";
    private static final String DEFAULT_PATH = "/META-INF/customer-application.properties";
    private final Properties properties = new Properties ();
    private final Set<String> propertyNames = new LinkedHashSet<> ();

    public MetaDataPropertyConfigSource() {
        init ();
    }

    private void init() {
        try {
            InputStream inputStream = this.getClass ().getResourceAsStream (DEFAULT_PATH);
            if (inputStream == null) {
                return;
            }
            properties.load (inputStream);
            for (Object key : properties.keySet ()) {
                propertyNames.add (String.valueOf (key));
            }
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
    }

    @Override
    public Set<String> getPropertyNames() {
        return propertyNames;
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public String getValue(String propertyName) {
        return properties.getProperty (propertyName);
    }

    @Override
    public String getName() {
        return configSourceName;
    }
}

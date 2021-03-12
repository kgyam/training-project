package org.example.ioc.configuration.configSource;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author kg yam
 * @date 2021-03-12 16:39
 * @since
 */
public class JavaSystemConfigSource implements ConfigSource {

    private final int ordinal = 2;
    private final Map<String, String> properties;
    private final String configSourceName="JavaSystemConfigSource";


    public JavaSystemConfigSource() {
        this.properties = new HashMap<> ();
        init ();
    }


    private void init() {
        for (String propertyName : System.getProperties ().stringPropertyNames ()) {
            this.properties.put (propertyName, System.getProperties ().getProperty (propertyName));
        }
    }



    @Override
    public Set<String> getPropertyNames() {
        return properties.keySet ();
    }

    @Override
    public int getOrdinal() {
        return this.ordinal;
    }

    @Override
    public String getValue(String propertyName) {
        return properties.get (propertyName);
    }

    @Override
    public String getName() {
        return configSourceName;
    }
}

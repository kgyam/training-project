package org.example.configuration.source;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author kg yam
 * @date 2021-03-15 10:04
 * @since
 */
public class MetaDataPropertyConfigSource extends MapBasedConfigSource {

    private static final String DEFAULT_PATH = "/META-INF/customer-application.properties";

    public MetaDataPropertyConfigSource() {
        super ("MetaDataPropertyConfigSource", 3);
    }

    @Override
    public Map prepareConfigData() throws Throwable {
        Properties properties = new Properties ();
        InputStream inputStream = this.getClass ().getResourceAsStream (DEFAULT_PATH);
        if (inputStream == null) {
            return null;
        }
        properties.load (inputStream);
        return properties;
    }
}

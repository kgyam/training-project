package org.example.ioc.configuration.source;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author kg yam
 * @date 2021-03-15 10:04
 * @since
 */
public class MetaDataPropertyConfigSource extends MapBasedConfigSource {

    private static final String DEFAULT_PATH = "classpath*:/META-INF/customer-application.properties";

    public MetaDataPropertyConfigSource() {
        super ("MetaDataPropertyConfigSource", 3);
    }

    @Override
    public void getConfigData(Map data) throws Throwable {
        Properties properties = new Properties ();
        InputStream inputStream = this.getClass ().getResourceAsStream (DEFAULT_PATH);
        if (inputStream == null) {
            return;
        }
        properties.load (inputStream);
        data = properties;
    }
}

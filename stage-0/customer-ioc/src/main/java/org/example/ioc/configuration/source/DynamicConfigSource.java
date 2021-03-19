package org.example.ioc.configuration.source;

import java.util.Map;

/**
 * @author kg yam
 * @date 2021-03-19 16:49
 * @since
 */
public class DynamicConfigSource extends MapBasedConfigSource {

    private Map configData;

    public DynamicConfigSource() {
        super ("DynamicConfigSource", 500);
    }

    @Override
    public void getConfigData(Map data) throws Throwable {
        this.configData = data;
    }
}

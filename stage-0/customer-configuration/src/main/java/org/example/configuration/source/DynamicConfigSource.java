package org.example.configuration.source;

import java.util.Map;

/**
 * @author kg yam
 * @date 2021-03-19 16:49
 * @since
 */
public class DynamicConfigSource extends MapBasedConfigSource {

    private Map configData;

    public DynamicConfigSource(Map configData) {
        super ("DynamicConfigSource", 500);
        this.configData = configData;
    }

    @Override
    public Map prepareConfigData() throws Throwable {
        return this.configData;
    }
}

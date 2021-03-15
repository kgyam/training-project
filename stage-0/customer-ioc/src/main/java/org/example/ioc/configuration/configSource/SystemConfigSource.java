package org.example.ioc.configuration.configSource;

import org.apache.commons.lang.StringUtils;
import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Map;
import java.util.Set;

/**
 * @author kg yam
 * @date 2021-03-12 17:45
 * @since
 */
public class SystemConfigSource implements ConfigSource {

    private final int ordinal = 1;
    private final String configSourceName = "OperationSystemConfigSource";
    /**
     * 当前类初始化时当前系统环境变量的快照
     */
    private final Map<String, String> envMap;

    public SystemConfigSource() {
        envMap = System.getenv ();
    }

    @Override
    public Set<String> getPropertyNames() {
        return envMap.keySet ();
    }

    @Override
    public int getOrdinal() {
        return 0;
    }

    @Override
    public String getValue(String propertyName) {
        return envMap.containsKey (propertyName) ? envMap.get (propertyName) : "";
    }

    @Override
    public String getName() {
        return configSourceName;
    }
}

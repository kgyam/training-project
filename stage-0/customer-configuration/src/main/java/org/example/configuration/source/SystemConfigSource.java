package org.example.configuration.source;

import java.util.Map;

/**
 * @author kg yam
 * @date 2021-03-12 17:45
 * @since
 */
public class SystemConfigSource extends MapBasedConfigSource {


    public SystemConfigSource() {
        super ("OperationSystemConfigSource", 1);
    }


    @Override
    public void getConfigData(Map data) throws Throwable {
        data = System.getenv ();
    }
}

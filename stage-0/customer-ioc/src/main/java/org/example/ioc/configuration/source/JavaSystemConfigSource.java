package org.example.ioc.configuration.source;

import java.util.Map;

/**
 * @author kg yam
 * @date 2021-03-12 16:39
 * @since
 */
public class JavaSystemConfigSource extends MapBasedConfigSource {

    public JavaSystemConfigSource() {
        super ("JavaSystemConfigSource", 2);
    }

    @Override
    public void getConfigData(Map data) throws Throwable {
      data= System.getProperties ();
    }

}
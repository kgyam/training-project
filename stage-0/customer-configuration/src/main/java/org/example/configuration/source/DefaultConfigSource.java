package org.example.configuration.source;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 兜底ConfigSource，这里会返回一个随机生成的application.name
 *
 * @author kg yam
 * @date 2021-03-24 16:42
 * @since
 */
public class DefaultConfigSource extends MapBasedConfigSource {

    DefaultConfigSource() {
        super ("DefaultConfigSource", 600);
    }

    @Override
    public Map prepareConfigData() throws Throwable {
        Map<String, String> map = new HashMap<> (1);
        Random random = new Random ();
        int randomInt = random.nextInt (100);
        String value = "default-application-name" + randomInt;
        map.put ("application.name", value);
        return map;
    }
}

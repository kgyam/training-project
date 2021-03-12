package org.example.ioc.configuration;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.ConfigValue;

/**
 * @author kg yam
 * @date 2021-03-12 11:43
 * @since
 */
public class ConfigUsageSample {

    public static void main(String[] args) {

        Config config = ConfigProvider.getConfig ();
        String serverUrl = config.getValue ("", String.class);
        callToServer (serverUrl);

        //or
        ConfigValue configServerUrl = config.getConfigValue ("");
        callToServer (configServerUrl.getValue ());

        // java -Dacme.myprj.som.url=http://other.sever/other/endpoint -jar demoProject.jar
    }

    private static void callToServer(String serverUrl) {
    }
}

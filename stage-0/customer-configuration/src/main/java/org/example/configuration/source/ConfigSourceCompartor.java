package org.example.configuration.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Comparator;

/**
 * @author kg yam
 * @date 2021-03-19 15:30
 * @since
 */
public class ConfigSourceCompartor implements Comparator<ConfigSource> {

    public static final Comparator<ConfigSource> INSTANCE = new ConfigSourceCompartor ();

    private ConfigSourceCompartor() {
    }

    @Override
    public int compare(ConfigSource o1, ConfigSource o2) {
        return Integer.compare (o2.getOrdinal (), o1.getOrdinal ());
    }
}

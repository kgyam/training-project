package org.example.ioc.configuration.converter;

import org.eclipse.microprofile.config.spi.Converter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author kg yam
 * @date 2021-03-19 17:58
 * @since
 */
public class Converters implements Iterable<Converter> {
    public static final int DEFAULT_PRIORITY = 100;

    private final Map<Class<?>, PriorityQueue<PrioritizedConverter>> typedConverters = new HashMap<> ();

    private ClassLoader classLoader;

    private boolean addedDiscoveredConverters = false;


    @Override
    public Iterator<Converter> iterator() {
        return null;
    }
}

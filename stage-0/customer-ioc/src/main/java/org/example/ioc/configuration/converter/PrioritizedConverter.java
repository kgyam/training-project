package org.example.ioc.configuration.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * @author kg yam
 * @date 2021-03-19 17:49
 * @since
 */
public class PrioritizedConverter<T> implements Converter<T>, Comparable<PrioritizedConverter<T>> {


    private final Converter<T> converter;
    private final int priority;


    public PrioritizedConverter(Converter converter, int priority) {
        this.converter = converter;
        this.priority = priority;
    }

    public Converter getConverter() {
        return converter;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(PrioritizedConverter<T> other) {
        return Integer.compare (other.getPriority (), this.priority);
    }

    @Override
    public T convert(String value) throws IllegalArgumentException, NullPointerException {
        return this.converter.convert (value);
    }
}

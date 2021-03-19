package org.example.ioc.configuration.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * @author kg yam
 * @date 2021-03-19 17:43
 * @since
 */
public abstract class AbstractConverter<T> implements Converter<T> {

    @Override
    public T convert(String value) throws IllegalArgumentException, NullPointerException {
        if (value == null) {
            throw new NullPointerException ("The value must not null");
        }
        return doConvert (value);
    }


    abstract T doConvert(String value);
}

package org.example.ioc.configuration;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * @author kg yam
 * @date 2021-03-15 10:20
 * @since
 */
public class StringConverter implements Converter {
    @Override
    public Object convert(String propertyValue) throws IllegalArgumentException, NullPointerException {
        return null;
    }
}

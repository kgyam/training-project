package org.example.configuration.converter;

/**
 * @author kg yam
 * @date 2021-03-19 17:46
 * @since
 */
public class BooleanConverter extends AbstractConverter {
    @Override
    Object doConvert(String value) {
        return Boolean.parseBoolean (value);
    }
}

package org.example.configuration.converter;

/**
 * @author kg yam
 * @date 2021-03-19 17:47
 * @since
 */
public class DoubleConverter extends AbstractConverter {
    @Override
    Object doConvert(String value) {
        return Double.parseDouble (value);
    }
}

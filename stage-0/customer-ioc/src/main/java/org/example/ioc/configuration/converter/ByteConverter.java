package org.example.ioc.configuration.converter;

/**
 * @author kg yam
 * @date 2021-03-19 17:47
 * @since
 */
public class ByteConverter extends AbstractConverter {
    @Override
    Object doConvert(String value) {
        return Byte.parseByte (value);
    }
}

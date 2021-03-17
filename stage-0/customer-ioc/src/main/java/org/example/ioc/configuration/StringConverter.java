package org.example.ioc.configuration;

import org.eclipse.microprofile.config.spi.Converter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author kg yam
 * @date 2021-03-15 10:20
 * @since
 */
public class StringConverter implements Converter {

    private final Logger logger = Logger.getLogger (getClass ().getName ());

    @Override
    public Object convert(String propertyValue) throws IllegalArgumentException, NullPointerException {

        Class klass = propertyValue.getClass ();

        if (boolean.class.isAssignableFrom (klass) || Boolean.class.isAssignableFrom (klass)) {
            return Boolean.parseBoolean (propertyValue);
        }

        if (byte.class.isAssignableFrom (klass) || Byte.class.isAssignableFrom (klass)) {
            return Byte.parseByte (propertyValue);
        }

        if (short.class.isAssignableFrom (klass) || Short.class.isAssignableFrom (klass)) {
            return Short.parseShort (propertyValue);
        }

        if (int.class.isAssignableFrom (klass) || Integer.class.isAssignableFrom (klass)) {
            return Integer.parseInt (propertyValue);
        }

        if (long.class.isAssignableFrom (klass) || Long.class.isAssignableFrom (klass)) {
            return Long.parseLong (propertyValue);
        }

        if (float.class.isAssignableFrom (klass) || Float.class.isAssignableFrom (klass)) {
            return Float.parseFloat (propertyValue);
        }

        if (double.class.isAssignableFrom (klass) || Double.class.isAssignableFrom (klass)) {
            return Double.parseDouble (propertyValue);
        }

        try {
            Class clazz = Class.forName (propertyValue);
            if (clazz != null) {
                return clazz;
            }
        } catch (ClassNotFoundException e) {
           //ignore
        }
        return propertyValue;
    }
}

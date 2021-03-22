package org.example.ioc.configuration.converter;

import org.eclipse.microprofile.config.spi.Converter;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

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


    public void addDiscoveredConverters() {
        if (addedDiscoveredConverters) {
            return;
        }

        addConverters (ServiceLoader.load (Converter.class, classLoader));
        addedDiscoveredConverters = true;
    }

    private void addConverters(Iterable<Converter> converters) {
        converters.forEach (this::addConverter);
    }

    private void addConverter(Converter converter) {
        addConverter (converter, DEFAULT_PRIORITY);
    }

    private void addConverter(Converter converter, int defaultPriority) {
        Class<?> converterType = resolveConvertedType (converter);
        addConverter (converter, defaultPriority, converterType);
    }

    private void addConverter(Converter converter, int defaultPriority, Class<?> converterType) {
        PriorityQueue priorityQueue = typedConverters.computeIfAbsent (converterType, t -> new PriorityQueue<> ());
        priorityQueue.offer (new PrioritizedConverter (converter, defaultPriority));
    }

    private Class<?> resolveConvertedType(Converter converter) {
        assertConverter (converter);
        Class<?> convertedType = null;
        Class<?> converterClass = converter.getClass ();
        while (converterClass != null) {
            convertedType = resolveConvertedType (converterClass);
            if (convertedType != null) {
                break;
            }

            Type superType = converterClass.getGenericSuperclass ();
            if (superType instanceof ParameterizedType) {
                convertedType = resolveConvertedType (superType);
            }

            if (convertedType != null) {
                break;
            }

            //递归
            converterClass = converterClass.getSuperclass ();
        }
        return convertedType;
    }

    private Class<?> resolveConvertedType(Class<?> converterClass) {
        Class<?> convertedType = null;

        for (Type genericInterface : converterClass.getGenericInterfaces ()) {
            convertedType = resolveConvertedType (genericInterface);
            if (convertedType != null) {
                break;
            }
        }
        return convertedType;
    }

    private Class<?> resolveConvertedType(Type type) {
        Class<?> convertedType = null;
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            if (pType.getRawType () instanceof Class) {
                Class<?> rawType = (Class) pType.getRawType ();
                if (Converter.class.isAssignableFrom (rawType)) {
                    Type[] arguments = pType.getActualTypeArguments ();
                    if (arguments.length == 1 && arguments[0] instanceof Class) {
                        convertedType = (Class) arguments[0];
                    }
                }
            }
        }
        return convertedType;
    }

    public Converters() {
        this (Thread.currentThread ().getContextClassLoader ());
    }

    public Converters(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }


    private void assertConverter(Converter<?> converter) {
        Class<?> converterClass = converter.getClass ();
        if (converterClass.isInterface ()) {
            throw new IllegalArgumentException ("The implementation class of Converter must not be an interface!");
        }
        if (Modifier.isAbstract (converterClass.getModifiers ())) {
            throw new IllegalArgumentException ("The implementation class of Converter must not be abstract!");
        }
    }

    @Override
    public Iterator<Converter> iterator() {
        return null;
    }
}

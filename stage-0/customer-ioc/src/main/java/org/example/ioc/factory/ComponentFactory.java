package org.example.ioc.factory;

import java.util.List;

/**
 * @author kg yam
 * @date 2021-03-10 15:17
 * @since
 */
public interface ComponentFactory {
    public static final String COMPONENT_CONTEXT = JndiComponentFactory.class.getName ();


    <C> List<C> getComponents(Class<C> componentType);

    <C> C getComponent(String componentName, Class<C> componentType);

    <C> C getComponent(String componentName);

}

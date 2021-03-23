package org.example.ioc.aware;

import org.example.ioc.factory.ComponentFactory;

/**
 * @author kg yam
 * @date 2021-03-10 17:14
 * @since
 */
public interface ComponentFactoryAware {
    void setComponentFactory(ComponentFactory componentFactory);
}

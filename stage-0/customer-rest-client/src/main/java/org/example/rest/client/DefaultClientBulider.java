package org.example.rest.client;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Configuration;
import java.security.KeyStore;
import java.util.Map;

public class DefaultClientBulider extends ClientBuilder {

    private Configuration configuration;

    @Override
    public ClientBuilder withConfig(Configuration config) {
        this.configuration = config;
        return this;
    }

    @Override
    public ClientBuilder sslContext(SSLContext sslContext) {
        throw new UnsupportedOperationException("not support ssl operation");
    }

    @Override
    public ClientBuilder keyStore(KeyStore keyStore, char[] password) {
        return null;
    }

    @Override
    public ClientBuilder trustStore(KeyStore trustStore) {
        return null;
    }

    @Override
    public ClientBuilder hostnameVerifier(HostnameVerifier verifier) {
        return null;
    }

    @Override
    public Client build() {
        return new DefaultClient();
    }

    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }

    @Override
    public ClientBuilder property(String name, Object value) {
        if (configuration != null) {
            configuration.getProperties().put(name, value);
        }
        return this;
    }

    @Override
    public ClientBuilder register(Class<?> componentClass) {
        return null;
    }

    @Override
    public ClientBuilder register(Class<?> componentClass, int priority) {
        return null;
    }

    @Override
    public ClientBuilder register(Class<?> componentClass, Class<?>... contracts) {
        return null;
    }

    @Override
    public ClientBuilder register(Class<?> componentClass, Map<Class<?>, Integer> contracts) {
        return null;
    }

    @Override
    public ClientBuilder register(Object component) {
        return null;
    }

    @Override
    public ClientBuilder register(Object component, int priority) {
        return null;
    }

    @Override
    public ClientBuilder register(Object component, Class<?>... contracts) {
        return null;
    }

    @Override
    public ClientBuilder register(Object component, Map<Class<?>, Integer> contracts) {
        return null;
    }
}

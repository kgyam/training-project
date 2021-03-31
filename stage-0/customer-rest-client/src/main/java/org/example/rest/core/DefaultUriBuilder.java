package org.example.rest.core;

import org.apache.commons.lang.StringUtils;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriBuilderException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.*;

public class DefaultUriBuilder extends UriBuilder {


    private String scheme;

    private String schemeSpecificPart;

    private String userInfo;

    private String host;

    private int port;

    private String path;

    private String fragment;

    private String uriTemplate;

    private String resolvedTemplate;

    private MultivaluedMap<String, String> matrixParams = new MultivaluedHashMap<>();

    private MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>();


    public DefaultUriBuilder(DefaultUriBuilder other) {
        this.scheme = other.scheme;
        this.schemeSpecificPart = other.schemeSpecificPart;
        this.userInfo = other.userInfo;
        this.host = other.host;
        this.port = other.port;
        this.fragment = other.fragment;
        this.path = other.path;
        this.uriTemplate = other.uriTemplate;
        this.resolvedTemplate = other.resolvedTemplate;
        this.queryParams.putAll(other.queryParams);
        this.matrixParams.putAll(other.matrixParams);
    }


    public DefaultUriBuilder() {
    }

    @Override
    public UriBuilder clone() {
        return new DefaultUriBuilder(this);
    }

    @Override
    public UriBuilder uri(URI uri) {
        this.scheme = uri.getScheme();
        this.schemeSpecificPart = uri.getSchemeSpecificPart();
        this.userInfo = uri.getUserInfo();
        this.host = uri.getHost();
        this.port = uri.getPort();
        this.fragment = uri.getFragment();
        this.path = uri.getPath();
        String query = uri.getRawQuery();
        this.queryParams.clear();
        this.queryParams.putAll(resolveParameters(query));
        return this;
    }

    /**
     * 解析query参数字符串
     * 参数范例 q=12&id=id-123&xx=xx
     *
     * @param query
     * @return
     */
    private Map<String, ? extends List<String>> resolveParameters(String query) {
        if (StringUtils.isNotBlank(query)) {
            Map<String, List<String>> parametersMap = new LinkedHashMap<>();
            String[] queryParams = StringUtils.split(query, "&");
            for (String queryParam : queryParams) {
                //一般的情况下[0]为key,[1]为value
                String[] paramNameAndValue = StringUtils.split(queryParam, "=");
                String paramName = paramNameAndValue[0];
                if (paramNameAndValue != null && paramNameAndValue.length > 1) {
                    String paramValue = paramNameAndValue[1];
                    if (parametersMap.containsKey(paramName)) {
                        parametersMap.get(paramName).add(paramValue);
                    } else {
                        List<String> paramValues = new LinkedList<>();
                        paramValues.add(paramValue);
                        parametersMap.put(paramName, paramValues);
                    }
                }
            }
            return parametersMap;
        }
        return Collections.emptyMap();
    }

    @Override
    public UriBuilder uri(String uriTemplate) {
        return null;
    }

    @Override
    public UriBuilder scheme(String scheme) {
        return null;
    }

    @Override
    public UriBuilder schemeSpecificPart(String ssp) {
        return null;
    }

    @Override
    public UriBuilder userInfo(String ui) {
        return null;
    }

    @Override
    public UriBuilder host(String host) {
        return null;
    }

    @Override
    public UriBuilder port(int port) {
        return null;
    }

    @Override
    public UriBuilder replacePath(String path) {
        return null;
    }

    @Override
    public UriBuilder path(String path) {
        return null;
    }

    @Override
    public UriBuilder path(Class resource) {
        return null;
    }

    @Override
    public UriBuilder path(Class resource, String method) {
        return null;
    }

    @Override
    public UriBuilder path(Method method) {
        return null;
    }

    @Override
    public UriBuilder segment(String... segments) {
        return null;
    }

    @Override
    public UriBuilder replaceMatrix(String matrix) {
        return null;
    }

    @Override
    public UriBuilder matrixParam(String name, Object... values) {
        return null;
    }

    @Override
    public UriBuilder replaceMatrixParam(String name, Object... values) {
        return null;
    }

    @Override
    public UriBuilder replaceQuery(String query) {
        return null;
    }

    @Override
    public UriBuilder queryParam(String name, Object... values) {
        return null;
    }

    @Override
    public UriBuilder replaceQueryParam(String name, Object... values) {
        return null;
    }

    @Override
    public UriBuilder fragment(String fragment) {
        return null;
    }

    @Override
    public UriBuilder resolveTemplate(String name, Object value) {
        return null;
    }

    @Override
    public UriBuilder resolveTemplate(String name, Object value, boolean encodeSlashInPath) {
        return null;
    }

    @Override
    public UriBuilder resolveTemplateFromEncoded(String name, Object value) {
        return null;
    }

    @Override
    public UriBuilder resolveTemplates(Map<String, Object> templateValues) {
        return null;
    }

    @Override
    public UriBuilder resolveTemplates(Map<String, Object> templateValues, boolean encodeSlashInPath) throws IllegalArgumentException {
        return null;
    }

    @Override
    public UriBuilder resolveTemplatesFromEncoded(Map<String, Object> templateValues) {
        return null;
    }

    @Override
    public URI buildFromMap(Map<String, ?> values) {
        return null;
    }

    @Override
    public URI buildFromMap(Map<String, ?> values, boolean encodeSlashInPath) throws IllegalArgumentException, UriBuilderException {
        return null;
    }

    @Override
    public URI buildFromEncodedMap(Map<String, ?> values) throws IllegalArgumentException, UriBuilderException {
        return null;
    }

    @Override
    public URI build(Object... values) throws IllegalArgumentException, UriBuilderException {
        return null;
    }

    @Override
    public URI build(Object[] values, boolean encodeSlashInPath) throws IllegalArgumentException, UriBuilderException {
        return null;
    }

    @Override
    public URI buildFromEncoded(Object... values) throws IllegalArgumentException, UriBuilderException {
        return null;
    }

    @Override
    public String toTemplate() {
        return null;
    }
}

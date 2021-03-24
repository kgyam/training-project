package org.example.configuration.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 成员变量修饰符移除final修饰，如果加上final修饰。
 * 扩展configSource的时候，如果传入参数是需要在prepareConfigData中用到就会出现问题。
 * 因为父类构造函数必须在子类赋值前先执行，那么prepareConfigData会容易出现NPE
 *
 * @author kg yam
 * @date 2021-03-19 15:34
 * @since
 */
public abstract class MapBasedConfigSource implements ConfigSource {

    private Map<String, String> properties;
    private String name;
    private int ordinal;

    public MapBasedConfigSource(String name, int ordinal) {
        initMapBasedConfigSource (name, ordinal);
    }

    protected MapBasedConfigSource() {

    }


    protected void initMapBasedConfigSource(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
        this.properties = getProperties ();
    }

    @Override
    public Set<String> getPropertyNames() {
        return properties.keySet ();
    }

    @Override
    public String getValue(String propertyName) {
        return properties.get (propertyName);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public final Map<String, String> getProperties() {
        Map<String, String> data = new HashMap<> ();
        try {
            data = prepareConfigData ();
        } catch (Throwable throwable) {
            throw new IllegalStateException ("准备配置数据发生错误", throwable);
        }
        return Collections.unmodifiableMap (data);
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }


    public abstract Map prepareConfigData() throws Throwable;
}

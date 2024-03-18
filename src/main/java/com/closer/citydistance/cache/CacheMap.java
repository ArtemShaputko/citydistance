package com.closer.citydistance.cache;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@Component
@Scope("prototype")
public class CacheMap<K, V> extends LinkedHashMap<K, V> {
    private static final Integer MAX_SIZE = 1000;

    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size()>= MAX_SIZE;
    }

}

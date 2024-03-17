package com.closer.citydistance.cache;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Component
@Data
public class CacheMap<K, V> extends LinkedHashMap<K, V> {
    private static final Integer maxSize = 1000;

    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size()>=maxSize;
    }

}

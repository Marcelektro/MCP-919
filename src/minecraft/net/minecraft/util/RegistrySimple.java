package net.minecraft.util;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrySimple<K, V> implements IRegistry<K, V>
{
    private static final Logger logger = LogManager.getLogger();
    protected final Map<K, V> registryObjects = this.createUnderlyingMap();

    protected Map<K, V> createUnderlyingMap()
    {
        return Maps.<K, V>newHashMap();
    }

    public V getObject(K name)
    {
        return this.registryObjects.get(name);
    }

    /**
     * Register an object on this registry.
     */
    public void putObject(K key, V value)
    {
        Validate.notNull(key);
        Validate.notNull(value);

        if (this.registryObjects.containsKey(key))
        {
            logger.debug("Adding duplicate key \'" + key + "\' to registry");
        }

        this.registryObjects.put(key, value);
    }

    public Set<K> getKeys()
    {
        return Collections.<K>unmodifiableSet(this.registryObjects.keySet());
    }

    /**
     * Does this registry contain an entry for the given key?
     */
    public boolean containsKey(K key)
    {
        return this.registryObjects.containsKey(key);
    }

    public Iterator<V> iterator()
    {
        return this.registryObjects.values().iterator();
    }
}

package net.minecraft.util;

import org.apache.commons.lang3.Validate;

public class RegistryNamespacedDefaultedByKey<K, V> extends RegistryNamespaced<K, V>
{
    /** The key of the default value. */
    private final K defaultValueKey;

    /**
     * The default value for this registry, retrurned in the place of a null value.
     */
    private V defaultValue;

    public RegistryNamespacedDefaultedByKey(K defaultValueKeyIn)
    {
        this.defaultValueKey = defaultValueKeyIn;
    }

    public void register(int id, K key, V value)
    {
        if (this.defaultValueKey.equals(key))
        {
            this.defaultValue = value;
        }

        super.register(id, key, value);
    }

    /**
     * validates that this registry's key is non-null
     */
    public void validateKey()
    {
        Validate.notNull(this.defaultValueKey);
    }

    public V getObject(K name)
    {
        V v = super.getObject(name);
        return (V)(v == null ? this.defaultValue : v);
    }

    /**
     * Gets the object identified by the given ID.
     */
    public V getObjectById(int id)
    {
        V v = super.getObjectById(id);
        return (V)(v == null ? this.defaultValue : v);
    }
}

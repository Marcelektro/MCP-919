package net.minecraft.util;

public interface IRegistry<K, V> extends Iterable<V>
{
    V getObject(K name);

    /**
     * Register an object on this registry.
     */
    void putObject(K key, V value);
}

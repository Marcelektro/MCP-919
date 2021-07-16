package net.minecraft.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.util.IObjectIntIterable;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.RegistrySimple;

public class RegistryNamespaced<K, V> extends RegistrySimple<K, V> implements IObjectIntIterable<V> {
   protected final ObjectIntIdentityMap<V> field_148759_a = new ObjectIntIdentityMap();
   protected final Map<V, K> field_148758_b;

   public RegistryNamespaced() {
      this.field_148758_b = ((BiMap)this.field_82596_a).inverse();
   }

   public void func_177775_a(int p_177775_1_, K p_177775_2_, V p_177775_3_) {
      this.field_148759_a.func_148746_a(p_177775_3_, p_177775_1_);
      this.func_82595_a(p_177775_2_, p_177775_3_);
   }

   protected Map<K, V> func_148740_a() {
      return HashBiMap.<K, V>create();
   }

   public V func_82594_a(K p_82594_1_) {
      return super.func_82594_a(p_82594_1_);
   }

   public K func_177774_c(V p_177774_1_) {
      return (K)this.field_148758_b.get(p_177774_1_);
   }

   public boolean func_148741_d(K p_148741_1_) {
      return super.func_148741_d(p_148741_1_);
   }

   public int func_148757_b(V p_148757_1_) {
      return this.field_148759_a.func_148747_b(p_148757_1_);
   }

   public V func_148754_a(int p_148754_1_) {
      return (V)this.field_148759_a.func_148745_a(p_148754_1_);
   }

   public Iterator<V> iterator() {
      return this.field_148759_a.iterator();
   }
}

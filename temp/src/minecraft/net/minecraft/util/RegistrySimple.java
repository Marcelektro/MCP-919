package net.minecraft.util;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.util.IRegistry;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrySimple<K, V> implements IRegistry<K, V> {
   private static final Logger field_148743_a = LogManager.getLogger();
   protected final Map<K, V> field_82596_a = this.func_148740_a();

   protected Map<K, V> func_148740_a() {
      return Maps.<K, V>newHashMap();
   }

   public V func_82594_a(K p_82594_1_) {
      return this.field_82596_a.get(p_82594_1_);
   }

   public void func_82595_a(K p_82595_1_, V p_82595_2_) {
      Validate.notNull(p_82595_1_);
      Validate.notNull(p_82595_2_);
      if(this.field_82596_a.containsKey(p_82595_1_)) {
         field_148743_a.debug("Adding duplicate key \'" + p_82595_1_ + "\' to registry");
      }

      this.field_82596_a.put(p_82595_1_, p_82595_2_);
   }

   public Set<K> func_148742_b() {
      return Collections.<K>unmodifiableSet(this.field_82596_a.keySet());
   }

   public boolean func_148741_d(K p_148741_1_) {
      return this.field_82596_a.containsKey(p_148741_1_);
   }

   public Iterator<V> iterator() {
      return this.field_82596_a.values().iterator();
   }
}

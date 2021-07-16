package net.minecraft.util;

import net.minecraft.util.RegistryNamespaced;
import org.apache.commons.lang3.Validate;

public class RegistryNamespacedDefaultedByKey<K, V> extends RegistryNamespaced<K, V> {
   private final K field_148760_d;
   private V field_148761_e;

   public RegistryNamespacedDefaultedByKey(K p_i46017_1_) {
      this.field_148760_d = p_i46017_1_;
   }

   public void func_177775_a(int p_177775_1_, K p_177775_2_, V p_177775_3_) {
      if(this.field_148760_d.equals(p_177775_2_)) {
         this.field_148761_e = p_177775_3_;
      }

      super.func_177775_a(p_177775_1_, p_177775_2_, p_177775_3_);
   }

   public void func_177776_a() {
      Validate.notNull(this.field_148760_d);
   }

   public V func_82594_a(K p_82594_1_) {
      V v = super.func_82594_a(p_82594_1_);
      return (V)(v == null?this.field_148761_e:v);
   }

   public V func_148754_a(int p_148754_1_) {
      V v = super.func_148754_a(p_148754_1_);
      return (V)(v == null?this.field_148761_e:v);
   }
}

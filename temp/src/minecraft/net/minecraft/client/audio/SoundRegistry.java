package net.minecraft.client.audio;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.util.RegistrySimple;
import net.minecraft.util.ResourceLocation;

public class SoundRegistry extends RegistrySimple<ResourceLocation, SoundEventAccessorComposite> {
   private Map<ResourceLocation, SoundEventAccessorComposite> field_148764_a;

   protected Map<ResourceLocation, SoundEventAccessorComposite> func_148740_a() {
      this.field_148764_a = Maps.<ResourceLocation, SoundEventAccessorComposite>newHashMap();
      return this.field_148764_a;
   }

   public void func_148762_a(SoundEventAccessorComposite p_148762_1_) {
      this.func_82595_a(p_148762_1_.func_148729_c(), p_148762_1_);
   }

   public void func_148763_c() {
      this.field_148764_a.clear();
   }
}

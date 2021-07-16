package net.minecraft.client.audio;

import net.minecraft.client.audio.ISoundEventAccessor;
import net.minecraft.client.audio.SoundPoolEntry;

public class SoundEventAccessor implements ISoundEventAccessor<SoundPoolEntry> {
   private final SoundPoolEntry field_148739_a;
   private final int field_148738_b;

   SoundEventAccessor(SoundPoolEntry p_i45123_1_, int p_i45123_2_) {
      this.field_148739_a = p_i45123_1_;
      this.field_148738_b = p_i45123_2_;
   }

   public int func_148721_a() {
      return this.field_148738_b;
   }

   public SoundPoolEntry func_148720_g() {
      return new SoundPoolEntry(this.field_148739_a);
   }
}

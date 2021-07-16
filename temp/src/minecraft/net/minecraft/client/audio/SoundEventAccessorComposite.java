package net.minecraft.client.audio;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.client.audio.ISoundEventAccessor;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.util.ResourceLocation;

public class SoundEventAccessorComposite implements ISoundEventAccessor<SoundPoolEntry> {
   private final List<ISoundEventAccessor<SoundPoolEntry>> field_148736_a = Lists.<ISoundEventAccessor<SoundPoolEntry>>newArrayList();
   private final Random field_148734_b = new Random();
   private final ResourceLocation field_148735_c;
   private final SoundCategory field_148732_d;
   private double field_148733_e;
   private double field_148731_f;

   public SoundEventAccessorComposite(ResourceLocation p_i45120_1_, double p_i45120_2_, double p_i45120_4_, SoundCategory p_i45120_6_) {
      this.field_148735_c = p_i45120_1_;
      this.field_148731_f = p_i45120_4_;
      this.field_148733_e = p_i45120_2_;
      this.field_148732_d = p_i45120_6_;
   }

   public int func_148721_a() {
      int i = 0;

      for(ISoundEventAccessor<SoundPoolEntry> isoundeventaccessor : this.field_148736_a) {
         i += isoundeventaccessor.func_148721_a();
      }

      return i;
   }

   public SoundPoolEntry func_148720_g() {
      int i = this.func_148721_a();
      if(!this.field_148736_a.isEmpty() && i != 0) {
         int j = this.field_148734_b.nextInt(i);

         for(ISoundEventAccessor<SoundPoolEntry> isoundeventaccessor : this.field_148736_a) {
            j -= isoundeventaccessor.func_148721_a();
            if(j < 0) {
               SoundPoolEntry soundpoolentry = (SoundPoolEntry)isoundeventaccessor.func_148720_g();
               soundpoolentry.func_148651_a(soundpoolentry.func_148650_b() * this.field_148733_e);
               soundpoolentry.func_148647_b(soundpoolentry.func_148649_c() * this.field_148731_f);
               return soundpoolentry;
            }
         }

         return SoundHandler.field_147700_a;
      } else {
         return SoundHandler.field_147700_a;
      }
   }

   public void func_148727_a(ISoundEventAccessor<SoundPoolEntry> p_148727_1_) {
      this.field_148736_a.add(p_148727_1_);
   }

   public ResourceLocation func_148729_c() {
      return this.field_148735_c;
   }

   public SoundCategory func_148728_d() {
      return this.field_148732_d;
   }
}

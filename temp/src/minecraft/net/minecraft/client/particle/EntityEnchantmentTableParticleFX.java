package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.world.World;

public class EntityEnchantmentTableParticleFX extends EntityFX {
   private float field_70565_a;
   private double field_70568_aq;
   private double field_70567_ar;
   private double field_70566_as;

   protected EntityEnchantmentTableParticleFX(World p_i1204_1_, double p_i1204_2_, double p_i1204_4_, double p_i1204_6_, double p_i1204_8_, double p_i1204_10_, double p_i1204_12_) {
      super(p_i1204_1_, p_i1204_2_, p_i1204_4_, p_i1204_6_, p_i1204_8_, p_i1204_10_, p_i1204_12_);
      this.field_70159_w = p_i1204_8_;
      this.field_70181_x = p_i1204_10_;
      this.field_70179_y = p_i1204_12_;
      this.field_70568_aq = p_i1204_2_;
      this.field_70567_ar = p_i1204_4_;
      this.field_70566_as = p_i1204_6_;
      this.field_70165_t = this.field_70169_q = p_i1204_2_ + p_i1204_8_;
      this.field_70163_u = this.field_70167_r = p_i1204_4_ + p_i1204_10_;
      this.field_70161_v = this.field_70166_s = p_i1204_6_ + p_i1204_12_;
      float f = this.field_70146_Z.nextFloat() * 0.6F + 0.4F;
      this.field_70565_a = this.field_70544_f = this.field_70146_Z.nextFloat() * 0.5F + 0.2F;
      this.field_70552_h = this.field_70553_i = this.field_70551_j = 1.0F * f;
      this.field_70553_i *= 0.9F;
      this.field_70552_h *= 0.9F;
      this.field_70547_e = (int)(Math.random() * 10.0D) + 30;
      this.field_70145_X = true;
      this.func_70536_a((int)(Math.random() * 26.0D + 1.0D + 224.0D));
   }

   public int func_70070_b(float p_70070_1_) {
      int i = super.func_70070_b(p_70070_1_);
      float f = (float)this.field_70546_d / (float)this.field_70547_e;
      f = f * f;
      f = f * f;
      int j = i & 255;
      int k = i >> 16 & 255;
      k = k + (int)(f * 15.0F * 16.0F);
      if(k > 240) {
         k = 240;
      }

      return j | k << 16;
   }

   public float func_70013_c(float p_70013_1_) {
      float f = super.func_70013_c(p_70013_1_);
      float f1 = (float)this.field_70546_d / (float)this.field_70547_e;
      f1 = f1 * f1;
      f1 = f1 * f1;
      return f * (1.0F - f1) + f1;
   }

   public void func_70071_h_() {
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      float f = (float)this.field_70546_d / (float)this.field_70547_e;
      f = 1.0F - f;
      float f1 = 1.0F - f;
      f1 = f1 * f1;
      f1 = f1 * f1;
      this.field_70165_t = this.field_70568_aq + this.field_70159_w * (double)f;
      this.field_70163_u = this.field_70567_ar + this.field_70181_x * (double)f - (double)(f1 * 1.2F);
      this.field_70161_v = this.field_70566_as + this.field_70179_y * (double)f;
      if(this.field_70546_d++ >= this.field_70547_e) {
         this.func_70106_y();
      }

   }

   public static class EnchantmentTable implements IParticleFactory {
      public EntityFX func_178902_a(int p_178902_1_, World p_178902_2_, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
         return new EntityEnchantmentTableParticleFX(p_178902_2_, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
      }
   }
}

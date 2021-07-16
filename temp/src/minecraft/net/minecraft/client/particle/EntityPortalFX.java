package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityPortalFX extends EntityFX {
   private float field_70571_a;
   private double field_70574_aq;
   private double field_70573_ar;
   private double field_70572_as;

   protected EntityPortalFX(World p_i46351_1_, double p_i46351_2_, double p_i46351_4_, double p_i46351_6_, double p_i46351_8_, double p_i46351_10_, double p_i46351_12_) {
      super(p_i46351_1_, p_i46351_2_, p_i46351_4_, p_i46351_6_, p_i46351_8_, p_i46351_10_, p_i46351_12_);
      this.field_70159_w = p_i46351_8_;
      this.field_70181_x = p_i46351_10_;
      this.field_70179_y = p_i46351_12_;
      this.field_70574_aq = this.field_70165_t = p_i46351_2_;
      this.field_70573_ar = this.field_70163_u = p_i46351_4_;
      this.field_70572_as = this.field_70161_v = p_i46351_6_;
      float f = this.field_70146_Z.nextFloat() * 0.6F + 0.4F;
      this.field_70571_a = this.field_70544_f = this.field_70146_Z.nextFloat() * 0.2F + 0.5F;
      this.field_70552_h = this.field_70553_i = this.field_70551_j = 1.0F * f;
      this.field_70553_i *= 0.3F;
      this.field_70552_h *= 0.9F;
      this.field_70547_e = (int)(Math.random() * 10.0D) + 40;
      this.field_70145_X = true;
      this.func_70536_a((int)(Math.random() * 8.0D));
   }

   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
      float f = ((float)this.field_70546_d + p_180434_3_) / (float)this.field_70547_e;
      f = 1.0F - f;
      f = f * f;
      f = 1.0F - f;
      this.field_70544_f = this.field_70571_a * f;
      super.func_180434_a(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
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
      f1 = f1 * f1 * f1 * f1;
      return f * (1.0F - f1) + f1;
   }

   public void func_70071_h_() {
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      float f = (float)this.field_70546_d / (float)this.field_70547_e;
      f = -f + f * f * 2.0F;
      f = 1.0F - f;
      this.field_70165_t = this.field_70574_aq + this.field_70159_w * (double)f;
      this.field_70163_u = this.field_70573_ar + this.field_70181_x * (double)f + (double)(1.0F - f);
      this.field_70161_v = this.field_70572_as + this.field_70179_y * (double)f;
      if(this.field_70546_d++ >= this.field_70547_e) {
         this.func_70106_y();
      }

   }

   public static class Factory implements IParticleFactory {
      public EntityFX func_178902_a(int p_178902_1_, World p_178902_2_, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
         return new EntityPortalFX(p_178902_2_, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
      }
   }
}

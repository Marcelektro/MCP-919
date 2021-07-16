package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFlameFX extends EntityFX {
   private float field_70562_a;

   protected EntityFlameFX(World p_i1209_1_, double p_i1209_2_, double p_i1209_4_, double p_i1209_6_, double p_i1209_8_, double p_i1209_10_, double p_i1209_12_) {
      super(p_i1209_1_, p_i1209_2_, p_i1209_4_, p_i1209_6_, p_i1209_8_, p_i1209_10_, p_i1209_12_);
      this.field_70159_w = this.field_70159_w * 0.009999999776482582D + p_i1209_8_;
      this.field_70181_x = this.field_70181_x * 0.009999999776482582D + p_i1209_10_;
      this.field_70179_y = this.field_70179_y * 0.009999999776482582D + p_i1209_12_;
      this.field_70165_t += (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.05F);
      this.field_70163_u += (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.05F);
      this.field_70161_v += (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.05F);
      this.field_70562_a = this.field_70544_f;
      this.field_70552_h = this.field_70553_i = this.field_70551_j = 1.0F;
      this.field_70547_e = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
      this.field_70145_X = true;
      this.func_70536_a(48);
   }

   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
      float f = ((float)this.field_70546_d + p_180434_3_) / (float)this.field_70547_e;
      this.field_70544_f = this.field_70562_a * (1.0F - f * f * 0.5F);
      super.func_180434_a(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
   }

   public int func_70070_b(float p_70070_1_) {
      float f = ((float)this.field_70546_d + p_70070_1_) / (float)this.field_70547_e;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      int i = super.func_70070_b(p_70070_1_);
      int j = i & 255;
      int k = i >> 16 & 255;
      j = j + (int)(f * 15.0F * 16.0F);
      if(j > 240) {
         j = 240;
      }

      return j | k << 16;
   }

   public float func_70013_c(float p_70013_1_) {
      float f = ((float)this.field_70546_d + p_70013_1_) / (float)this.field_70547_e;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      float f1 = super.func_70013_c(p_70013_1_);
      return f1 * f + (1.0F - f);
   }

   public void func_70071_h_() {
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      if(this.field_70546_d++ >= this.field_70547_e) {
         this.func_70106_y();
      }

      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      this.field_70159_w *= 0.9599999785423279D;
      this.field_70181_x *= 0.9599999785423279D;
      this.field_70179_y *= 0.9599999785423279D;
      if(this.field_70122_E) {
         this.field_70159_w *= 0.699999988079071D;
         this.field_70179_y *= 0.699999988079071D;
      }

   }

   public static class Factory implements IParticleFactory {
      public EntityFX func_178902_a(int p_178902_1_, World p_178902_2_, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
         return new EntityFlameFX(p_178902_2_, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
      }
   }
}

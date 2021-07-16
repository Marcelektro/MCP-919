package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityLavaFX extends EntityFX {
   private float field_70586_a;

   protected EntityLavaFX(World p_i1215_1_, double p_i1215_2_, double p_i1215_4_, double p_i1215_6_) {
      super(p_i1215_1_, p_i1215_2_, p_i1215_4_, p_i1215_6_, 0.0D, 0.0D, 0.0D);
      this.field_70159_w *= 0.800000011920929D;
      this.field_70181_x *= 0.800000011920929D;
      this.field_70179_y *= 0.800000011920929D;
      this.field_70181_x = (double)(this.field_70146_Z.nextFloat() * 0.4F + 0.05F);
      this.field_70552_h = this.field_70553_i = this.field_70551_j = 1.0F;
      this.field_70544_f *= this.field_70146_Z.nextFloat() * 2.0F + 0.2F;
      this.field_70586_a = this.field_70544_f;
      this.field_70547_e = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
      this.field_70145_X = false;
      this.func_70536_a(49);
   }

   public int func_70070_b(float p_70070_1_) {
      float f = ((float)this.field_70546_d + p_70070_1_) / (float)this.field_70547_e;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      int i = super.func_70070_b(p_70070_1_);
      int j = 240;
      int k = i >> 16 & 255;
      return j | k << 16;
   }

   public float func_70013_c(float p_70013_1_) {
      return 1.0F;
   }

   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
      float f = ((float)this.field_70546_d + p_180434_3_) / (float)this.field_70547_e;
      this.field_70544_f = this.field_70586_a * (1.0F - f * f);
      super.func_180434_a(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
   }

   public void func_70071_h_() {
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      if(this.field_70546_d++ >= this.field_70547_e) {
         this.func_70106_y();
      }

      float f = (float)this.field_70546_d / (float)this.field_70547_e;
      if(this.field_70146_Z.nextFloat() > f) {
         this.field_70170_p.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70159_w, this.field_70181_x, this.field_70179_y, new int[0]);
      }

      this.field_70181_x -= 0.03D;
      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      this.field_70159_w *= 0.9990000128746033D;
      this.field_70181_x *= 0.9990000128746033D;
      this.field_70179_y *= 0.9990000128746033D;
      if(this.field_70122_E) {
         this.field_70159_w *= 0.699999988079071D;
         this.field_70179_y *= 0.699999988079071D;
      }

   }

   public static class Factory implements IParticleFactory {
      public EntityFX func_178902_a(int p_178902_1_, World p_178902_2_, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
         return new EntityLavaFX(p_178902_2_, p_178902_3_, p_178902_5_, p_178902_7_);
      }
   }
}

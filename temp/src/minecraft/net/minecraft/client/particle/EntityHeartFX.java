package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityHeartFX extends EntityFX {
   float field_70575_a;

   protected EntityHeartFX(World p_i1211_1_, double p_i1211_2_, double p_i1211_4_, double p_i1211_6_, double p_i1211_8_, double p_i1211_10_, double p_i1211_12_) {
      this(p_i1211_1_, p_i1211_2_, p_i1211_4_, p_i1211_6_, p_i1211_8_, p_i1211_10_, p_i1211_12_, 2.0F);
   }

   protected EntityHeartFX(World p_i46354_1_, double p_i46354_2_, double p_i46354_4_, double p_i46354_6_, double p_i46354_8_, double p_i46354_10_, double p_i46354_12_, float p_i46354_14_) {
      super(p_i46354_1_, p_i46354_2_, p_i46354_4_, p_i46354_6_, 0.0D, 0.0D, 0.0D);
      this.field_70159_w *= 0.009999999776482582D;
      this.field_70181_x *= 0.009999999776482582D;
      this.field_70179_y *= 0.009999999776482582D;
      this.field_70181_x += 0.1D;
      this.field_70544_f *= 0.75F;
      this.field_70544_f *= p_i46354_14_;
      this.field_70575_a = this.field_70544_f;
      this.field_70547_e = 16;
      this.field_70145_X = false;
      this.func_70536_a(80);
   }

   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
      float f = ((float)this.field_70546_d + p_180434_3_) / (float)this.field_70547_e * 32.0F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      this.field_70544_f = this.field_70575_a * f;
      super.func_180434_a(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
   }

   public void func_70071_h_() {
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      if(this.field_70546_d++ >= this.field_70547_e) {
         this.func_70106_y();
      }

      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      if(this.field_70163_u == this.field_70167_r) {
         this.field_70159_w *= 1.1D;
         this.field_70179_y *= 1.1D;
      }

      this.field_70159_w *= 0.8600000143051147D;
      this.field_70181_x *= 0.8600000143051147D;
      this.field_70179_y *= 0.8600000143051147D;
      if(this.field_70122_E) {
         this.field_70159_w *= 0.699999988079071D;
         this.field_70179_y *= 0.699999988079071D;
      }

   }

   public static class AngryVillagerFactory implements IParticleFactory {
      public EntityFX func_178902_a(int p_178902_1_, World p_178902_2_, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
         EntityFX entityfx = new EntityHeartFX(p_178902_2_, p_178902_3_, p_178902_5_ + 0.5D, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
         entityfx.func_70536_a(81);
         entityfx.func_70538_b(1.0F, 1.0F, 1.0F);
         return entityfx;
      }
   }

   public static class Factory implements IParticleFactory {
      public EntityFX func_178902_a(int p_178902_1_, World p_178902_2_, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
         return new EntityHeartFX(p_178902_2_, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
      }
   }
}

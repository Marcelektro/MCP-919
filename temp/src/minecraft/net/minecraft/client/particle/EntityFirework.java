package net.minecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemDye;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFirework {
   public static class Factory implements IParticleFactory {
      public EntityFX func_178902_a(int p_178902_1_, World p_178902_2_, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
         EntityFirework.SparkFX entityfirework$sparkfx = new EntityFirework.SparkFX(p_178902_2_, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_, Minecraft.func_71410_x().field_71452_i);
         entityfirework$sparkfx.func_82338_g(0.99F);
         return entityfirework$sparkfx;
      }
   }

   public static class OverlayFX extends EntityFX {
      protected OverlayFX(World p_i46466_1_, double p_i46466_2_, double p_i46466_4_, double p_i46466_6_) {
         super(p_i46466_1_, p_i46466_2_, p_i46466_4_, p_i46466_6_);
         this.field_70547_e = 4;
      }

      public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
         float f = 0.25F;
         float f1 = 0.5F;
         float f2 = 0.125F;
         float f3 = 0.375F;
         float f4 = 7.1F * MathHelper.func_76126_a(((float)this.field_70546_d + p_180434_3_ - 1.0F) * 0.25F * 3.1415927F);
         this.field_82339_as = 0.6F - ((float)this.field_70546_d + p_180434_3_ - 1.0F) * 0.25F * 0.5F;
         float f5 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)p_180434_3_ - field_70556_an);
         float f6 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)p_180434_3_ - field_70554_ao);
         float f7 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)p_180434_3_ - field_70555_ap);
         int i = this.func_70070_b(p_180434_3_);
         int j = i >> 16 & '\uffff';
         int k = i & '\uffff';
         p_180434_1_.func_181662_b((double)(f5 - p_180434_4_ * f4 - p_180434_7_ * f4), (double)(f6 - p_180434_5_ * f4), (double)(f7 - p_180434_6_ * f4 - p_180434_8_ * f4)).func_181673_a(0.5D, 0.375D).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(j, k).func_181675_d();
         p_180434_1_.func_181662_b((double)(f5 - p_180434_4_ * f4 + p_180434_7_ * f4), (double)(f6 + p_180434_5_ * f4), (double)(f7 - p_180434_6_ * f4 + p_180434_8_ * f4)).func_181673_a(0.5D, 0.125D).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(j, k).func_181675_d();
         p_180434_1_.func_181662_b((double)(f5 + p_180434_4_ * f4 + p_180434_7_ * f4), (double)(f6 + p_180434_5_ * f4), (double)(f7 + p_180434_6_ * f4 + p_180434_8_ * f4)).func_181673_a(0.25D, 0.125D).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(j, k).func_181675_d();
         p_180434_1_.func_181662_b((double)(f5 + p_180434_4_ * f4 - p_180434_7_ * f4), (double)(f6 - p_180434_5_ * f4), (double)(f7 + p_180434_6_ * f4 - p_180434_8_ * f4)).func_181673_a(0.25D, 0.375D).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as).func_181671_a(j, k).func_181675_d();
      }
   }

   public static class SparkFX extends EntityFX {
      private int field_92049_a = 160;
      private boolean field_92054_ax;
      private boolean field_92048_ay;
      private final EffectRenderer field_92047_az;
      private float field_92050_aA;
      private float field_92051_aB;
      private float field_92052_aC;
      private boolean field_92053_aD;

      public SparkFX(World p_i46465_1_, double p_i46465_2_, double p_i46465_4_, double p_i46465_6_, double p_i46465_8_, double p_i46465_10_, double p_i46465_12_, EffectRenderer p_i46465_14_) {
         super(p_i46465_1_, p_i46465_2_, p_i46465_4_, p_i46465_6_);
         this.field_70159_w = p_i46465_8_;
         this.field_70181_x = p_i46465_10_;
         this.field_70179_y = p_i46465_12_;
         this.field_92047_az = p_i46465_14_;
         this.field_70544_f *= 0.75F;
         this.field_70547_e = 48 + this.field_70146_Z.nextInt(12);
         this.field_70145_X = false;
      }

      public void func_92045_e(boolean p_92045_1_) {
         this.field_92054_ax = p_92045_1_;
      }

      public void func_92043_f(boolean p_92043_1_) {
         this.field_92048_ay = p_92043_1_;
      }

      public void func_92044_a(int p_92044_1_) {
         float f = (float)((p_92044_1_ & 16711680) >> 16) / 255.0F;
         float f1 = (float)((p_92044_1_ & '\uff00') >> 8) / 255.0F;
         float f2 = (float)((p_92044_1_ & 255) >> 0) / 255.0F;
         float f3 = 1.0F;
         this.func_70538_b(f * f3, f1 * f3, f2 * f3);
      }

      public void func_92046_g(int p_92046_1_) {
         this.field_92050_aA = (float)((p_92046_1_ & 16711680) >> 16) / 255.0F;
         this.field_92051_aB = (float)((p_92046_1_ & '\uff00') >> 8) / 255.0F;
         this.field_92052_aC = (float)((p_92046_1_ & 255) >> 0) / 255.0F;
         this.field_92053_aD = true;
      }

      public AxisAlignedBB func_70046_E() {
         return null;
      }

      public boolean func_70104_M() {
         return false;
      }

      public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
         if(!this.field_92048_ay || this.field_70546_d < this.field_70547_e / 3 || (this.field_70546_d + this.field_70547_e) / 3 % 2 == 0) {
            super.func_180434_a(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
         }

      }

      public void func_70071_h_() {
         this.field_70169_q = this.field_70165_t;
         this.field_70167_r = this.field_70163_u;
         this.field_70166_s = this.field_70161_v;
         if(this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
         }

         if(this.field_70546_d > this.field_70547_e / 2) {
            this.func_82338_g(1.0F - ((float)this.field_70546_d - (float)(this.field_70547_e / 2)) / (float)this.field_70547_e);
            if(this.field_92053_aD) {
               this.field_70552_h += (this.field_92050_aA - this.field_70552_h) * 0.2F;
               this.field_70553_i += (this.field_92051_aB - this.field_70553_i) * 0.2F;
               this.field_70551_j += (this.field_92052_aC - this.field_70551_j) * 0.2F;
            }
         }

         this.func_70536_a(this.field_92049_a + (7 - this.field_70546_d * 8 / this.field_70547_e));
         this.field_70181_x -= 0.004D;
         this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
         this.field_70159_w *= 0.9100000262260437D;
         this.field_70181_x *= 0.9100000262260437D;
         this.field_70179_y *= 0.9100000262260437D;
         if(this.field_70122_E) {
            this.field_70159_w *= 0.699999988079071D;
            this.field_70179_y *= 0.699999988079071D;
         }

         if(this.field_92054_ax && this.field_70546_d < this.field_70547_e / 2 && (this.field_70546_d + this.field_70547_e) % 2 == 0) {
            EntityFirework.SparkFX entityfirework$sparkfx = new EntityFirework.SparkFX(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0D, 0.0D, 0.0D, this.field_92047_az);
            entityfirework$sparkfx.func_82338_g(0.99F);
            entityfirework$sparkfx.func_70538_b(this.field_70552_h, this.field_70553_i, this.field_70551_j);
            entityfirework$sparkfx.field_70546_d = entityfirework$sparkfx.field_70547_e / 2;
            if(this.field_92053_aD) {
               entityfirework$sparkfx.field_92053_aD = true;
               entityfirework$sparkfx.field_92050_aA = this.field_92050_aA;
               entityfirework$sparkfx.field_92051_aB = this.field_92051_aB;
               entityfirework$sparkfx.field_92052_aC = this.field_92052_aC;
            }

            entityfirework$sparkfx.field_92048_ay = this.field_92048_ay;
            this.field_92047_az.func_78873_a(entityfirework$sparkfx);
         }

      }

      public int func_70070_b(float p_70070_1_) {
         return 15728880;
      }

      public float func_70013_c(float p_70013_1_) {
         return 1.0F;
      }
   }

   public static class StarterFX extends EntityFX {
      private int field_92042_ax;
      private final EffectRenderer field_92040_ay;
      private NBTTagList field_92039_az;
      boolean field_92041_a;

      public StarterFX(World p_i46464_1_, double p_i46464_2_, double p_i46464_4_, double p_i46464_6_, double p_i46464_8_, double p_i46464_10_, double p_i46464_12_, EffectRenderer p_i46464_14_, NBTTagCompound p_i46464_15_) {
         super(p_i46464_1_, p_i46464_2_, p_i46464_4_, p_i46464_6_, 0.0D, 0.0D, 0.0D);
         this.field_70159_w = p_i46464_8_;
         this.field_70181_x = p_i46464_10_;
         this.field_70179_y = p_i46464_12_;
         this.field_92040_ay = p_i46464_14_;
         this.field_70547_e = 8;
         if(p_i46464_15_ != null) {
            this.field_92039_az = p_i46464_15_.func_150295_c("Explosions", 10);
            if(this.field_92039_az.func_74745_c() == 0) {
               this.field_92039_az = null;
            } else {
               this.field_70547_e = this.field_92039_az.func_74745_c() * 2 - 1;

               for(int i = 0; i < this.field_92039_az.func_74745_c(); ++i) {
                  NBTTagCompound nbttagcompound = this.field_92039_az.func_150305_b(i);
                  if(nbttagcompound.func_74767_n("Flicker")) {
                     this.field_92041_a = true;
                     this.field_70547_e += 15;
                     break;
                  }
               }
            }
         }

      }

      public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
      }

      public void func_70071_h_() {
         if(this.field_92042_ax == 0 && this.field_92039_az != null) {
            boolean flag = this.func_92037_i();
            boolean flag1 = false;
            if(this.field_92039_az.func_74745_c() >= 3) {
               flag1 = true;
            } else {
               for(int i = 0; i < this.field_92039_az.func_74745_c(); ++i) {
                  NBTTagCompound nbttagcompound = this.field_92039_az.func_150305_b(i);
                  if(nbttagcompound.func_74771_c("Type") == 1) {
                     flag1 = true;
                     break;
                  }
               }
            }

            String s1 = "fireworks." + (flag1?"largeBlast":"blast") + (flag?"_far":"");
            this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, s1, 20.0F, 0.95F + this.field_70146_Z.nextFloat() * 0.1F, true);
         }

         if(this.field_92042_ax % 2 == 0 && this.field_92039_az != null && this.field_92042_ax / 2 < this.field_92039_az.func_74745_c()) {
            int k = this.field_92042_ax / 2;
            NBTTagCompound nbttagcompound1 = this.field_92039_az.func_150305_b(k);
            int l = nbttagcompound1.func_74771_c("Type");
            boolean flag4 = nbttagcompound1.func_74767_n("Trail");
            boolean flag2 = nbttagcompound1.func_74767_n("Flicker");
            int[] aint = nbttagcompound1.func_74759_k("Colors");
            int[] aint1 = nbttagcompound1.func_74759_k("FadeColors");
            if(aint.length == 0) {
               aint = new int[]{ItemDye.field_150922_c[0]};
            }

            if(l == 1) {
               this.func_92035_a(0.5D, 4, aint, aint1, flag4, flag2);
            } else if(l == 2) {
               this.func_92038_a(0.5D, new double[][]{{0.0D, 1.0D}, {0.3455D, 0.309D}, {0.9511D, 0.309D}, {0.3795918367346939D, -0.12653061224489795D}, {0.6122448979591837D, -0.8040816326530612D}, {0.0D, -0.35918367346938773D}}, aint, aint1, flag4, flag2, false);
            } else if(l == 3) {
               this.func_92038_a(0.5D, new double[][]{{0.0D, 0.2D}, {0.2D, 0.2D}, {0.2D, 0.6D}, {0.6D, 0.6D}, {0.6D, 0.2D}, {0.2D, 0.2D}, {0.2D, 0.0D}, {0.4D, 0.0D}, {0.4D, -0.6D}, {0.2D, -0.6D}, {0.2D, -0.4D}, {0.0D, -0.4D}}, aint, aint1, flag4, flag2, true);
            } else if(l == 4) {
               this.func_92036_a(aint, aint1, flag4, flag2);
            } else {
               this.func_92035_a(0.25D, 2, aint, aint1, flag4, flag2);
            }

            int j = aint[0];
            float f = (float)((j & 16711680) >> 16) / 255.0F;
            float f1 = (float)((j & '\uff00') >> 8) / 255.0F;
            float f2 = (float)((j & 255) >> 0) / 255.0F;
            EntityFirework.OverlayFX entityfirework$overlayfx = new EntityFirework.OverlayFX(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            entityfirework$overlayfx.func_70538_b(f, f1, f2);
            this.field_92040_ay.func_78873_a(entityfirework$overlayfx);
         }

         ++this.field_92042_ax;
         if(this.field_92042_ax > this.field_70547_e) {
            if(this.field_92041_a) {
               boolean flag3 = this.func_92037_i();
               String s = "fireworks." + (flag3?"twinkle_far":"twinkle");
               this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, s, 20.0F, 0.9F + this.field_70146_Z.nextFloat() * 0.15F, true);
            }

            this.func_70106_y();
         }

      }

      private boolean func_92037_i() {
         Minecraft minecraft = Minecraft.func_71410_x();
         return minecraft == null || minecraft.func_175606_aa() == null || minecraft.func_175606_aa().func_70092_e(this.field_70165_t, this.field_70163_u, this.field_70161_v) >= 256.0D;
      }

      private void func_92034_a(double p_92034_1_, double p_92034_3_, double p_92034_5_, double p_92034_7_, double p_92034_9_, double p_92034_11_, int[] p_92034_13_, int[] p_92034_14_, boolean p_92034_15_, boolean p_92034_16_) {
         EntityFirework.SparkFX entityfirework$sparkfx = new EntityFirework.SparkFX(this.field_70170_p, p_92034_1_, p_92034_3_, p_92034_5_, p_92034_7_, p_92034_9_, p_92034_11_, this.field_92040_ay);
         entityfirework$sparkfx.func_82338_g(0.99F);
         entityfirework$sparkfx.func_92045_e(p_92034_15_);
         entityfirework$sparkfx.func_92043_f(p_92034_16_);
         int i = this.field_70146_Z.nextInt(p_92034_13_.length);
         entityfirework$sparkfx.func_92044_a(p_92034_13_[i]);
         if(p_92034_14_ != null && p_92034_14_.length > 0) {
            entityfirework$sparkfx.func_92046_g(p_92034_14_[this.field_70146_Z.nextInt(p_92034_14_.length)]);
         }

         this.field_92040_ay.func_78873_a(entityfirework$sparkfx);
      }

      private void func_92035_a(double p_92035_1_, int p_92035_3_, int[] p_92035_4_, int[] p_92035_5_, boolean p_92035_6_, boolean p_92035_7_) {
         double d0 = this.field_70165_t;
         double d1 = this.field_70163_u;
         double d2 = this.field_70161_v;

         for(int i = -p_92035_3_; i <= p_92035_3_; ++i) {
            for(int j = -p_92035_3_; j <= p_92035_3_; ++j) {
               for(int k = -p_92035_3_; k <= p_92035_3_; ++k) {
                  double d3 = (double)j + (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * 0.5D;
                  double d4 = (double)i + (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * 0.5D;
                  double d5 = (double)k + (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * 0.5D;
                  double d6 = (double)MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5) / p_92035_1_ + this.field_70146_Z.nextGaussian() * 0.05D;
                  this.func_92034_a(d0, d1, d2, d3 / d6, d4 / d6, d5 / d6, p_92035_4_, p_92035_5_, p_92035_6_, p_92035_7_);
                  if(i != -p_92035_3_ && i != p_92035_3_ && j != -p_92035_3_ && j != p_92035_3_) {
                     k += p_92035_3_ * 2 - 1;
                  }
               }
            }
         }

      }

      private void func_92038_a(double p_92038_1_, double[][] p_92038_3_, int[] p_92038_4_, int[] p_92038_5_, boolean p_92038_6_, boolean p_92038_7_, boolean p_92038_8_) {
         double d0 = p_92038_3_[0][0];
         double d1 = p_92038_3_[0][1];
         this.func_92034_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, d0 * p_92038_1_, d1 * p_92038_1_, 0.0D, p_92038_4_, p_92038_5_, p_92038_6_, p_92038_7_);
         float f = this.field_70146_Z.nextFloat() * 3.1415927F;
         double d2 = p_92038_8_?0.034D:0.34D;

         for(int i = 0; i < 3; ++i) {
            double d3 = (double)f + (double)((float)i * 3.1415927F) * d2;
            double d4 = d0;
            double d5 = d1;

            for(int j = 1; j < p_92038_3_.length; ++j) {
               double d6 = p_92038_3_[j][0];
               double d7 = p_92038_3_[j][1];

               for(double d8 = 0.25D; d8 <= 1.0D; d8 += 0.25D) {
                  double d9 = (d4 + (d6 - d4) * d8) * p_92038_1_;
                  double d10 = (d5 + (d7 - d5) * d8) * p_92038_1_;
                  double d11 = d9 * Math.sin(d3);
                  d9 = d9 * Math.cos(d3);

                  for(double d12 = -1.0D; d12 <= 1.0D; d12 += 2.0D) {
                     this.func_92034_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, d9 * d12, d10, d11 * d12, p_92038_4_, p_92038_5_, p_92038_6_, p_92038_7_);
                  }
               }

               d4 = d6;
               d5 = d7;
            }
         }

      }

      private void func_92036_a(int[] p_92036_1_, int[] p_92036_2_, boolean p_92036_3_, boolean p_92036_4_) {
         double d0 = this.field_70146_Z.nextGaussian() * 0.05D;
         double d1 = this.field_70146_Z.nextGaussian() * 0.05D;

         for(int i = 0; i < 70; ++i) {
            double d2 = this.field_70159_w * 0.5D + this.field_70146_Z.nextGaussian() * 0.15D + d0;
            double d3 = this.field_70179_y * 0.5D + this.field_70146_Z.nextGaussian() * 0.15D + d1;
            double d4 = this.field_70181_x * 0.5D + this.field_70146_Z.nextDouble() * 0.5D;
            this.func_92034_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, d2, d4, d3, p_92036_1_, p_92036_2_, p_92036_3_, p_92036_4_);
         }

      }

      public int func_70537_b() {
         return 0;
      }
   }
}

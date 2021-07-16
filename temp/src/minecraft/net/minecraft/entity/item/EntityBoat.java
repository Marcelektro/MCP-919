package net.minecraft.entity.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBoat extends Entity {
   private boolean field_70279_a;
   private double field_70276_b;
   private int field_70277_c;
   private double field_70274_d;
   private double field_70275_e;
   private double field_70272_f;
   private double field_70273_g;
   private double field_70281_h;
   private double field_70282_i;
   private double field_70280_j;
   private double field_70278_an;

   public EntityBoat(World p_i1704_1_) {
      super(p_i1704_1_);
      this.field_70279_a = true;
      this.field_70276_b = 0.07D;
      this.field_70156_m = true;
      this.func_70105_a(1.5F, 0.6F);
   }

   protected boolean func_70041_e_() {
      return false;
   }

   protected void func_70088_a() {
      this.field_70180_af.func_75682_a(17, new Integer(0));
      this.field_70180_af.func_75682_a(18, new Integer(1));
      this.field_70180_af.func_75682_a(19, new Float(0.0F));
   }

   public AxisAlignedBB func_70114_g(Entity p_70114_1_) {
      return p_70114_1_.func_174813_aQ();
   }

   public AxisAlignedBB func_70046_E() {
      return this.func_174813_aQ();
   }

   public boolean func_70104_M() {
      return true;
   }

   public EntityBoat(World p_i1705_1_, double p_i1705_2_, double p_i1705_4_, double p_i1705_6_) {
      this(p_i1705_1_);
      this.func_70107_b(p_i1705_2_, p_i1705_4_, p_i1705_6_);
      this.field_70159_w = 0.0D;
      this.field_70181_x = 0.0D;
      this.field_70179_y = 0.0D;
      this.field_70169_q = p_i1705_2_;
      this.field_70167_r = p_i1705_4_;
      this.field_70166_s = p_i1705_6_;
   }

   public double func_70042_X() {
      return -0.3D;
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if(this.func_180431_b(p_70097_1_)) {
         return false;
      } else if(!this.field_70170_p.field_72995_K && !this.field_70128_L) {
         if(this.field_70153_n != null && this.field_70153_n == p_70097_1_.func_76346_g() && p_70097_1_ instanceof EntityDamageSourceIndirect) {
            return false;
         } else {
            this.func_70269_c(-this.func_70267_i());
            this.func_70265_b(10);
            this.func_70266_a(this.func_70271_g() + p_70097_2_ * 10.0F);
            this.func_70018_K();
            boolean flag = p_70097_1_.func_76346_g() instanceof EntityPlayer && ((EntityPlayer)p_70097_1_.func_76346_g()).field_71075_bZ.field_75098_d;
            if(flag || this.func_70271_g() > 40.0F) {
               if(this.field_70153_n != null) {
                  this.field_70153_n.func_70078_a(this);
               }

               if(!flag && this.field_70170_p.func_82736_K().func_82766_b("doEntityDrops")) {
                  this.func_145778_a(Items.field_151124_az, 1, 0.0F);
               }

               this.func_70106_y();
            }

            return true;
         }
      } else {
         return true;
      }
   }

   public void func_70057_ab() {
      this.func_70269_c(-this.func_70267_i());
      this.func_70265_b(10);
      this.func_70266_a(this.func_70271_g() * 11.0F);
   }

   public boolean func_70067_L() {
      return !this.field_70128_L;
   }

   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
      if(p_180426_10_ && this.field_70153_n != null) {
         this.field_70169_q = this.field_70165_t = p_180426_1_;
         this.field_70167_r = this.field_70163_u = p_180426_3_;
         this.field_70166_s = this.field_70161_v = p_180426_5_;
         this.field_70177_z = p_180426_7_;
         this.field_70125_A = p_180426_8_;
         this.field_70277_c = 0;
         this.func_70107_b(p_180426_1_, p_180426_3_, p_180426_5_);
         this.field_70159_w = this.field_70282_i = 0.0D;
         this.field_70181_x = this.field_70280_j = 0.0D;
         this.field_70179_y = this.field_70278_an = 0.0D;
      } else {
         if(this.field_70279_a) {
            this.field_70277_c = p_180426_9_ + 5;
         } else {
            double d0 = p_180426_1_ - this.field_70165_t;
            double d1 = p_180426_3_ - this.field_70163_u;
            double d2 = p_180426_5_ - this.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if(d3 <= 1.0D) {
               return;
            }

            this.field_70277_c = 3;
         }

         this.field_70274_d = p_180426_1_;
         this.field_70275_e = p_180426_3_;
         this.field_70272_f = p_180426_5_;
         this.field_70273_g = (double)p_180426_7_;
         this.field_70281_h = (double)p_180426_8_;
         this.field_70159_w = this.field_70282_i;
         this.field_70181_x = this.field_70280_j;
         this.field_70179_y = this.field_70278_an;
      }
   }

   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.field_70282_i = this.field_70159_w = p_70016_1_;
      this.field_70280_j = this.field_70181_x = p_70016_3_;
      this.field_70278_an = this.field_70179_y = p_70016_5_;
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if(this.func_70268_h() > 0) {
         this.func_70265_b(this.func_70268_h() - 1);
      }

      if(this.func_70271_g() > 0.0F) {
         this.func_70266_a(this.func_70271_g() - 1.0F);
      }

      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      int i = 5;
      double d0 = 0.0D;

      for(int j = 0; j < i; ++j) {
         double d1 = this.func_174813_aQ().field_72338_b + (this.func_174813_aQ().field_72337_e - this.func_174813_aQ().field_72338_b) * (double)(j + 0) / (double)i - 0.125D;
         double d3 = this.func_174813_aQ().field_72338_b + (this.func_174813_aQ().field_72337_e - this.func_174813_aQ().field_72338_b) * (double)(j + 1) / (double)i - 0.125D;
         AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.func_174813_aQ().field_72340_a, d1, this.func_174813_aQ().field_72339_c, this.func_174813_aQ().field_72336_d, d3, this.func_174813_aQ().field_72334_f);
         if(this.field_70170_p.func_72830_b(axisalignedbb, Material.field_151586_h)) {
            d0 += 1.0D / (double)i;
         }
      }

      double d9 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      if(d9 > 0.2975D) {
         double d2 = Math.cos((double)this.field_70177_z * 3.141592653589793D / 180.0D);
         double d4 = Math.sin((double)this.field_70177_z * 3.141592653589793D / 180.0D);

         for(int k = 0; (double)k < 1.0D + d9 * 60.0D; ++k) {
            double d5 = (double)(this.field_70146_Z.nextFloat() * 2.0F - 1.0F);
            double d6 = (double)(this.field_70146_Z.nextInt(2) * 2 - 1) * 0.7D;
            if(this.field_70146_Z.nextBoolean()) {
               double d7 = this.field_70165_t - d2 * d5 * 0.8D + d4 * d6;
               double d8 = this.field_70161_v - d4 * d5 * 0.8D - d2 * d6;
               this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_SPLASH, d7, this.field_70163_u - 0.125D, d8, this.field_70159_w, this.field_70181_x, this.field_70179_y, new int[0]);
            } else {
               double d24 = this.field_70165_t + d2 + d4 * d5 * 0.7D;
               double d25 = this.field_70161_v + d4 - d2 * d5 * 0.7D;
               this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_SPLASH, d24, this.field_70163_u - 0.125D, d25, this.field_70159_w, this.field_70181_x, this.field_70179_y, new int[0]);
            }
         }
      }

      if(this.field_70170_p.field_72995_K && this.field_70279_a) {
         if(this.field_70277_c > 0) {
            double d12 = this.field_70165_t + (this.field_70274_d - this.field_70165_t) / (double)this.field_70277_c;
            double d16 = this.field_70163_u + (this.field_70275_e - this.field_70163_u) / (double)this.field_70277_c;
            double d19 = this.field_70161_v + (this.field_70272_f - this.field_70161_v) / (double)this.field_70277_c;
            double d22 = MathHelper.func_76138_g(this.field_70273_g - (double)this.field_70177_z);
            this.field_70177_z = (float)((double)this.field_70177_z + d22 / (double)this.field_70277_c);
            this.field_70125_A = (float)((double)this.field_70125_A + (this.field_70281_h - (double)this.field_70125_A) / (double)this.field_70277_c);
            --this.field_70277_c;
            this.func_70107_b(d12, d16, d19);
            this.func_70101_b(this.field_70177_z, this.field_70125_A);
         } else {
            double d13 = this.field_70165_t + this.field_70159_w;
            double d17 = this.field_70163_u + this.field_70181_x;
            double d20 = this.field_70161_v + this.field_70179_y;
            this.func_70107_b(d13, d17, d20);
            if(this.field_70122_E) {
               this.field_70159_w *= 0.5D;
               this.field_70181_x *= 0.5D;
               this.field_70179_y *= 0.5D;
            }

            this.field_70159_w *= 0.9900000095367432D;
            this.field_70181_x *= 0.949999988079071D;
            this.field_70179_y *= 0.9900000095367432D;
         }

      } else {
         if(d0 < 1.0D) {
            double d10 = d0 * 2.0D - 1.0D;
            this.field_70181_x += 0.03999999910593033D * d10;
         } else {
            if(this.field_70181_x < 0.0D) {
               this.field_70181_x /= 2.0D;
            }

            this.field_70181_x += 0.007000000216066837D;
         }

         if(this.field_70153_n instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase)this.field_70153_n;
            float f = this.field_70153_n.field_70177_z + -entitylivingbase.field_70702_br * 90.0F;
            this.field_70159_w += -Math.sin((double)(f * 3.1415927F / 180.0F)) * this.field_70276_b * (double)entitylivingbase.field_70701_bs * 0.05000000074505806D;
            this.field_70179_y += Math.cos((double)(f * 3.1415927F / 180.0F)) * this.field_70276_b * (double)entitylivingbase.field_70701_bs * 0.05000000074505806D;
         }

         double d11 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
         if(d11 > 0.35D) {
            double d14 = 0.35D / d11;
            this.field_70159_w *= d14;
            this.field_70179_y *= d14;
            d11 = 0.35D;
         }

         if(d11 > d9 && this.field_70276_b < 0.35D) {
            this.field_70276_b += (0.35D - this.field_70276_b) / 35.0D;
            if(this.field_70276_b > 0.35D) {
               this.field_70276_b = 0.35D;
            }
         } else {
            this.field_70276_b -= (this.field_70276_b - 0.07D) / 35.0D;
            if(this.field_70276_b < 0.07D) {
               this.field_70276_b = 0.07D;
            }
         }

         for(int i1 = 0; i1 < 4; ++i1) {
            int l1 = MathHelper.func_76128_c(this.field_70165_t + ((double)(i1 % 2) - 0.5D) * 0.8D);
            int i2 = MathHelper.func_76128_c(this.field_70161_v + ((double)(i1 / 2) - 0.5D) * 0.8D);

            for(int j2 = 0; j2 < 2; ++j2) {
               int l = MathHelper.func_76128_c(this.field_70163_u) + j2;
               BlockPos blockpos = new BlockPos(l1, l, i2);
               Block block = this.field_70170_p.func_180495_p(blockpos).func_177230_c();
               if(block == Blocks.field_150431_aC) {
                  this.field_70170_p.func_175698_g(blockpos);
                  this.field_70123_F = false;
               } else if(block == Blocks.field_150392_bi) {
                  this.field_70170_p.func_175655_b(blockpos, true);
                  this.field_70123_F = false;
               }
            }
         }

         if(this.field_70122_E) {
            this.field_70159_w *= 0.5D;
            this.field_70181_x *= 0.5D;
            this.field_70179_y *= 0.5D;
         }

         this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
         if(this.field_70123_F && d9 > 0.2975D) {
            if(!this.field_70170_p.field_72995_K && !this.field_70128_L) {
               this.func_70106_y();
               if(this.field_70170_p.func_82736_K().func_82766_b("doEntityDrops")) {
                  for(int j1 = 0; j1 < 3; ++j1) {
                     this.func_145778_a(Item.func_150898_a(Blocks.field_150344_f), 1, 0.0F);
                  }

                  for(int k1 = 0; k1 < 2; ++k1) {
                     this.func_145778_a(Items.field_151055_y, 1, 0.0F);
                  }
               }
            }
         } else {
            this.field_70159_w *= 0.9900000095367432D;
            this.field_70181_x *= 0.949999988079071D;
            this.field_70179_y *= 0.9900000095367432D;
         }

         this.field_70125_A = 0.0F;
         double d15 = (double)this.field_70177_z;
         double d18 = this.field_70169_q - this.field_70165_t;
         double d21 = this.field_70166_s - this.field_70161_v;
         if(d18 * d18 + d21 * d21 > 0.001D) {
            d15 = (double)((float)(MathHelper.func_181159_b(d21, d18) * 180.0D / 3.141592653589793D));
         }

         double d23 = MathHelper.func_76138_g(d15 - (double)this.field_70177_z);
         if(d23 > 20.0D) {
            d23 = 20.0D;
         }

         if(d23 < -20.0D) {
            d23 = -20.0D;
         }

         this.field_70177_z = (float)((double)this.field_70177_z + d23);
         this.func_70101_b(this.field_70177_z, this.field_70125_A);
         if(!this.field_70170_p.field_72995_K) {
            List<Entity> list = this.field_70170_p.func_72839_b(this, this.func_174813_aQ().func_72314_b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
            if(list != null && !list.isEmpty()) {
               for(int k2 = 0; k2 < list.size(); ++k2) {
                  Entity entity = (Entity)list.get(k2);
                  if(entity != this.field_70153_n && entity.func_70104_M() && entity instanceof EntityBoat) {
                     entity.func_70108_f(this);
                  }
               }
            }

            if(this.field_70153_n != null && this.field_70153_n.field_70128_L) {
               this.field_70153_n = null;
            }

         }
      }
   }

   public void func_70043_V() {
      if(this.field_70153_n != null) {
         double d0 = Math.cos((double)this.field_70177_z * 3.141592653589793D / 180.0D) * 0.4D;
         double d1 = Math.sin((double)this.field_70177_z * 3.141592653589793D / 180.0D) * 0.4D;
         this.field_70153_n.func_70107_b(this.field_70165_t + d0, this.field_70163_u + this.func_70042_X() + this.field_70153_n.func_70033_W(), this.field_70161_v + d1);
      }
   }

   protected void func_70014_b(NBTTagCompound p_70014_1_) {
   }

   protected void func_70037_a(NBTTagCompound p_70037_1_) {
   }

   public boolean func_130002_c(EntityPlayer p_130002_1_) {
      if(this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer && this.field_70153_n != p_130002_1_) {
         return true;
      } else {
         if(!this.field_70170_p.field_72995_K) {
            p_130002_1_.func_70078_a(this);
         }

         return true;
      }
   }

   protected void func_180433_a(double p_180433_1_, boolean p_180433_3_, Block p_180433_4_, BlockPos p_180433_5_) {
      if(p_180433_3_) {
         if(this.field_70143_R > 3.0F) {
            this.func_180430_e(this.field_70143_R, 1.0F);
            if(!this.field_70170_p.field_72995_K && !this.field_70128_L) {
               this.func_70106_y();
               if(this.field_70170_p.func_82736_K().func_82766_b("doEntityDrops")) {
                  for(int i = 0; i < 3; ++i) {
                     this.func_145778_a(Item.func_150898_a(Blocks.field_150344_f), 1, 0.0F);
                  }

                  for(int j = 0; j < 2; ++j) {
                     this.func_145778_a(Items.field_151055_y, 1, 0.0F);
                  }
               }
            }

            this.field_70143_R = 0.0F;
         }
      } else if(this.field_70170_p.func_180495_p((new BlockPos(this)).func_177977_b()).func_177230_c().func_149688_o() != Material.field_151586_h && p_180433_1_ < 0.0D) {
         this.field_70143_R = (float)((double)this.field_70143_R - p_180433_1_);
      }

   }

   public void func_70266_a(float p_70266_1_) {
      this.field_70180_af.func_75692_b(19, Float.valueOf(p_70266_1_));
   }

   public float func_70271_g() {
      return this.field_70180_af.func_111145_d(19);
   }

   public void func_70265_b(int p_70265_1_) {
      this.field_70180_af.func_75692_b(17, Integer.valueOf(p_70265_1_));
   }

   public int func_70268_h() {
      return this.field_70180_af.func_75679_c(17);
   }

   public void func_70269_c(int p_70269_1_) {
      this.field_70180_af.func_75692_b(18, Integer.valueOf(p_70269_1_));
   }

   public int func_70267_i() {
      return this.field_70180_af.func_75679_c(18);
   }

   public void func_70270_d(boolean p_70270_1_) {
      this.field_70279_a = p_70270_1_;
   }
}

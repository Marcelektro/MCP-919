package net.minecraft.client.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityOtherPlayerMP extends AbstractClientPlayer {
   private boolean field_71186_a;
   private int field_71184_b;
   private double field_71185_c;
   private double field_71182_d;
   private double field_71183_e;
   private double field_71180_f;
   private double field_71181_g;

   public EntityOtherPlayerMP(World p_i45075_1_, GameProfile p_i45075_2_) {
      super(p_i45075_1_, p_i45075_2_);
      this.field_70138_W = 0.0F;
      this.field_70145_X = true;
      this.field_71082_cx = 0.25F;
      this.field_70155_l = 10.0D;
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      return true;
   }

   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
      this.field_71185_c = p_180426_1_;
      this.field_71182_d = p_180426_3_;
      this.field_71183_e = p_180426_5_;
      this.field_71180_f = (double)p_180426_7_;
      this.field_71181_g = (double)p_180426_8_;
      this.field_71184_b = p_180426_9_;
   }

   public void func_70071_h_() {
      this.field_71082_cx = 0.0F;
      super.func_70071_h_();
      this.field_70722_aY = this.field_70721_aZ;
      double d0 = this.field_70165_t - this.field_70169_q;
      double d1 = this.field_70161_v - this.field_70166_s;
      float f = MathHelper.func_76133_a(d0 * d0 + d1 * d1) * 4.0F;
      if(f > 1.0F) {
         f = 1.0F;
      }

      this.field_70721_aZ += (f - this.field_70721_aZ) * 0.4F;
      this.field_70754_ba += this.field_70721_aZ;
      if(!this.field_71186_a && this.func_70113_ah() && this.field_71071_by.field_70462_a[this.field_71071_by.field_70461_c] != null) {
         ItemStack itemstack = this.field_71071_by.field_70462_a[this.field_71071_by.field_70461_c];
         this.func_71008_a(this.field_71071_by.field_70462_a[this.field_71071_by.field_70461_c], itemstack.func_77973_b().func_77626_a(itemstack));
         this.field_71186_a = true;
      } else if(this.field_71186_a && !this.func_70113_ah()) {
         this.func_71041_bz();
         this.field_71186_a = false;
      }

   }

   public void func_70636_d() {
      if(this.field_71184_b > 0) {
         double d0 = this.field_70165_t + (this.field_71185_c - this.field_70165_t) / (double)this.field_71184_b;
         double d1 = this.field_70163_u + (this.field_71182_d - this.field_70163_u) / (double)this.field_71184_b;
         double d2 = this.field_70161_v + (this.field_71183_e - this.field_70161_v) / (double)this.field_71184_b;

         double d3;
         for(d3 = this.field_71180_f - (double)this.field_70177_z; d3 < -180.0D; d3 += 360.0D) {
            ;
         }

         while(d3 >= 180.0D) {
            d3 -= 360.0D;
         }

         this.field_70177_z = (float)((double)this.field_70177_z + d3 / (double)this.field_71184_b);
         this.field_70125_A = (float)((double)this.field_70125_A + (this.field_71181_g - (double)this.field_70125_A) / (double)this.field_71184_b);
         --this.field_71184_b;
         this.func_70107_b(d0, d1, d2);
         this.func_70101_b(this.field_70177_z, this.field_70125_A);
      }

      this.field_71107_bF = this.field_71109_bG;
      this.func_82168_bl();
      float f1 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      float f = (float)Math.atan(-this.field_70181_x * 0.20000000298023224D) * 15.0F;
      if(f1 > 0.1F) {
         f1 = 0.1F;
      }

      if(!this.field_70122_E || this.func_110143_aJ() <= 0.0F) {
         f1 = 0.0F;
      }

      if(this.field_70122_E || this.func_110143_aJ() <= 0.0F) {
         f = 0.0F;
      }

      this.field_71109_bG += (f1 - this.field_71109_bG) * 0.4F;
      this.field_70726_aT += (f - this.field_70726_aT) * 0.8F;
   }

   public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_) {
      if(p_70062_1_ == 0) {
         this.field_71071_by.field_70462_a[this.field_71071_by.field_70461_c] = p_70062_2_;
      } else {
         this.field_71071_by.field_70460_b[p_70062_1_ - 1] = p_70062_2_;
      }

   }

   public void func_145747_a(IChatComponent p_145747_1_) {
      Minecraft.func_71410_x().field_71456_v.func_146158_b().func_146227_a(p_145747_1_);
   }

   public boolean func_70003_b(int p_70003_1_, String p_70003_2_) {
      return false;
   }

   public BlockPos func_180425_c() {
      return new BlockPos(this.field_70165_t + 0.5D, this.field_70163_u + 0.5D, this.field_70161_v + 0.5D);
   }
}

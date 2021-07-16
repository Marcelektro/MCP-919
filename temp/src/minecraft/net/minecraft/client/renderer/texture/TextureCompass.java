package net.minecraft.client.renderer.texture;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TextureCompass extends TextureAtlasSprite {
   public double field_94244_i;
   public double field_94242_j;
   public static String field_176608_l;

   public TextureCompass(String p_i1286_1_) {
      super(p_i1286_1_);
      field_176608_l = p_i1286_1_;
   }

   public void func_94219_l() {
      Minecraft minecraft = Minecraft.func_71410_x();
      if(minecraft.field_71441_e != null && minecraft.field_71439_g != null) {
         this.func_94241_a(minecraft.field_71441_e, minecraft.field_71439_g.field_70165_t, minecraft.field_71439_g.field_70161_v, (double)minecraft.field_71439_g.field_70177_z, false, false);
      } else {
         this.func_94241_a((World)null, 0.0D, 0.0D, 0.0D, true, false);
      }

   }

   public void func_94241_a(World p_94241_1_, double p_94241_2_, double p_94241_4_, double p_94241_6_, boolean p_94241_8_, boolean p_94241_9_) {
      if(!this.field_110976_a.isEmpty()) {
         double d0 = 0.0D;
         if(p_94241_1_ != null && !p_94241_8_) {
            BlockPos blockpos = p_94241_1_.func_175694_M();
            double d1 = (double)blockpos.func_177958_n() - p_94241_2_;
            double d2 = (double)blockpos.func_177952_p() - p_94241_4_;
            p_94241_6_ = p_94241_6_ % 360.0D;
            d0 = -((p_94241_6_ - 90.0D) * 3.141592653589793D / 180.0D - Math.atan2(d2, d1));
            if(!p_94241_1_.field_73011_w.func_76569_d()) {
               d0 = Math.random() * 3.1415927410125732D * 2.0D;
            }
         }

         if(p_94241_9_) {
            this.field_94244_i = d0;
         } else {
            double d3;
            for(d3 = d0 - this.field_94244_i; d3 < -3.141592653589793D; d3 += 6.283185307179586D) {
               ;
            }

            while(d3 >= 3.141592653589793D) {
               d3 -= 6.283185307179586D;
            }

            d3 = MathHelper.func_151237_a(d3, -1.0D, 1.0D);
            this.field_94242_j += d3 * 0.1D;
            this.field_94242_j *= 0.8D;
            this.field_94244_i += this.field_94242_j;
         }

         int i;
         for(i = (int)((this.field_94244_i / 6.283185307179586D + 1.0D) * (double)this.field_110976_a.size()) % this.field_110976_a.size(); i < 0; i = (i + this.field_110976_a.size()) % this.field_110976_a.size()) {
            ;
         }

         if(i != this.field_110973_g) {
            this.field_110973_g = i;
            TextureUtil.func_147955_a((int[][])this.field_110976_a.get(this.field_110973_g), this.field_130223_c, this.field_130224_d, this.field_110975_c, this.field_110974_d, false, false);
         }

      }
   }
}

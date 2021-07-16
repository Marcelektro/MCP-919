package net.minecraft.client.renderer.texture;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.MathHelper;

public class TextureClock extends TextureAtlasSprite {
   private double field_94239_h;
   private double field_94240_i;

   public TextureClock(String p_i1285_1_) {
      super(p_i1285_1_);
   }

   public void func_94219_l() {
      if(!this.field_110976_a.isEmpty()) {
         Minecraft minecraft = Minecraft.func_71410_x();
         double d0 = 0.0D;
         if(minecraft.field_71441_e != null && minecraft.field_71439_g != null) {
            d0 = (double)minecraft.field_71441_e.func_72826_c(1.0F);
            if(!minecraft.field_71441_e.field_73011_w.func_76569_d()) {
               d0 = Math.random();
            }
         }

         double d1;
         for(d1 = d0 - this.field_94239_h; d1 < -0.5D; ++d1) {
            ;
         }

         while(d1 >= 0.5D) {
            --d1;
         }

         d1 = MathHelper.func_151237_a(d1, -1.0D, 1.0D);
         this.field_94240_i += d1 * 0.1D;
         this.field_94240_i *= 0.8D;
         this.field_94239_h += this.field_94240_i;

         int i;
         for(i = (int)((this.field_94239_h + 1.0D) * (double)this.field_110976_a.size()) % this.field_110976_a.size(); i < 0; i = (i + this.field_110976_a.size()) % this.field_110976_a.size()) {
            ;
         }

         if(i != this.field_110973_g) {
            this.field_110973_g = i;
            TextureUtil.func_147955_a((int[][])this.field_110976_a.get(this.field_110973_g), this.field_130223_c, this.field_130224_d, this.field_110975_c, this.field_110974_d, false, false);
         }

      }
   }
}

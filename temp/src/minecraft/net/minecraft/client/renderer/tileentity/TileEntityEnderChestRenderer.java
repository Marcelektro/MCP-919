package net.minecraft.client.renderer.tileentity;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.ResourceLocation;

public class TileEntityEnderChestRenderer extends TileEntitySpecialRenderer<TileEntityEnderChest> {
   private static final ResourceLocation field_147520_b = new ResourceLocation("textures/entity/chest/ender.png");
   private ModelChest field_147521_c = new ModelChest();

   public void func_180535_a(TileEntityEnderChest p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
      int i = 0;
      if(p_180535_1_.func_145830_o()) {
         i = p_180535_1_.func_145832_p();
      }

      if(p_180535_9_ >= 0) {
         this.func_147499_a(field_178460_a[p_180535_9_]);
         GlStateManager.func_179128_n(5890);
         GlStateManager.func_179094_E();
         GlStateManager.func_179152_a(4.0F, 4.0F, 1.0F);
         GlStateManager.func_179109_b(0.0625F, 0.0625F, 0.0625F);
         GlStateManager.func_179128_n(5888);
      } else {
         this.func_147499_a(field_147520_b);
      }

      GlStateManager.func_179094_E();
      GlStateManager.func_179091_B();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179109_b((float)p_180535_2_, (float)p_180535_4_ + 1.0F, (float)p_180535_6_ + 1.0F);
      GlStateManager.func_179152_a(1.0F, -1.0F, -1.0F);
      GlStateManager.func_179109_b(0.5F, 0.5F, 0.5F);
      int j = 0;
      if(i == 2) {
         j = 180;
      }

      if(i == 3) {
         j = 0;
      }

      if(i == 4) {
         j = 90;
      }

      if(i == 5) {
         j = -90;
      }

      GlStateManager.func_179114_b((float)j, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179109_b(-0.5F, -0.5F, -0.5F);
      float f = p_180535_1_.field_145975_i + (p_180535_1_.field_145972_a - p_180535_1_.field_145975_i) * p_180535_8_;
      f = 1.0F - f;
      f = 1.0F - f * f * f;
      this.field_147521_c.field_78234_a.field_78795_f = -(f * 3.1415927F / 2.0F);
      this.field_147521_c.func_78231_a();
      GlStateManager.func_179101_C();
      GlStateManager.func_179121_F();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      if(p_180535_9_ >= 0) {
         GlStateManager.func_179128_n(5890);
         GlStateManager.func_179121_F();
         GlStateManager.func_179128_n(5888);
      }

   }
}

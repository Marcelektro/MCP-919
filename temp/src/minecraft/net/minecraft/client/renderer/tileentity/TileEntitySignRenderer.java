package net.minecraft.client.renderer.tileentity;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntitySignRenderer extends TileEntitySpecialRenderer<TileEntitySign> {
   private static final ResourceLocation field_147513_b = new ResourceLocation("textures/entity/sign.png");
   private final ModelSign field_147514_c = new ModelSign();

   public void func_180535_a(TileEntitySign p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
      Block block = p_180535_1_.func_145838_q();
      GlStateManager.func_179094_E();
      float f = 0.6666667F;
      if(block == Blocks.field_150472_an) {
         GlStateManager.func_179109_b((float)p_180535_2_ + 0.5F, (float)p_180535_4_ + 0.75F * f, (float)p_180535_6_ + 0.5F);
         float f1 = (float)(p_180535_1_.func_145832_p() * 360) / 16.0F;
         GlStateManager.func_179114_b(-f1, 0.0F, 1.0F, 0.0F);
         this.field_147514_c.field_78165_b.field_78806_j = true;
      } else {
         int k = p_180535_1_.func_145832_p();
         float f2 = 0.0F;
         if(k == 2) {
            f2 = 180.0F;
         }

         if(k == 4) {
            f2 = 90.0F;
         }

         if(k == 5) {
            f2 = -90.0F;
         }

         GlStateManager.func_179109_b((float)p_180535_2_ + 0.5F, (float)p_180535_4_ + 0.75F * f, (float)p_180535_6_ + 0.5F);
         GlStateManager.func_179114_b(-f2, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179109_b(0.0F, -0.3125F, -0.4375F);
         this.field_147514_c.field_78165_b.field_78806_j = false;
      }

      if(p_180535_9_ >= 0) {
         this.func_147499_a(field_178460_a[p_180535_9_]);
         GlStateManager.func_179128_n(5890);
         GlStateManager.func_179094_E();
         GlStateManager.func_179152_a(4.0F, 2.0F, 1.0F);
         GlStateManager.func_179109_b(0.0625F, 0.0625F, 0.0625F);
         GlStateManager.func_179128_n(5888);
      } else {
         this.func_147499_a(field_147513_b);
      }

      GlStateManager.func_179091_B();
      GlStateManager.func_179094_E();
      GlStateManager.func_179152_a(f, -f, -f);
      this.field_147514_c.func_78164_a();
      GlStateManager.func_179121_F();
      FontRenderer fontrenderer = this.func_147498_b();
      float f3 = 0.015625F * f;
      GlStateManager.func_179109_b(0.0F, 0.5F * f, 0.07F * f);
      GlStateManager.func_179152_a(f3, -f3, f3);
      GL11.glNormal3f(0.0F, 0.0F, -1.0F * f3);
      GlStateManager.func_179132_a(false);
      int i = 0;
      if(p_180535_9_ < 0) {
         for(int j = 0; j < p_180535_1_.field_145915_a.length; ++j) {
            if(p_180535_1_.field_145915_a[j] != null) {
               IChatComponent ichatcomponent = p_180535_1_.field_145915_a[j];
               List<IChatComponent> list = GuiUtilRenderComponents.func_178908_a(ichatcomponent, 90, fontrenderer, false, true);
               String s = list != null && list.size() > 0?((IChatComponent)list.get(0)).func_150254_d():"";
               if(j == p_180535_1_.field_145918_i) {
                  s = "> " + s + " <";
                  fontrenderer.func_78276_b(s, -fontrenderer.func_78256_a(s) / 2, j * 10 - p_180535_1_.field_145915_a.length * 5, i);
               } else {
                  fontrenderer.func_78276_b(s, -fontrenderer.func_78256_a(s) / 2, j * 10 - p_180535_1_.field_145915_a.length * 5, i);
               }
            }
         }
      }

      GlStateManager.func_179132_a(true);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179121_F();
      if(p_180535_9_ >= 0) {
         GlStateManager.func_179128_n(5890);
         GlStateManager.func_179121_F();
         GlStateManager.func_179128_n(5888);
      }

   }
}

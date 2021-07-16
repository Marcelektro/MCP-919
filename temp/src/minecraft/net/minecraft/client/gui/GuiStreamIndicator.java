package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class GuiStreamIndicator {
   private static final ResourceLocation field_152441_a = new ResourceLocation("textures/gui/stream_indicator.png");
   private final Minecraft field_152442_b;
   private float field_152443_c = 1.0F;
   private int field_152444_d = 1;

   public GuiStreamIndicator(Minecraft p_i46322_1_) {
      this.field_152442_b = p_i46322_1_;
   }

   public void func_152437_a(int p_152437_1_, int p_152437_2_) {
      if(this.field_152442_b.func_152346_Z().func_152934_n()) {
         GlStateManager.func_179147_l();
         int i = this.field_152442_b.func_152346_Z().func_152920_A();
         if(i > 0) {
            String s = "" + i;
            int j = this.field_152442_b.field_71466_p.func_78256_a(s);
            int k = 20;
            int l = p_152437_1_ - j - 1;
            int i1 = p_152437_2_ + 20 - 1;
            int j1 = p_152437_2_ + 20 + this.field_152442_b.field_71466_p.field_78288_b - 1;
            GlStateManager.func_179090_x();
            Tessellator tessellator = Tessellator.func_178181_a();
            WorldRenderer worldrenderer = tessellator.func_178180_c();
            GlStateManager.func_179131_c(0.0F, 0.0F, 0.0F, (0.65F + 0.35000002F * this.field_152443_c) / 2.0F);
            worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
            worldrenderer.func_181662_b((double)l, (double)j1, 0.0D).func_181675_d();
            worldrenderer.func_181662_b((double)p_152437_1_, (double)j1, 0.0D).func_181675_d();
            worldrenderer.func_181662_b((double)p_152437_1_, (double)i1, 0.0D).func_181675_d();
            worldrenderer.func_181662_b((double)l, (double)i1, 0.0D).func_181675_d();
            tessellator.func_78381_a();
            GlStateManager.func_179098_w();
            this.field_152442_b.field_71466_p.func_78276_b(s, p_152437_1_ - j, p_152437_2_ + 20, 16777215);
         }

         this.func_152436_a(p_152437_1_, p_152437_2_, this.func_152440_b(), 0);
         this.func_152436_a(p_152437_1_, p_152437_2_, this.func_152438_c(), 17);
      }

   }

   private void func_152436_a(int p_152436_1_, int p_152436_2_, int p_152436_3_, int p_152436_4_) {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.65F + 0.35000002F * this.field_152443_c);
      this.field_152442_b.func_110434_K().func_110577_a(field_152441_a);
      float f = 150.0F;
      float f1 = 0.0F;
      float f2 = (float)p_152436_3_ * 0.015625F;
      float f3 = 1.0F;
      float f4 = (float)(p_152436_3_ + 16) * 0.015625F;
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      worldrenderer.func_181662_b((double)(p_152436_1_ - 16 - p_152436_4_), (double)(p_152436_2_ + 16), (double)f).func_181673_a((double)f1, (double)f4).func_181675_d();
      worldrenderer.func_181662_b((double)(p_152436_1_ - p_152436_4_), (double)(p_152436_2_ + 16), (double)f).func_181673_a((double)f3, (double)f4).func_181675_d();
      worldrenderer.func_181662_b((double)(p_152436_1_ - p_152436_4_), (double)(p_152436_2_ + 0), (double)f).func_181673_a((double)f3, (double)f2).func_181675_d();
      worldrenderer.func_181662_b((double)(p_152436_1_ - 16 - p_152436_4_), (double)(p_152436_2_ + 0), (double)f).func_181673_a((double)f1, (double)f2).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private int func_152440_b() {
      return this.field_152442_b.func_152346_Z().func_152919_o()?16:0;
   }

   private int func_152438_c() {
      return this.field_152442_b.func_152346_Z().func_152929_G()?48:32;
   }

   public void func_152439_a() {
      if(this.field_152442_b.func_152346_Z().func_152934_n()) {
         this.field_152443_c += 0.025F * (float)this.field_152444_d;
         if(this.field_152443_c < 0.0F) {
            this.field_152444_d *= -1;
            this.field_152443_c = 0.0F;
         } else if(this.field_152443_c > 1.0F) {
            this.field_152444_d *= -1;
            this.field_152443_c = 1.0F;
         }
      } else {
         this.field_152443_c = 1.0F;
         this.field_152444_d = 1;
      }

   }
}

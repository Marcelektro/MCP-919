package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderArrow extends Render<EntityArrow> {
   private static final ResourceLocation field_110780_a = new ResourceLocation("textures/entity/arrow.png");

   public RenderArrow(RenderManager p_i46193_1_) {
      super(p_i46193_1_);
   }

   public void func_76986_a(EntityArrow p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.func_180548_c(p_76986_1_);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
      GlStateManager.func_179114_b(p_76986_1_.field_70126_B + (p_76986_1_.field_70177_z - p_76986_1_.field_70126_B) * p_76986_9_ - 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(p_76986_1_.field_70127_C + (p_76986_1_.field_70125_A - p_76986_1_.field_70127_C) * p_76986_9_, 0.0F, 0.0F, 1.0F);
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      int i = 0;
      float f = 0.0F;
      float f1 = 0.5F;
      float f2 = (float)(0 + i * 10) / 32.0F;
      float f3 = (float)(5 + i * 10) / 32.0F;
      float f4 = 0.0F;
      float f5 = 0.15625F;
      float f6 = (float)(5 + i * 10) / 32.0F;
      float f7 = (float)(10 + i * 10) / 32.0F;
      float f8 = 0.05625F;
      GlStateManager.func_179091_B();
      float f9 = (float)p_76986_1_.field_70249_b - p_76986_9_;
      if(f9 > 0.0F) {
         float f10 = -MathHelper.func_76126_a(f9 * 3.0F) * f9;
         GlStateManager.func_179114_b(f10, 0.0F, 0.0F, 1.0F);
      }

      GlStateManager.func_179114_b(45.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179152_a(f8, f8, f8);
      GlStateManager.func_179109_b(-4.0F, 0.0F, 0.0F);
      GL11.glNormal3f(f8, 0.0F, 0.0F);
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      worldrenderer.func_181662_b(-7.0D, -2.0D, -2.0D).func_181673_a((double)f4, (double)f6).func_181675_d();
      worldrenderer.func_181662_b(-7.0D, -2.0D, 2.0D).func_181673_a((double)f5, (double)f6).func_181675_d();
      worldrenderer.func_181662_b(-7.0D, 2.0D, 2.0D).func_181673_a((double)f5, (double)f7).func_181675_d();
      worldrenderer.func_181662_b(-7.0D, 2.0D, -2.0D).func_181673_a((double)f4, (double)f7).func_181675_d();
      tessellator.func_78381_a();
      GL11.glNormal3f(-f8, 0.0F, 0.0F);
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      worldrenderer.func_181662_b(-7.0D, 2.0D, -2.0D).func_181673_a((double)f4, (double)f6).func_181675_d();
      worldrenderer.func_181662_b(-7.0D, 2.0D, 2.0D).func_181673_a((double)f5, (double)f6).func_181675_d();
      worldrenderer.func_181662_b(-7.0D, -2.0D, 2.0D).func_181673_a((double)f5, (double)f7).func_181675_d();
      worldrenderer.func_181662_b(-7.0D, -2.0D, -2.0D).func_181673_a((double)f4, (double)f7).func_181675_d();
      tessellator.func_78381_a();

      for(int j = 0; j < 4; ++j) {
         GlStateManager.func_179114_b(90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glNormal3f(0.0F, 0.0F, f8);
         worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
         worldrenderer.func_181662_b(-8.0D, -2.0D, 0.0D).func_181673_a((double)f, (double)f2).func_181675_d();
         worldrenderer.func_181662_b(8.0D, -2.0D, 0.0D).func_181673_a((double)f1, (double)f2).func_181675_d();
         worldrenderer.func_181662_b(8.0D, 2.0D, 0.0D).func_181673_a((double)f1, (double)f3).func_181675_d();
         worldrenderer.func_181662_b(-8.0D, 2.0D, 0.0D).func_181673_a((double)f, (double)f3).func_181675_d();
         tessellator.func_78381_a();
      }

      GlStateManager.func_179101_C();
      GlStateManager.func_179121_F();
      super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation func_110775_a(EntityArrow p_110775_1_) {
      return field_110780_a;
   }
}

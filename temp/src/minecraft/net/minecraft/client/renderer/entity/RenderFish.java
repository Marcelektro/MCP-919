package net.minecraft.client.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class RenderFish extends Render<EntityFishHook> {
   private static final ResourceLocation field_110792_a = new ResourceLocation("textures/particle/particles.png");

   public RenderFish(RenderManager p_i46175_1_) {
      super(p_i46175_1_);
   }

   public void func_76986_a(EntityFishHook p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
      GlStateManager.func_179091_B();
      GlStateManager.func_179152_a(0.5F, 0.5F, 0.5F);
      this.func_180548_c(p_76986_1_);
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      int i = 1;
      int j = 2;
      float f = 0.0625F;
      float f1 = 0.125F;
      float f2 = 0.125F;
      float f3 = 0.1875F;
      float f4 = 1.0F;
      float f5 = 0.5F;
      float f6 = 0.5F;
      GlStateManager.func_179114_b(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181710_j);
      worldrenderer.func_181662_b(-0.5D, -0.5D, 0.0D).func_181673_a(0.0625D, 0.1875D).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
      worldrenderer.func_181662_b(0.5D, -0.5D, 0.0D).func_181673_a(0.125D, 0.1875D).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
      worldrenderer.func_181662_b(0.5D, 0.5D, 0.0D).func_181673_a(0.125D, 0.125D).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
      worldrenderer.func_181662_b(-0.5D, 0.5D, 0.0D).func_181673_a(0.0625D, 0.125D).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179101_C();
      GlStateManager.func_179121_F();
      if(p_76986_1_.field_146042_b != null) {
         float f7 = p_76986_1_.field_146042_b.func_70678_g(p_76986_9_);
         float f8 = MathHelper.func_76126_a(MathHelper.func_76129_c(f7) * 3.1415927F);
         Vec3 vec3 = new Vec3(-0.36D, 0.03D, 0.35D);
         vec3 = vec3.func_178789_a(-(p_76986_1_.field_146042_b.field_70127_C + (p_76986_1_.field_146042_b.field_70125_A - p_76986_1_.field_146042_b.field_70127_C) * p_76986_9_) * 3.1415927F / 180.0F);
         vec3 = vec3.func_178785_b(-(p_76986_1_.field_146042_b.field_70126_B + (p_76986_1_.field_146042_b.field_70177_z - p_76986_1_.field_146042_b.field_70126_B) * p_76986_9_) * 3.1415927F / 180.0F);
         vec3 = vec3.func_178785_b(f8 * 0.5F);
         vec3 = vec3.func_178789_a(-f8 * 0.7F);
         double d0 = p_76986_1_.field_146042_b.field_70169_q + (p_76986_1_.field_146042_b.field_70165_t - p_76986_1_.field_146042_b.field_70169_q) * (double)p_76986_9_ + vec3.field_72450_a;
         double d1 = p_76986_1_.field_146042_b.field_70167_r + (p_76986_1_.field_146042_b.field_70163_u - p_76986_1_.field_146042_b.field_70167_r) * (double)p_76986_9_ + vec3.field_72448_b;
         double d2 = p_76986_1_.field_146042_b.field_70166_s + (p_76986_1_.field_146042_b.field_70161_v - p_76986_1_.field_146042_b.field_70166_s) * (double)p_76986_9_ + vec3.field_72449_c;
         double d3 = (double)p_76986_1_.field_146042_b.func_70047_e();
         if(this.field_76990_c.field_78733_k != null && this.field_76990_c.field_78733_k.field_74320_O > 0 || p_76986_1_.field_146042_b != Minecraft.func_71410_x().field_71439_g) {
            float f9 = (p_76986_1_.field_146042_b.field_70760_ar + (p_76986_1_.field_146042_b.field_70761_aq - p_76986_1_.field_146042_b.field_70760_ar) * p_76986_9_) * 3.1415927F / 180.0F;
            double d4 = (double)MathHelper.func_76126_a(f9);
            double d6 = (double)MathHelper.func_76134_b(f9);
            double d8 = 0.35D;
            double d10 = 0.8D;
            d0 = p_76986_1_.field_146042_b.field_70169_q + (p_76986_1_.field_146042_b.field_70165_t - p_76986_1_.field_146042_b.field_70169_q) * (double)p_76986_9_ - d6 * 0.35D - d4 * 0.8D;
            d1 = p_76986_1_.field_146042_b.field_70167_r + d3 + (p_76986_1_.field_146042_b.field_70163_u - p_76986_1_.field_146042_b.field_70167_r) * (double)p_76986_9_ - 0.45D;
            d2 = p_76986_1_.field_146042_b.field_70166_s + (p_76986_1_.field_146042_b.field_70161_v - p_76986_1_.field_146042_b.field_70166_s) * (double)p_76986_9_ - d4 * 0.35D + d6 * 0.8D;
            d3 = p_76986_1_.field_146042_b.func_70093_af()?-0.1875D:0.0D;
         }

         double d13 = p_76986_1_.field_70169_q + (p_76986_1_.field_70165_t - p_76986_1_.field_70169_q) * (double)p_76986_9_;
         double d5 = p_76986_1_.field_70167_r + (p_76986_1_.field_70163_u - p_76986_1_.field_70167_r) * (double)p_76986_9_ + 0.25D;
         double d7 = p_76986_1_.field_70166_s + (p_76986_1_.field_70161_v - p_76986_1_.field_70166_s) * (double)p_76986_9_;
         double d9 = (double)((float)(d0 - d13));
         double d11 = (double)((float)(d1 - d5)) + d3;
         double d12 = (double)((float)(d2 - d7));
         GlStateManager.func_179090_x();
         GlStateManager.func_179140_f();
         worldrenderer.func_181668_a(3, DefaultVertexFormats.field_181706_f);
         int k = 16;

         for(int l = 0; l <= 16; ++l) {
            float f10 = (float)l / 16.0F;
            worldrenderer.func_181662_b(p_76986_2_ + d9 * (double)f10, p_76986_4_ + d11 * (double)(f10 * f10 + f10) * 0.5D + 0.25D, p_76986_6_ + d12 * (double)f10).func_181669_b(0, 0, 0, 255).func_181675_d();
         }

         tessellator.func_78381_a();
         GlStateManager.func_179145_e();
         GlStateManager.func_179098_w();
         super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
      }

   }

   protected ResourceLocation func_110775_a(EntityFishHook p_110775_1_) {
      return field_110792_a;
   }
}

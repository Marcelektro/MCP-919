package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerEnderDragonDeath;
import net.minecraft.client.renderer.entity.layers.LayerEnderDragonEyes;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderDragon extends RenderLiving<EntityDragon> {
   private static final ResourceLocation field_110843_g = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");
   private static final ResourceLocation field_110842_f = new ResourceLocation("textures/entity/enderdragon/dragon_exploding.png");
   private static final ResourceLocation field_110844_k = new ResourceLocation("textures/entity/enderdragon/dragon.png");
   protected ModelDragon field_77084_b;

   public RenderDragon(RenderManager p_i46183_1_) {
      super(p_i46183_1_, new ModelDragon(0.0F), 0.5F);
      this.field_77084_b = (ModelDragon)this.field_77045_g;
      this.func_177094_a(new LayerEnderDragonEyes(this));
      this.func_177094_a(new LayerEnderDragonDeath());
   }

   protected void func_77043_a(EntityDragon p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
      float f = (float)p_77043_1_.func_70974_a(7, p_77043_4_)[0];
      float f1 = (float)(p_77043_1_.func_70974_a(5, p_77043_4_)[1] - p_77043_1_.func_70974_a(10, p_77043_4_)[1]);
      GlStateManager.func_179114_b(-f, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(f1 * 10.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179109_b(0.0F, 0.0F, 1.0F);
      if(p_77043_1_.field_70725_aQ > 0) {
         float f2 = ((float)p_77043_1_.field_70725_aQ + p_77043_4_ - 1.0F) / 20.0F * 1.6F;
         f2 = MathHelper.func_76129_c(f2);
         if(f2 > 1.0F) {
            f2 = 1.0F;
         }

         GlStateManager.func_179114_b(f2 * this.func_77037_a(p_77043_1_), 0.0F, 0.0F, 1.0F);
      }

   }

   protected void func_77036_a(EntityDragon p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {
      if(p_77036_1_.field_70995_bG > 0) {
         float f = (float)p_77036_1_.field_70995_bG / 200.0F;
         GlStateManager.func_179143_c(515);
         GlStateManager.func_179141_d();
         GlStateManager.func_179092_a(516, f);
         this.func_110776_a(field_110842_f);
         this.field_77045_g.func_78088_a(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
         GlStateManager.func_179092_a(516, 0.1F);
         GlStateManager.func_179143_c(514);
      }

      this.func_180548_c(p_77036_1_);
      this.field_77045_g.func_78088_a(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
      if(p_77036_1_.field_70737_aN > 0) {
         GlStateManager.func_179143_c(514);
         GlStateManager.func_179090_x();
         GlStateManager.func_179147_l();
         GlStateManager.func_179112_b(770, 771);
         GlStateManager.func_179131_c(1.0F, 0.0F, 0.0F, 0.5F);
         this.field_77045_g.func_78088_a(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
         GlStateManager.func_179098_w();
         GlStateManager.func_179084_k();
         GlStateManager.func_179143_c(515);
      }

   }

   public void func_76986_a(EntityDragon p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      BossStatus.func_82824_a(p_76986_1_, false);
      super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
      if(p_76986_1_.field_70992_bH != null) {
         this.func_180574_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_9_);
      }

   }

   protected void func_180574_a(EntityDragon p_180574_1_, double p_180574_2_, double p_180574_4_, double p_180574_6_, float p_180574_8_) {
      float f = (float)p_180574_1_.field_70992_bH.field_70261_a + p_180574_8_;
      float f1 = MathHelper.func_76126_a(f * 0.2F) / 2.0F + 0.5F;
      f1 = (f1 * f1 + f1) * 0.2F;
      float f2 = (float)(p_180574_1_.field_70992_bH.field_70165_t - p_180574_1_.field_70165_t - (p_180574_1_.field_70169_q - p_180574_1_.field_70165_t) * (double)(1.0F - p_180574_8_));
      float f3 = (float)((double)f1 + p_180574_1_.field_70992_bH.field_70163_u - 1.0D - p_180574_1_.field_70163_u - (p_180574_1_.field_70167_r - p_180574_1_.field_70163_u) * (double)(1.0F - p_180574_8_));
      float f4 = (float)(p_180574_1_.field_70992_bH.field_70161_v - p_180574_1_.field_70161_v - (p_180574_1_.field_70166_s - p_180574_1_.field_70161_v) * (double)(1.0F - p_180574_8_));
      float f5 = MathHelper.func_76129_c(f2 * f2 + f4 * f4);
      float f6 = MathHelper.func_76129_c(f2 * f2 + f3 * f3 + f4 * f4);
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b((float)p_180574_2_, (float)p_180574_4_ + 2.0F, (float)p_180574_6_);
      GlStateManager.func_179114_b((float)(-Math.atan2((double)f4, (double)f2)) * 180.0F / 3.1415927F - 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b((float)(-Math.atan2((double)f5, (double)f3)) * 180.0F / 3.1415927F - 90.0F, 1.0F, 0.0F, 0.0F);
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      RenderHelper.func_74518_a();
      GlStateManager.func_179129_p();
      this.func_110776_a(field_110843_g);
      GlStateManager.func_179103_j(7425);
      float f7 = 0.0F - ((float)p_180574_1_.field_70173_aa + p_180574_8_) * 0.01F;
      float f8 = MathHelper.func_76129_c(f2 * f2 + f3 * f3 + f4 * f4) / 32.0F - ((float)p_180574_1_.field_70173_aa + p_180574_8_) * 0.01F;
      worldrenderer.func_181668_a(5, DefaultVertexFormats.field_181709_i);
      int i = 8;

      for(int j = 0; j <= 8; ++j) {
         float f9 = MathHelper.func_76126_a((float)(j % 8) * 3.1415927F * 2.0F / 8.0F) * 0.75F;
         float f10 = MathHelper.func_76134_b((float)(j % 8) * 3.1415927F * 2.0F / 8.0F) * 0.75F;
         float f11 = (float)(j % 8) * 1.0F / 8.0F;
         worldrenderer.func_181662_b((double)(f9 * 0.2F), (double)(f10 * 0.2F), 0.0D).func_181673_a((double)f11, (double)f8).func_181669_b(0, 0, 0, 255).func_181675_d();
         worldrenderer.func_181662_b((double)f9, (double)f10, (double)f6).func_181673_a((double)f11, (double)f7).func_181669_b(255, 255, 255, 255).func_181675_d();
      }

      tessellator.func_78381_a();
      GlStateManager.func_179089_o();
      GlStateManager.func_179103_j(7424);
      RenderHelper.func_74519_b();
      GlStateManager.func_179121_F();
   }

   protected ResourceLocation func_110775_a(EntityDragon p_110775_1_) {
      return field_110844_k;
   }
}

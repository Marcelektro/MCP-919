package net.minecraft.client.renderer.tileentity;

import java.nio.FloatBuffer;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.util.ResourceLocation;

public class TileEntityEndPortalRenderer extends TileEntitySpecialRenderer<TileEntityEndPortal> {
   private static final ResourceLocation field_147529_c = new ResourceLocation("textures/environment/end_sky.png");
   private static final ResourceLocation field_147526_d = new ResourceLocation("textures/entity/end_portal.png");
   private static final Random field_147527_e = new Random(31100L);
   FloatBuffer field_147528_b = GLAllocation.func_74529_h(16);

   public void func_180535_a(TileEntityEndPortal p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
      float f = (float)this.field_147501_a.field_147560_j;
      float f1 = (float)this.field_147501_a.field_147561_k;
      float f2 = (float)this.field_147501_a.field_147558_l;
      GlStateManager.func_179140_f();
      field_147527_e.setSeed(31100L);
      float f3 = 0.75F;

      for(int i = 0; i < 16; ++i) {
         GlStateManager.func_179094_E();
         float f4 = (float)(16 - i);
         float f5 = 0.0625F;
         float f6 = 1.0F / (f4 + 1.0F);
         if(i == 0) {
            this.func_147499_a(field_147529_c);
            f6 = 0.1F;
            f4 = 65.0F;
            f5 = 0.125F;
            GlStateManager.func_179147_l();
            GlStateManager.func_179112_b(770, 771);
         }

         if(i >= 1) {
            this.func_147499_a(field_147526_d);
         }

         if(i == 1) {
            GlStateManager.func_179147_l();
            GlStateManager.func_179112_b(1, 1);
            f5 = 0.5F;
         }

         float f7 = (float)(-(p_180535_4_ + (double)f3));
         float f8 = f7 + (float)ActiveRenderInfo.func_178804_a().field_72448_b;
         float f9 = f7 + f4 + (float)ActiveRenderInfo.func_178804_a().field_72448_b;
         float f10 = f8 / f9;
         f10 = (float)(p_180535_4_ + (double)f3) + f10;
         GlStateManager.func_179109_b(f, f10, f2);
         GlStateManager.func_179149_a(GlStateManager.TexGen.S, 9217);
         GlStateManager.func_179149_a(GlStateManager.TexGen.T, 9217);
         GlStateManager.func_179149_a(GlStateManager.TexGen.R, 9217);
         GlStateManager.func_179149_a(GlStateManager.TexGen.Q, 9216);
         GlStateManager.func_179105_a(GlStateManager.TexGen.S, 9473, this.func_147525_a(1.0F, 0.0F, 0.0F, 0.0F));
         GlStateManager.func_179105_a(GlStateManager.TexGen.T, 9473, this.func_147525_a(0.0F, 0.0F, 1.0F, 0.0F));
         GlStateManager.func_179105_a(GlStateManager.TexGen.R, 9473, this.func_147525_a(0.0F, 0.0F, 0.0F, 1.0F));
         GlStateManager.func_179105_a(GlStateManager.TexGen.Q, 9474, this.func_147525_a(0.0F, 1.0F, 0.0F, 0.0F));
         GlStateManager.func_179087_a(GlStateManager.TexGen.S);
         GlStateManager.func_179087_a(GlStateManager.TexGen.T);
         GlStateManager.func_179087_a(GlStateManager.TexGen.R);
         GlStateManager.func_179087_a(GlStateManager.TexGen.Q);
         GlStateManager.func_179121_F();
         GlStateManager.func_179128_n(5890);
         GlStateManager.func_179094_E();
         GlStateManager.func_179096_D();
         GlStateManager.func_179109_b(0.0F, (float)(Minecraft.func_71386_F() % 700000L) / 700000.0F, 0.0F);
         GlStateManager.func_179152_a(f5, f5, f5);
         GlStateManager.func_179109_b(0.5F, 0.5F, 0.0F);
         GlStateManager.func_179114_b((float)(i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
         GlStateManager.func_179109_b(-0.5F, -0.5F, 0.0F);
         GlStateManager.func_179109_b(-f, -f2, -f1);
         f8 = f7 + (float)ActiveRenderInfo.func_178804_a().field_72448_b;
         GlStateManager.func_179109_b((float)ActiveRenderInfo.func_178804_a().field_72450_a * f4 / f8, (float)ActiveRenderInfo.func_178804_a().field_72449_c * f4 / f8, -f1);
         Tessellator tessellator = Tessellator.func_178181_a();
         WorldRenderer worldrenderer = tessellator.func_178180_c();
         worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
         float f11 = (field_147527_e.nextFloat() * 0.5F + 0.1F) * f6;
         float f12 = (field_147527_e.nextFloat() * 0.5F + 0.4F) * f6;
         float f13 = (field_147527_e.nextFloat() * 0.5F + 0.5F) * f6;
         if(i == 0) {
            f11 = f12 = f13 = 1.0F * f6;
         }

         worldrenderer.func_181662_b(p_180535_2_, p_180535_4_ + (double)f3, p_180535_6_).func_181666_a(f11, f12, f13, 1.0F).func_181675_d();
         worldrenderer.func_181662_b(p_180535_2_, p_180535_4_ + (double)f3, p_180535_6_ + 1.0D).func_181666_a(f11, f12, f13, 1.0F).func_181675_d();
         worldrenderer.func_181662_b(p_180535_2_ + 1.0D, p_180535_4_ + (double)f3, p_180535_6_ + 1.0D).func_181666_a(f11, f12, f13, 1.0F).func_181675_d();
         worldrenderer.func_181662_b(p_180535_2_ + 1.0D, p_180535_4_ + (double)f3, p_180535_6_).func_181666_a(f11, f12, f13, 1.0F).func_181675_d();
         tessellator.func_78381_a();
         GlStateManager.func_179121_F();
         GlStateManager.func_179128_n(5888);
         this.func_147499_a(field_147529_c);
      }

      GlStateManager.func_179084_k();
      GlStateManager.func_179100_b(GlStateManager.TexGen.S);
      GlStateManager.func_179100_b(GlStateManager.TexGen.T);
      GlStateManager.func_179100_b(GlStateManager.TexGen.R);
      GlStateManager.func_179100_b(GlStateManager.TexGen.Q);
      GlStateManager.func_179145_e();
   }

   private FloatBuffer func_147525_a(float p_147525_1_, float p_147525_2_, float p_147525_3_, float p_147525_4_) {
      this.field_147528_b.clear();
      this.field_147528_b.put(p_147525_1_).put(p_147525_2_).put(p_147525_3_).put(p_147525_4_);
      this.field_147528_b.flip();
      return this.field_147528_b;
   }
}

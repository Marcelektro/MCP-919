package net.minecraft.client.renderer.entity;

import com.google.common.collect.Lists;
import java.nio.FloatBuffer;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public abstract class RendererLivingEntity<T extends EntityLivingBase> extends Render<T> {
   private static final Logger field_147923_a = LogManager.getLogger();
   private static final DynamicTexture field_177096_e = new DynamicTexture(16, 16);
   protected ModelBase field_77045_g;
   protected FloatBuffer field_177095_g = GLAllocation.func_74529_h(4);
   protected List<LayerRenderer<T>> field_177097_h = Lists.<LayerRenderer<T>>newArrayList();
   protected boolean field_177098_i = false;

   public RendererLivingEntity(RenderManager p_i46156_1_, ModelBase p_i46156_2_, float p_i46156_3_) {
      super(p_i46156_1_);
      this.field_77045_g = p_i46156_2_;
      this.field_76989_e = p_i46156_3_;
   }

   protected <V extends EntityLivingBase, U extends LayerRenderer<V>> boolean func_177094_a(U p_177094_1_) {
      return this.field_177097_h.add(p_177094_1_);
   }

   protected <V extends EntityLivingBase, U extends LayerRenderer<V>> boolean func_177089_b(U p_177089_1_) {
      return this.field_177097_h.remove(p_177089_1_);
   }

   public ModelBase func_177087_b() {
      return this.field_77045_g;
   }

   protected float func_77034_a(float p_77034_1_, float p_77034_2_, float p_77034_3_) {
      float f;
      for(f = p_77034_2_ - p_77034_1_; f < -180.0F; f += 360.0F) {
         ;
      }

      while(f >= 180.0F) {
         f -= 360.0F;
      }

      return p_77034_1_ + p_77034_3_ * f;
   }

   public void func_82422_c() {
   }

   public void func_76986_a(T p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179129_p();
      this.field_77045_g.field_78095_p = this.func_77040_d(p_76986_1_, p_76986_9_);
      this.field_77045_g.field_78093_q = p_76986_1_.func_70115_ae();
      this.field_77045_g.field_78091_s = p_76986_1_.func_70631_g_();

      try {
         float f = this.func_77034_a(p_76986_1_.field_70760_ar, p_76986_1_.field_70761_aq, p_76986_9_);
         float f1 = this.func_77034_a(p_76986_1_.field_70758_at, p_76986_1_.field_70759_as, p_76986_9_);
         float f2 = f1 - f;
         if(p_76986_1_.func_70115_ae() && p_76986_1_.field_70154_o instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase)p_76986_1_.field_70154_o;
            f = this.func_77034_a(entitylivingbase.field_70760_ar, entitylivingbase.field_70761_aq, p_76986_9_);
            f2 = f1 - f;
            float f3 = MathHelper.func_76142_g(f2);
            if(f3 < -85.0F) {
               f3 = -85.0F;
            }

            if(f3 >= 85.0F) {
               f3 = 85.0F;
            }

            f = f1 - f3;
            if(f3 * f3 > 2500.0F) {
               f += f3 * 0.2F;
            }
         }

         float f7 = p_76986_1_.field_70127_C + (p_76986_1_.field_70125_A - p_76986_1_.field_70127_C) * p_76986_9_;
         this.func_77039_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
         float f8 = this.func_77044_a(p_76986_1_, p_76986_9_);
         this.func_77043_a(p_76986_1_, f8, f, p_76986_9_);
         GlStateManager.func_179091_B();
         GlStateManager.func_179152_a(-1.0F, -1.0F, 1.0F);
         this.func_77041_b(p_76986_1_, p_76986_9_);
         float f4 = 0.0625F;
         GlStateManager.func_179109_b(0.0F, -1.5078125F, 0.0F);
         float f5 = p_76986_1_.field_70722_aY + (p_76986_1_.field_70721_aZ - p_76986_1_.field_70722_aY) * p_76986_9_;
         float f6 = p_76986_1_.field_70754_ba - p_76986_1_.field_70721_aZ * (1.0F - p_76986_9_);
         if(p_76986_1_.func_70631_g_()) {
            f6 *= 3.0F;
         }

         if(f5 > 1.0F) {
            f5 = 1.0F;
         }

         GlStateManager.func_179141_d();
         this.field_77045_g.func_78086_a(p_76986_1_, f6, f5, p_76986_9_);
         this.field_77045_g.func_78087_a(f6, f5, f8, f2, f7, 0.0625F, p_76986_1_);
         if(this.field_177098_i) {
            boolean flag1 = this.func_177088_c(p_76986_1_);
            this.func_77036_a(p_76986_1_, f6, f5, f8, f2, f7, 0.0625F);
            if(flag1) {
               this.func_180565_e();
            }
         } else {
            boolean flag = this.func_177090_c(p_76986_1_, p_76986_9_);
            this.func_77036_a(p_76986_1_, f6, f5, f8, f2, f7, 0.0625F);
            if(flag) {
               this.func_177091_f();
            }

            GlStateManager.func_179132_a(true);
            if(!(p_76986_1_ instanceof EntityPlayer) || !((EntityPlayer)p_76986_1_).func_175149_v()) {
               this.func_177093_a(p_76986_1_, f6, f5, p_76986_9_, f8, f2, f7, 0.0625F);
            }
         }

         GlStateManager.func_179101_C();
      } catch (Exception exception) {
         field_147923_a.error((String)"Couldn\'t render entity", (Throwable)exception);
      }

      GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
      GlStateManager.func_179098_w();
      GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
      GlStateManager.func_179089_o();
      GlStateManager.func_179121_F();
      if(!this.field_177098_i) {
         super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
      }

   }

   protected boolean func_177088_c(T p_177088_1_) {
      int i = 16777215;
      if(p_177088_1_ instanceof EntityPlayer) {
         ScorePlayerTeam scoreplayerteam = (ScorePlayerTeam)p_177088_1_.func_96124_cp();
         if(scoreplayerteam != null) {
            String s = FontRenderer.func_78282_e(scoreplayerteam.func_96668_e());
            if(s.length() >= 2) {
               i = this.func_76983_a().func_175064_b(s.charAt(1));
            }
         }
      }

      float f1 = (float)(i >> 16 & 255) / 255.0F;
      float f2 = (float)(i >> 8 & 255) / 255.0F;
      float f = (float)(i & 255) / 255.0F;
      GlStateManager.func_179140_f();
      GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
      GlStateManager.func_179131_c(f1, f2, f, 1.0F);
      GlStateManager.func_179090_x();
      GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
      GlStateManager.func_179090_x();
      GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
      return true;
   }

   protected void func_180565_e() {
      GlStateManager.func_179145_e();
      GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
      GlStateManager.func_179098_w();
      GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
      GlStateManager.func_179098_w();
      GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
   }

   protected void func_77036_a(T p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {
      boolean flag = !p_77036_1_.func_82150_aj();
      boolean flag1 = !flag && !p_77036_1_.func_98034_c(Minecraft.func_71410_x().field_71439_g);
      if(flag || flag1) {
         if(!this.func_180548_c(p_77036_1_)) {
            return;
         }

         if(flag1) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.15F);
            GlStateManager.func_179132_a(false);
            GlStateManager.func_179147_l();
            GlStateManager.func_179112_b(770, 771);
            GlStateManager.func_179092_a(516, 0.003921569F);
         }

         this.field_77045_g.func_78088_a(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
         if(flag1) {
            GlStateManager.func_179084_k();
            GlStateManager.func_179092_a(516, 0.1F);
            GlStateManager.func_179121_F();
            GlStateManager.func_179132_a(true);
         }
      }

   }

   protected boolean func_177090_c(T p_177090_1_, float p_177090_2_) {
      return this.func_177092_a(p_177090_1_, p_177090_2_, true);
   }

   protected boolean func_177092_a(T p_177092_1_, float p_177092_2_, boolean p_177092_3_) {
      float f = p_177092_1_.func_70013_c(p_177092_2_);
      int i = this.func_77030_a(p_177092_1_, f, p_177092_2_);
      boolean flag = (i >> 24 & 255) > 0;
      boolean flag1 = p_177092_1_.field_70737_aN > 0 || p_177092_1_.field_70725_aQ > 0;
      if(!flag && !flag1) {
         return false;
      } else if(!flag && !p_177092_3_) {
         return false;
      } else {
         GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
         GlStateManager.func_179098_w();
         GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, OpenGlHelper.field_77478_a);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176093_u);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 7681);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, OpenGlHelper.field_77478_a);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
         GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
         GlStateManager.func_179098_w();
         GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, OpenGlHelper.field_176094_t);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, OpenGlHelper.field_176092_v);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176091_w);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176080_A, OpenGlHelper.field_176092_v);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176076_D, 770);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 7681);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, OpenGlHelper.field_176091_w);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
         this.field_177095_g.position(0);
         if(flag1) {
            this.field_177095_g.put(1.0F);
            this.field_177095_g.put(0.0F);
            this.field_177095_g.put(0.0F);
            this.field_177095_g.put(0.3F);
         } else {
            float f1 = (float)(i >> 24 & 255) / 255.0F;
            float f2 = (float)(i >> 16 & 255) / 255.0F;
            float f3 = (float)(i >> 8 & 255) / 255.0F;
            float f4 = (float)(i & 255) / 255.0F;
            this.field_177095_g.put(f2);
            this.field_177095_g.put(f3);
            this.field_177095_g.put(f4);
            this.field_177095_g.put(1.0F - f1);
         }

         this.field_177095_g.flip();
         GL11.glTexEnv(8960, 8705, (FloatBuffer)this.field_177095_g);
         GlStateManager.func_179138_g(OpenGlHelper.field_176096_r);
         GlStateManager.func_179098_w();
         GlStateManager.func_179144_i(field_177096_e.func_110552_b());
         GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, OpenGlHelper.field_176091_w);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_77476_b);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 7681);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, OpenGlHelper.field_176091_w);
         GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
         GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
         return true;
      }
   }

   protected void func_177091_f() {
      GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
      GlStateManager.func_179098_w();
      GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, OpenGlHelper.field_77478_a);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176093_u);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, OpenGlHelper.field_77478_a);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176079_G, OpenGlHelper.field_176093_u);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176086_J, 770);
      GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
      GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, 5890);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176091_w);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, 5890);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179138_g(OpenGlHelper.field_176096_r);
      GlStateManager.func_179090_x();
      GlStateManager.func_179144_i(0);
      GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, 5890);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176091_w);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 8448);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
      GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, 5890);
      GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
   }

   protected void func_77039_a(T p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_) {
      GlStateManager.func_179109_b((float)p_77039_2_, (float)p_77039_4_, (float)p_77039_6_);
   }

   protected void func_77043_a(T p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
      GlStateManager.func_179114_b(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);
      if(p_77043_1_.field_70725_aQ > 0) {
         float f = ((float)p_77043_1_.field_70725_aQ + p_77043_4_ - 1.0F) / 20.0F * 1.6F;
         f = MathHelper.func_76129_c(f);
         if(f > 1.0F) {
            f = 1.0F;
         }

         GlStateManager.func_179114_b(f * this.func_77037_a(p_77043_1_), 0.0F, 0.0F, 1.0F);
      } else {
         String s = EnumChatFormatting.func_110646_a(p_77043_1_.func_70005_c_());
         if(s != null && (s.equals("Dinnerbone") || s.equals("Grumm")) && (!(p_77043_1_ instanceof EntityPlayer) || ((EntityPlayer)p_77043_1_).func_175148_a(EnumPlayerModelParts.CAPE))) {
            GlStateManager.func_179109_b(0.0F, p_77043_1_.field_70131_O + 0.1F, 0.0F);
            GlStateManager.func_179114_b(180.0F, 0.0F, 0.0F, 1.0F);
         }
      }

   }

   protected float func_77040_d(T p_77040_1_, float p_77040_2_) {
      return p_77040_1_.func_70678_g(p_77040_2_);
   }

   protected float func_77044_a(T p_77044_1_, float p_77044_2_) {
      return (float)p_77044_1_.field_70173_aa + p_77044_2_;
   }

   protected void func_177093_a(T p_177093_1_, float p_177093_2_, float p_177093_3_, float p_177093_4_, float p_177093_5_, float p_177093_6_, float p_177093_7_, float p_177093_8_) {
      for(LayerRenderer<T> layerrenderer : this.field_177097_h) {
         boolean flag = this.func_177092_a(p_177093_1_, p_177093_4_, layerrenderer.func_177142_b());
         layerrenderer.func_177141_a(p_177093_1_, p_177093_2_, p_177093_3_, p_177093_4_, p_177093_5_, p_177093_6_, p_177093_7_, p_177093_8_);
         if(flag) {
            this.func_177091_f();
         }
      }

   }

   protected float func_77037_a(T p_77037_1_) {
      return 90.0F;
   }

   protected int func_77030_a(T p_77030_1_, float p_77030_2_, float p_77030_3_) {
      return 0;
   }

   protected void func_77041_b(T p_77041_1_, float p_77041_2_) {
   }

   public void func_177067_a(T p_177067_1_, double p_177067_2_, double p_177067_4_, double p_177067_6_) {
      if(this.func_177070_b(p_177067_1_)) {
         double d0 = p_177067_1_.func_70068_e(this.field_76990_c.field_78734_h);
         float f = p_177067_1_.func_70093_af()?32.0F:64.0F;
         if(d0 < (double)(f * f)) {
            String s = p_177067_1_.func_145748_c_().func_150254_d();
            float f1 = 0.02666667F;
            GlStateManager.func_179092_a(516, 0.1F);
            if(p_177067_1_.func_70093_af()) {
               FontRenderer fontrenderer = this.func_76983_a();
               GlStateManager.func_179094_E();
               GlStateManager.func_179109_b((float)p_177067_2_, (float)p_177067_4_ + p_177067_1_.field_70131_O + 0.5F - (p_177067_1_.func_70631_g_()?p_177067_1_.field_70131_O / 2.0F:0.0F), (float)p_177067_6_);
               GL11.glNormal3f(0.0F, 1.0F, 0.0F);
               GlStateManager.func_179114_b(-this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
               GlStateManager.func_179114_b(this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
               GlStateManager.func_179152_a(-0.02666667F, -0.02666667F, 0.02666667F);
               GlStateManager.func_179109_b(0.0F, 9.374999F, 0.0F);
               GlStateManager.func_179140_f();
               GlStateManager.func_179132_a(false);
               GlStateManager.func_179147_l();
               GlStateManager.func_179090_x();
               GlStateManager.func_179120_a(770, 771, 1, 0);
               int i = fontrenderer.func_78256_a(s) / 2;
               Tessellator tessellator = Tessellator.func_178181_a();
               WorldRenderer worldrenderer = tessellator.func_178180_c();
               worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
               worldrenderer.func_181662_b((double)(-i - 1), -1.0D, 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
               worldrenderer.func_181662_b((double)(-i - 1), 8.0D, 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
               worldrenderer.func_181662_b((double)(i + 1), 8.0D, 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
               worldrenderer.func_181662_b((double)(i + 1), -1.0D, 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
               tessellator.func_78381_a();
               GlStateManager.func_179098_w();
               GlStateManager.func_179132_a(true);
               fontrenderer.func_78276_b(s, -fontrenderer.func_78256_a(s) / 2, 0, 553648127);
               GlStateManager.func_179145_e();
               GlStateManager.func_179084_k();
               GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.func_179121_F();
            } else {
               this.func_177069_a(p_177067_1_, p_177067_2_, p_177067_4_ - (p_177067_1_.func_70631_g_()?(double)(p_177067_1_.field_70131_O / 2.0F):0.0D), p_177067_6_, s, 0.02666667F, d0);
            }

         }
      }
   }

   protected boolean func_177070_b(T p_177070_1_) {
      EntityPlayerSP entityplayersp = Minecraft.func_71410_x().field_71439_g;
      if(p_177070_1_ instanceof EntityPlayer && p_177070_1_ != entityplayersp) {
         Team team = p_177070_1_.func_96124_cp();
         Team team1 = entityplayersp.func_96124_cp();
         if(team != null) {
            Team.EnumVisible team$enumvisible = team.func_178770_i();
            switch(team$enumvisible) {
            case ALWAYS:
               return true;
            case NEVER:
               return false;
            case HIDE_FOR_OTHER_TEAMS:
               return team1 == null || team.func_142054_a(team1);
            case HIDE_FOR_OWN_TEAM:
               return team1 == null || !team.func_142054_a(team1);
            default:
               return true;
            }
         }
      }

      return Minecraft.func_71382_s() && p_177070_1_ != this.field_76990_c.field_78734_h && !p_177070_1_.func_98034_c(entityplayersp) && p_177070_1_.field_70153_n == null;
   }

   public void func_177086_a(boolean p_177086_1_) {
      this.field_177098_i = p_177086_1_;
   }

   static {
      int[] aint = field_177096_e.func_110565_c();

      for(int i = 0; i < 256; ++i) {
         aint[i] = -1;
      }

      field_177096_e.func_110564_a();
   }
}

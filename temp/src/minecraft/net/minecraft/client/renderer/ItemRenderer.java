package net.minecraft.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;
import org.lwjgl.opengl.GL11;

public class ItemRenderer {
   private static final ResourceLocation field_110931_c = new ResourceLocation("textures/map/map_background.png");
   private static final ResourceLocation field_110929_d = new ResourceLocation("textures/misc/underwater.png");
   private final Minecraft field_78455_a;
   private ItemStack field_78453_b;
   private float field_78454_c;
   private float field_78451_d;
   private final RenderManager field_178111_g;
   private final RenderItem field_178112_h;
   private int field_78450_g = -1;

   public ItemRenderer(Minecraft p_i1247_1_) {
      this.field_78455_a = p_i1247_1_;
      this.field_178111_g = p_i1247_1_.func_175598_ae();
      this.field_178112_h = p_i1247_1_.func_175599_af();
   }

   public void func_178099_a(EntityLivingBase p_178099_1_, ItemStack p_178099_2_, ItemCameraTransforms.TransformType p_178099_3_) {
      if(p_178099_2_ != null) {
         Item item = p_178099_2_.func_77973_b();
         Block block = Block.func_149634_a(item);
         GlStateManager.func_179094_E();
         if(this.field_178112_h.func_175050_a(p_178099_2_)) {
            GlStateManager.func_179152_a(2.0F, 2.0F, 2.0F);
            if(this.func_178107_a(block)) {
               GlStateManager.func_179132_a(false);
            }
         }

         this.field_178112_h.func_175049_a(p_178099_2_, p_178099_1_, p_178099_3_);
         if(this.func_178107_a(block)) {
            GlStateManager.func_179132_a(true);
         }

         GlStateManager.func_179121_F();
      }
   }

   private boolean func_178107_a(Block p_178107_1_) {
      return p_178107_1_ != null && p_178107_1_.func_180664_k() == EnumWorldBlockLayer.TRANSLUCENT;
   }

   private void func_178101_a(float p_178101_1_, float p_178101_2_) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179114_b(p_178101_1_, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179114_b(p_178101_2_, 0.0F, 1.0F, 0.0F);
      RenderHelper.func_74519_b();
      GlStateManager.func_179121_F();
   }

   private void func_178109_a(AbstractClientPlayer p_178109_1_) {
      int i = this.field_78455_a.field_71441_e.func_175626_b(new BlockPos(p_178109_1_.field_70165_t, p_178109_1_.field_70163_u + (double)p_178109_1_.func_70047_e(), p_178109_1_.field_70161_v), 0);
      float f = (float)(i & '\uffff');
      float f1 = (float)(i >> 16);
      OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, f, f1);
   }

   private void func_178110_a(EntityPlayerSP p_178110_1_, float p_178110_2_) {
      float f = p_178110_1_.field_71164_i + (p_178110_1_.field_71155_g - p_178110_1_.field_71164_i) * p_178110_2_;
      float f1 = p_178110_1_.field_71163_h + (p_178110_1_.field_71154_f - p_178110_1_.field_71163_h) * p_178110_2_;
      GlStateManager.func_179114_b((p_178110_1_.field_70125_A - f) * 0.1F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179114_b((p_178110_1_.field_70177_z - f1) * 0.1F, 0.0F, 1.0F, 0.0F);
   }

   private float func_178100_c(float p_178100_1_) {
      float f = 1.0F - p_178100_1_ / 45.0F + 0.1F;
      f = MathHelper.func_76131_a(f, 0.0F, 1.0F);
      f = -MathHelper.func_76134_b(f * 3.1415927F) * 0.5F + 0.5F;
      return f;
   }

   private void func_180534_a(RenderPlayer p_180534_1_) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179114_b(54.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(64.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179114_b(-62.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.func_179109_b(0.25F, -0.85F, 0.75F);
      p_180534_1_.func_177138_b(this.field_78455_a.field_71439_g);
      GlStateManager.func_179121_F();
   }

   private void func_178106_b(RenderPlayer p_178106_1_) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179114_b(92.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(45.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179114_b(41.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.func_179109_b(-0.3F, -1.1F, 0.45F);
      p_178106_1_.func_177139_c(this.field_78455_a.field_71439_g);
      GlStateManager.func_179121_F();
   }

   private void func_178102_b(AbstractClientPlayer p_178102_1_) {
      this.field_78455_a.func_110434_K().func_110577_a(p_178102_1_.func_110306_p());
      Render<AbstractClientPlayer> render = this.field_178111_g.<AbstractClientPlayer>func_78713_a(this.field_78455_a.field_71439_g);
      RenderPlayer renderplayer = (RenderPlayer)render;
      if(!p_178102_1_.func_82150_aj()) {
         GlStateManager.func_179129_p();
         this.func_180534_a(renderplayer);
         this.func_178106_b(renderplayer);
         GlStateManager.func_179089_o();
      }

   }

   private void func_178097_a(AbstractClientPlayer p_178097_1_, float p_178097_2_, float p_178097_3_, float p_178097_4_) {
      float f = -0.4F * MathHelper.func_76126_a(MathHelper.func_76129_c(p_178097_4_) * 3.1415927F);
      float f1 = 0.2F * MathHelper.func_76126_a(MathHelper.func_76129_c(p_178097_4_) * 3.1415927F * 2.0F);
      float f2 = -0.2F * MathHelper.func_76126_a(p_178097_4_ * 3.1415927F);
      GlStateManager.func_179109_b(f, f1, f2);
      float f3 = this.func_178100_c(p_178097_2_);
      GlStateManager.func_179109_b(0.0F, 0.04F, -0.72F);
      GlStateManager.func_179109_b(0.0F, p_178097_3_ * -1.2F, 0.0F);
      GlStateManager.func_179109_b(0.0F, f3 * -0.5F, 0.0F);
      GlStateManager.func_179114_b(90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(f3 * -85.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.func_179114_b(0.0F, 1.0F, 0.0F, 0.0F);
      this.func_178102_b(p_178097_1_);
      float f4 = MathHelper.func_76126_a(p_178097_4_ * p_178097_4_ * 3.1415927F);
      float f5 = MathHelper.func_76126_a(MathHelper.func_76129_c(p_178097_4_) * 3.1415927F);
      GlStateManager.func_179114_b(f4 * -20.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(f5 * -20.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.func_179114_b(f5 * -80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179152_a(0.38F, 0.38F, 0.38F);
      GlStateManager.func_179114_b(90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(180.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.func_179114_b(0.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179109_b(-1.0F, -1.0F, 0.0F);
      GlStateManager.func_179152_a(0.015625F, 0.015625F, 0.015625F);
      this.field_78455_a.func_110434_K().func_110577_a(field_110931_c);
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      GL11.glNormal3f(0.0F, 0.0F, -1.0F);
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      worldrenderer.func_181662_b(-7.0D, 135.0D, 0.0D).func_181673_a(0.0D, 1.0D).func_181675_d();
      worldrenderer.func_181662_b(135.0D, 135.0D, 0.0D).func_181673_a(1.0D, 1.0D).func_181675_d();
      worldrenderer.func_181662_b(135.0D, -7.0D, 0.0D).func_181673_a(1.0D, 0.0D).func_181675_d();
      worldrenderer.func_181662_b(-7.0D, -7.0D, 0.0D).func_181673_a(0.0D, 0.0D).func_181675_d();
      tessellator.func_78381_a();
      MapData mapdata = Items.field_151098_aY.func_77873_a(this.field_78453_b, this.field_78455_a.field_71441_e);
      if(mapdata != null) {
         this.field_78455_a.field_71460_t.func_147701_i().func_148250_a(mapdata, false);
      }

   }

   private void func_178095_a(AbstractClientPlayer p_178095_1_, float p_178095_2_, float p_178095_3_) {
      float f = -0.3F * MathHelper.func_76126_a(MathHelper.func_76129_c(p_178095_3_) * 3.1415927F);
      float f1 = 0.4F * MathHelper.func_76126_a(MathHelper.func_76129_c(p_178095_3_) * 3.1415927F * 2.0F);
      float f2 = -0.4F * MathHelper.func_76126_a(p_178095_3_ * 3.1415927F);
      GlStateManager.func_179109_b(f, f1, f2);
      GlStateManager.func_179109_b(0.64000005F, -0.6F, -0.71999997F);
      GlStateManager.func_179109_b(0.0F, p_178095_2_ * -0.6F, 0.0F);
      GlStateManager.func_179114_b(45.0F, 0.0F, 1.0F, 0.0F);
      float f3 = MathHelper.func_76126_a(p_178095_3_ * p_178095_3_ * 3.1415927F);
      float f4 = MathHelper.func_76126_a(MathHelper.func_76129_c(p_178095_3_) * 3.1415927F);
      GlStateManager.func_179114_b(f4 * 70.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(f3 * -20.0F, 0.0F, 0.0F, 1.0F);
      this.field_78455_a.func_110434_K().func_110577_a(p_178095_1_.func_110306_p());
      GlStateManager.func_179109_b(-1.0F, 3.6F, 3.5F);
      GlStateManager.func_179114_b(120.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.func_179114_b(200.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179114_b(-135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179152_a(1.0F, 1.0F, 1.0F);
      GlStateManager.func_179109_b(5.6F, 0.0F, 0.0F);
      Render<AbstractClientPlayer> render = this.field_178111_g.<AbstractClientPlayer>func_78713_a(this.field_78455_a.field_71439_g);
      GlStateManager.func_179129_p();
      RenderPlayer renderplayer = (RenderPlayer)render;
      renderplayer.func_177138_b(this.field_78455_a.field_71439_g);
      GlStateManager.func_179089_o();
   }

   private void func_178105_d(float p_178105_1_) {
      float f = -0.4F * MathHelper.func_76126_a(MathHelper.func_76129_c(p_178105_1_) * 3.1415927F);
      float f1 = 0.2F * MathHelper.func_76126_a(MathHelper.func_76129_c(p_178105_1_) * 3.1415927F * 2.0F);
      float f2 = -0.2F * MathHelper.func_76126_a(p_178105_1_ * 3.1415927F);
      GlStateManager.func_179109_b(f, f1, f2);
   }

   private void func_178104_a(AbstractClientPlayer p_178104_1_, float p_178104_2_) {
      float f = (float)p_178104_1_.func_71052_bv() - p_178104_2_ + 1.0F;
      float f1 = f / (float)this.field_78453_b.func_77988_m();
      float f2 = MathHelper.func_76135_e(MathHelper.func_76134_b(f / 4.0F * 3.1415927F) * 0.1F);
      if(f1 >= 0.8F) {
         f2 = 0.0F;
      }

      GlStateManager.func_179109_b(0.0F, f2, 0.0F);
      float f3 = 1.0F - (float)Math.pow((double)f1, 27.0D);
      GlStateManager.func_179109_b(f3 * 0.6F, f3 * -0.5F, f3 * 0.0F);
      GlStateManager.func_179114_b(f3 * 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(f3 * 10.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179114_b(f3 * 30.0F, 0.0F, 0.0F, 1.0F);
   }

   private void func_178096_b(float p_178096_1_, float p_178096_2_) {
      GlStateManager.func_179109_b(0.56F, -0.52F, -0.71999997F);
      GlStateManager.func_179109_b(0.0F, p_178096_1_ * -0.6F, 0.0F);
      GlStateManager.func_179114_b(45.0F, 0.0F, 1.0F, 0.0F);
      float f = MathHelper.func_76126_a(p_178096_2_ * p_178096_2_ * 3.1415927F);
      float f1 = MathHelper.func_76126_a(MathHelper.func_76129_c(p_178096_2_) * 3.1415927F);
      GlStateManager.func_179114_b(f * -20.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(f1 * -20.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.func_179114_b(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179152_a(0.4F, 0.4F, 0.4F);
   }

   private void func_178098_a(float p_178098_1_, AbstractClientPlayer p_178098_2_) {
      GlStateManager.func_179114_b(-18.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.func_179114_b(-12.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(-8.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179109_b(-0.9F, 0.2F, 0.0F);
      float f = (float)this.field_78453_b.func_77988_m() - ((float)p_178098_2_.func_71052_bv() - p_178098_1_ + 1.0F);
      float f1 = f / 20.0F;
      f1 = (f1 * f1 + f1 * 2.0F) / 3.0F;
      if(f1 > 1.0F) {
         f1 = 1.0F;
      }

      if(f1 > 0.1F) {
         float f2 = MathHelper.func_76126_a((f - 0.1F) * 1.3F);
         float f3 = f1 - 0.1F;
         float f4 = f2 * f3;
         GlStateManager.func_179109_b(f4 * 0.0F, f4 * 0.01F, f4 * 0.0F);
      }

      GlStateManager.func_179109_b(f1 * 0.0F, f1 * 0.0F, f1 * 0.1F);
      GlStateManager.func_179152_a(1.0F, 1.0F, 1.0F + f1 * 0.2F);
   }

   private void func_178103_d() {
      GlStateManager.func_179109_b(-0.5F, 0.2F, 0.0F);
      GlStateManager.func_179114_b(30.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(-80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179114_b(60.0F, 0.0F, 1.0F, 0.0F);
   }

   public void func_78440_a(float p_78440_1_) {
      float f = 1.0F - (this.field_78451_d + (this.field_78454_c - this.field_78451_d) * p_78440_1_);
      AbstractClientPlayer abstractclientplayer = this.field_78455_a.field_71439_g;
      float f1 = abstractclientplayer.func_70678_g(p_78440_1_);
      float f2 = abstractclientplayer.field_70127_C + (abstractclientplayer.field_70125_A - abstractclientplayer.field_70127_C) * p_78440_1_;
      float f3 = abstractclientplayer.field_70126_B + (abstractclientplayer.field_70177_z - abstractclientplayer.field_70126_B) * p_78440_1_;
      this.func_178101_a(f2, f3);
      this.func_178109_a(abstractclientplayer);
      this.func_178110_a((EntityPlayerSP)abstractclientplayer, p_78440_1_);
      GlStateManager.func_179091_B();
      GlStateManager.func_179094_E();
      if(this.field_78453_b != null) {
         if(this.field_78453_b.func_77973_b() == Items.field_151098_aY) {
            this.func_178097_a(abstractclientplayer, f2, f, f1);
         } else if(abstractclientplayer.func_71052_bv() > 0) {
            EnumAction enumaction = this.field_78453_b.func_77975_n();
            switch(enumaction) {
            case NONE:
               this.func_178096_b(f, 0.0F);
               break;
            case EAT:
            case DRINK:
               this.func_178104_a(abstractclientplayer, p_78440_1_);
               this.func_178096_b(f, 0.0F);
               break;
            case BLOCK:
               this.func_178096_b(f, 0.0F);
               this.func_178103_d();
               break;
            case BOW:
               this.func_178096_b(f, 0.0F);
               this.func_178098_a(p_78440_1_, abstractclientplayer);
            }
         } else {
            this.func_178105_d(f1);
            this.func_178096_b(f, f1);
         }

         this.func_178099_a(abstractclientplayer, this.field_78453_b, ItemCameraTransforms.TransformType.FIRST_PERSON);
      } else if(!abstractclientplayer.func_82150_aj()) {
         this.func_178095_a(abstractclientplayer, f, f1);
      }

      GlStateManager.func_179121_F();
      GlStateManager.func_179101_C();
      RenderHelper.func_74518_a();
   }

   public void func_78447_b(float p_78447_1_) {
      GlStateManager.func_179118_c();
      if(this.field_78455_a.field_71439_g.func_70094_T()) {
         IBlockState iblockstate = this.field_78455_a.field_71441_e.func_180495_p(new BlockPos(this.field_78455_a.field_71439_g));
         EntityPlayer entityplayer = this.field_78455_a.field_71439_g;

         for(int i = 0; i < 8; ++i) {
            double d0 = entityplayer.field_70165_t + (double)(((float)((i >> 0) % 2) - 0.5F) * entityplayer.field_70130_N * 0.8F);
            double d1 = entityplayer.field_70163_u + (double)(((float)((i >> 1) % 2) - 0.5F) * 0.1F);
            double d2 = entityplayer.field_70161_v + (double)(((float)((i >> 2) % 2) - 0.5F) * entityplayer.field_70130_N * 0.8F);
            BlockPos blockpos = new BlockPos(d0, d1 + (double)entityplayer.func_70047_e(), d2);
            IBlockState iblockstate1 = this.field_78455_a.field_71441_e.func_180495_p(blockpos);
            if(iblockstate1.func_177230_c().func_176214_u()) {
               iblockstate = iblockstate1;
            }
         }

         if(iblockstate.func_177230_c().func_149645_b() != -1) {
            this.func_178108_a(p_78447_1_, this.field_78455_a.func_175602_ab().func_175023_a().func_178122_a(iblockstate));
         }
      }

      if(!this.field_78455_a.field_71439_g.func_175149_v()) {
         if(this.field_78455_a.field_71439_g.func_70055_a(Material.field_151586_h)) {
            this.func_78448_c(p_78447_1_);
         }

         if(this.field_78455_a.field_71439_g.func_70027_ad()) {
            this.func_78442_d(p_78447_1_);
         }
      }

      GlStateManager.func_179141_d();
   }

   private void func_178108_a(float p_178108_1_, TextureAtlasSprite p_178108_2_) {
      this.field_78455_a.func_110434_K().func_110577_a(TextureMap.field_110575_b);
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      float f = 0.1F;
      GlStateManager.func_179131_c(0.1F, 0.1F, 0.1F, 0.5F);
      GlStateManager.func_179094_E();
      float f1 = -1.0F;
      float f2 = 1.0F;
      float f3 = -1.0F;
      float f4 = 1.0F;
      float f5 = -0.5F;
      float f6 = p_178108_2_.func_94209_e();
      float f7 = p_178108_2_.func_94212_f();
      float f8 = p_178108_2_.func_94206_g();
      float f9 = p_178108_2_.func_94210_h();
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      worldrenderer.func_181662_b(-1.0D, -1.0D, -0.5D).func_181673_a((double)f7, (double)f9).func_181675_d();
      worldrenderer.func_181662_b(1.0D, -1.0D, -0.5D).func_181673_a((double)f6, (double)f9).func_181675_d();
      worldrenderer.func_181662_b(1.0D, 1.0D, -0.5D).func_181673_a((double)f6, (double)f8).func_181675_d();
      worldrenderer.func_181662_b(-1.0D, 1.0D, -0.5D).func_181673_a((double)f7, (double)f8).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179121_F();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void func_78448_c(float p_78448_1_) {
      this.field_78455_a.func_110434_K().func_110577_a(field_110929_d);
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      float f = this.field_78455_a.field_71439_g.func_70013_c(p_78448_1_);
      GlStateManager.func_179131_c(f, f, f, 0.5F);
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179094_E();
      float f1 = 4.0F;
      float f2 = -1.0F;
      float f3 = 1.0F;
      float f4 = -1.0F;
      float f5 = 1.0F;
      float f6 = -0.5F;
      float f7 = -this.field_78455_a.field_71439_g.field_70177_z / 64.0F;
      float f8 = this.field_78455_a.field_71439_g.field_70125_A / 64.0F;
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      worldrenderer.func_181662_b(-1.0D, -1.0D, -0.5D).func_181673_a((double)(4.0F + f7), (double)(4.0F + f8)).func_181675_d();
      worldrenderer.func_181662_b(1.0D, -1.0D, -0.5D).func_181673_a((double)(0.0F + f7), (double)(4.0F + f8)).func_181675_d();
      worldrenderer.func_181662_b(1.0D, 1.0D, -0.5D).func_181673_a((double)(0.0F + f7), (double)(0.0F + f8)).func_181675_d();
      worldrenderer.func_181662_b(-1.0D, 1.0D, -0.5D).func_181673_a((double)(4.0F + f7), (double)(0.0F + f8)).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179121_F();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179084_k();
   }

   private void func_78442_d(float p_78442_1_) {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.9F);
      GlStateManager.func_179143_c(519);
      GlStateManager.func_179132_a(false);
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      float f = 1.0F;

      for(int i = 0; i < 2; ++i) {
         GlStateManager.func_179094_E();
         TextureAtlasSprite textureatlassprite = this.field_78455_a.func_147117_R().func_110572_b("minecraft:blocks/fire_layer_1");
         this.field_78455_a.func_110434_K().func_110577_a(TextureMap.field_110575_b);
         float f1 = textureatlassprite.func_94209_e();
         float f2 = textureatlassprite.func_94212_f();
         float f3 = textureatlassprite.func_94206_g();
         float f4 = textureatlassprite.func_94210_h();
         float f5 = (0.0F - f) / 2.0F;
         float f6 = f5 + f;
         float f7 = 0.0F - f / 2.0F;
         float f8 = f7 + f;
         float f9 = -0.5F;
         GlStateManager.func_179109_b((float)(-(i * 2 - 1)) * 0.24F, -0.3F, 0.0F);
         GlStateManager.func_179114_b((float)(i * 2 - 1) * 10.0F, 0.0F, 1.0F, 0.0F);
         worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
         worldrenderer.func_181662_b((double)f5, (double)f7, (double)f9).func_181673_a((double)f2, (double)f4).func_181675_d();
         worldrenderer.func_181662_b((double)f6, (double)f7, (double)f9).func_181673_a((double)f1, (double)f4).func_181675_d();
         worldrenderer.func_181662_b((double)f6, (double)f8, (double)f9).func_181673_a((double)f1, (double)f3).func_181675_d();
         worldrenderer.func_181662_b((double)f5, (double)f8, (double)f9).func_181673_a((double)f2, (double)f3).func_181675_d();
         tessellator.func_78381_a();
         GlStateManager.func_179121_F();
      }

      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179084_k();
      GlStateManager.func_179132_a(true);
      GlStateManager.func_179143_c(515);
   }

   public void func_78441_a() {
      this.field_78451_d = this.field_78454_c;
      EntityPlayer entityplayer = this.field_78455_a.field_71439_g;
      ItemStack itemstack = entityplayer.field_71071_by.func_70448_g();
      boolean flag = false;
      if(this.field_78453_b != null && itemstack != null) {
         if(!this.field_78453_b.func_179549_c(itemstack)) {
            flag = true;
         }
      } else if(this.field_78453_b == null && itemstack == null) {
         flag = false;
      } else {
         flag = true;
      }

      float f = 0.4F;
      float f1 = flag?0.0F:1.0F;
      float f2 = MathHelper.func_76131_a(f1 - this.field_78454_c, -f, f);
      this.field_78454_c += f2;
      if(this.field_78454_c < 0.1F) {
         this.field_78453_b = itemstack;
         this.field_78450_g = entityplayer.field_71071_by.field_70461_c;
      }

   }

   public void func_78444_b() {
      this.field_78454_c = 0.0F;
   }

   public void func_78445_c() {
      this.field_78454_c = 0.0F;
   }
}

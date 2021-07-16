package net.minecraft.client.renderer.entity;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerDeadmau5Head;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ResourceLocation;

public class RenderPlayer extends RendererLivingEntity<AbstractClientPlayer> {
   private boolean field_177140_a;

   public RenderPlayer(RenderManager p_i46102_1_) {
      this(p_i46102_1_, false);
   }

   public RenderPlayer(RenderManager p_i46103_1_, boolean p_i46103_2_) {
      super(p_i46103_1_, new ModelPlayer(0.0F, p_i46103_2_), 0.5F);
      this.field_177140_a = p_i46103_2_;
      this.func_177094_a(new LayerBipedArmor(this));
      this.func_177094_a(new LayerHeldItem(this));
      this.func_177094_a(new LayerArrow(this));
      this.func_177094_a(new LayerDeadmau5Head(this));
      this.func_177094_a(new LayerCape(this));
      this.func_177094_a(new LayerCustomHead(this.func_177087_b().field_78116_c));
   }

   public ModelPlayer func_177087_b() {
      return (ModelPlayer)super.func_177087_b();
   }

   public void func_76986_a(AbstractClientPlayer p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      if(!p_76986_1_.func_175144_cb() || this.field_76990_c.field_78734_h == p_76986_1_) {
         double d0 = p_76986_4_;
         if(p_76986_1_.func_70093_af() && !(p_76986_1_ instanceof EntityPlayerSP)) {
            d0 = p_76986_4_ - 0.125D;
         }

         this.func_177137_d(p_76986_1_);
         super.func_76986_a(p_76986_1_, p_76986_2_, d0, p_76986_6_, p_76986_8_, p_76986_9_);
      }
   }

   private void func_177137_d(AbstractClientPlayer p_177137_1_) {
      ModelPlayer modelplayer = this.func_177087_b();
      if(p_177137_1_.func_175149_v()) {
         modelplayer.func_178719_a(false);
         modelplayer.field_78116_c.field_78806_j = true;
         modelplayer.field_178720_f.field_78806_j = true;
      } else {
         ItemStack itemstack = p_177137_1_.field_71071_by.func_70448_g();
         modelplayer.func_178719_a(true);
         modelplayer.field_178720_f.field_78806_j = p_177137_1_.func_175148_a(EnumPlayerModelParts.HAT);
         modelplayer.field_178730_v.field_78806_j = p_177137_1_.func_175148_a(EnumPlayerModelParts.JACKET);
         modelplayer.field_178733_c.field_78806_j = p_177137_1_.func_175148_a(EnumPlayerModelParts.LEFT_PANTS_LEG);
         modelplayer.field_178731_d.field_78806_j = p_177137_1_.func_175148_a(EnumPlayerModelParts.RIGHT_PANTS_LEG);
         modelplayer.field_178734_a.field_78806_j = p_177137_1_.func_175148_a(EnumPlayerModelParts.LEFT_SLEEVE);
         modelplayer.field_178732_b.field_78806_j = p_177137_1_.func_175148_a(EnumPlayerModelParts.RIGHT_SLEEVE);
         modelplayer.field_78119_l = 0;
         modelplayer.field_78118_o = false;
         modelplayer.field_78117_n = p_177137_1_.func_70093_af();
         if(itemstack == null) {
            modelplayer.field_78120_m = 0;
         } else {
            modelplayer.field_78120_m = 1;
            if(p_177137_1_.func_71052_bv() > 0) {
               EnumAction enumaction = itemstack.func_77975_n();
               if(enumaction == EnumAction.BLOCK) {
                  modelplayer.field_78120_m = 3;
               } else if(enumaction == EnumAction.BOW) {
                  modelplayer.field_78118_o = true;
               }
            }
         }
      }

   }

   protected ResourceLocation func_110775_a(AbstractClientPlayer p_110775_1_) {
      return p_110775_1_.func_110306_p();
   }

   public void func_82422_c() {
      GlStateManager.func_179109_b(0.0F, 0.1875F, 0.0F);
   }

   protected void func_77041_b(AbstractClientPlayer p_77041_1_, float p_77041_2_) {
      float f = 0.9375F;
      GlStateManager.func_179152_a(f, f, f);
   }

   protected void func_177069_a(AbstractClientPlayer p_177069_1_, double p_177069_2_, double p_177069_4_, double p_177069_6_, String p_177069_8_, float p_177069_9_, double p_177069_10_) {
      if(p_177069_10_ < 100.0D) {
         Scoreboard scoreboard = p_177069_1_.func_96123_co();
         ScoreObjective scoreobjective = scoreboard.func_96539_a(2);
         if(scoreobjective != null) {
            Score score = scoreboard.func_96529_a(p_177069_1_.func_70005_c_(), scoreobjective);
            this.func_147906_a(p_177069_1_, score.func_96652_c() + " " + scoreobjective.func_96678_d(), p_177069_2_, p_177069_4_, p_177069_6_, 64);
            p_177069_4_ += (double)((float)this.func_76983_a().field_78288_b * 1.15F * p_177069_9_);
         }
      }

      super.func_177069_a(p_177069_1_, p_177069_2_, p_177069_4_, p_177069_6_, p_177069_8_, p_177069_9_, p_177069_10_);
   }

   public void func_177138_b(AbstractClientPlayer p_177138_1_) {
      float f = 1.0F;
      GlStateManager.func_179124_c(f, f, f);
      ModelPlayer modelplayer = this.func_177087_b();
      this.func_177137_d(p_177138_1_);
      modelplayer.field_78095_p = 0.0F;
      modelplayer.field_78117_n = false;
      modelplayer.func_78087_a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, p_177138_1_);
      modelplayer.func_178725_a();
   }

   public void func_177139_c(AbstractClientPlayer p_177139_1_) {
      float f = 1.0F;
      GlStateManager.func_179124_c(f, f, f);
      ModelPlayer modelplayer = this.func_177087_b();
      this.func_177137_d(p_177139_1_);
      modelplayer.field_78117_n = false;
      modelplayer.field_78095_p = 0.0F;
      modelplayer.func_78087_a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, p_177139_1_);
      modelplayer.func_178726_b();
   }

   protected void func_77039_a(AbstractClientPlayer p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_) {
      if(p_77039_1_.func_70089_S() && p_77039_1_.func_70608_bn()) {
         super.func_77039_a(p_77039_1_, p_77039_2_ + (double)p_77039_1_.field_71079_bU, p_77039_4_ + (double)p_77039_1_.field_71082_cx, p_77039_6_ + (double)p_77039_1_.field_71089_bV);
      } else {
         super.func_77039_a(p_77039_1_, p_77039_2_, p_77039_4_, p_77039_6_);
      }

   }

   protected void func_77043_a(AbstractClientPlayer p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
      if(p_77043_1_.func_70089_S() && p_77043_1_.func_70608_bn()) {
         GlStateManager.func_179114_b(p_77043_1_.func_71051_bG(), 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179114_b(this.func_77037_a(p_77043_1_), 0.0F, 0.0F, 1.0F);
         GlStateManager.func_179114_b(270.0F, 0.0F, 1.0F, 0.0F);
      } else {
         super.func_77043_a(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
      }

   }
}

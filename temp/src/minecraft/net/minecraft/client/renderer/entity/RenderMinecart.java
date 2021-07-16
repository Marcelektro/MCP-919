package net.minecraft.client.renderer.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class RenderMinecart<T extends EntityMinecart> extends Render<T> {
   private static final ResourceLocation field_110804_g = new ResourceLocation("textures/entity/minecart.png");
   protected ModelBase field_77013_a = new ModelMinecart();

   public RenderMinecart(RenderManager p_i46155_1_) {
      super(p_i46155_1_);
      this.field_76989_e = 0.5F;
   }

   public void func_76986_a(T p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      GlStateManager.func_179094_E();
      this.func_180548_c(p_76986_1_);
      long i = (long)p_76986_1_.func_145782_y() * 493286711L;
      i = i * i * 4392167121L + i * 98761L;
      float f = (((float)(i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float f1 = (((float)(i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float f2 = (((float)(i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      GlStateManager.func_179109_b(f, f1, f2);
      double d0 = p_76986_1_.field_70142_S + (p_76986_1_.field_70165_t - p_76986_1_.field_70142_S) * (double)p_76986_9_;
      double d1 = p_76986_1_.field_70137_T + (p_76986_1_.field_70163_u - p_76986_1_.field_70137_T) * (double)p_76986_9_;
      double d2 = p_76986_1_.field_70136_U + (p_76986_1_.field_70161_v - p_76986_1_.field_70136_U) * (double)p_76986_9_;
      double d3 = 0.30000001192092896D;
      Vec3 vec3 = p_76986_1_.func_70489_a(d0, d1, d2);
      float f3 = p_76986_1_.field_70127_C + (p_76986_1_.field_70125_A - p_76986_1_.field_70127_C) * p_76986_9_;
      if(vec3 != null) {
         Vec3 vec31 = p_76986_1_.func_70495_a(d0, d1, d2, d3);
         Vec3 vec32 = p_76986_1_.func_70495_a(d0, d1, d2, -d3);
         if(vec31 == null) {
            vec31 = vec3;
         }

         if(vec32 == null) {
            vec32 = vec3;
         }

         p_76986_2_ += vec3.field_72450_a - d0;
         p_76986_4_ += (vec31.field_72448_b + vec32.field_72448_b) / 2.0D - d1;
         p_76986_6_ += vec3.field_72449_c - d2;
         Vec3 vec33 = vec32.func_72441_c(-vec31.field_72450_a, -vec31.field_72448_b, -vec31.field_72449_c);
         if(vec33.func_72433_c() != 0.0D) {
            vec33 = vec33.func_72432_b();
            p_76986_8_ = (float)(Math.atan2(vec33.field_72449_c, vec33.field_72450_a) * 180.0D / 3.141592653589793D);
            f3 = (float)(Math.atan(vec33.field_72448_b) * 73.0D);
         }
      }

      GlStateManager.func_179109_b((float)p_76986_2_, (float)p_76986_4_ + 0.375F, (float)p_76986_6_);
      GlStateManager.func_179114_b(180.0F - p_76986_8_, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(-f3, 0.0F, 0.0F, 1.0F);
      float f5 = (float)p_76986_1_.func_70496_j() - p_76986_9_;
      float f6 = p_76986_1_.func_70491_i() - p_76986_9_;
      if(f6 < 0.0F) {
         f6 = 0.0F;
      }

      if(f5 > 0.0F) {
         GlStateManager.func_179114_b(MathHelper.func_76126_a(f5) * f5 * f6 / 10.0F * (float)p_76986_1_.func_70493_k(), 1.0F, 0.0F, 0.0F);
      }

      int j = p_76986_1_.func_94099_q();
      IBlockState iblockstate = p_76986_1_.func_174897_t();
      if(iblockstate.func_177230_c().func_149645_b() != -1) {
         GlStateManager.func_179094_E();
         this.func_110776_a(TextureMap.field_110575_b);
         float f4 = 0.75F;
         GlStateManager.func_179152_a(f4, f4, f4);
         GlStateManager.func_179109_b(-0.5F, (float)(j - 8) / 16.0F, 0.5F);
         this.func_180560_a(p_76986_1_, p_76986_9_, iblockstate);
         GlStateManager.func_179121_F();
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         this.func_180548_c(p_76986_1_);
      }

      GlStateManager.func_179152_a(-1.0F, -1.0F, 1.0F);
      this.field_77013_a.func_78088_a(p_76986_1_, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      GlStateManager.func_179121_F();
      super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation func_110775_a(T p_110775_1_) {
      return field_110804_g;
   }

   protected void func_180560_a(T p_180560_1_, float p_180560_2_, IBlockState p_180560_3_) {
      GlStateManager.func_179094_E();
      Minecraft.func_71410_x().func_175602_ab().func_175016_a(p_180560_3_, p_180560_1_.func_70013_c(p_180560_2_));
      GlStateManager.func_179121_F();
   }
}

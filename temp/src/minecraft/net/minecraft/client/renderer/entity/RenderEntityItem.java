package net.minecraft.client.renderer.entity;

import java.util.Random;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderEntityItem extends Render<EntityItem> {
   private final RenderItem field_177080_a;
   private Random field_177079_e = new Random();

   public RenderEntityItem(RenderManager p_i46167_1_, RenderItem p_i46167_2_) {
      super(p_i46167_1_);
      this.field_177080_a = p_i46167_2_;
      this.field_76989_e = 0.15F;
      this.field_76987_f = 0.75F;
   }

   private int func_177077_a(EntityItem p_177077_1_, double p_177077_2_, double p_177077_4_, double p_177077_6_, float p_177077_8_, IBakedModel p_177077_9_) {
      ItemStack itemstack = p_177077_1_.func_92059_d();
      Item item = itemstack.func_77973_b();
      if(item == null) {
         return 0;
      } else {
         boolean flag = p_177077_9_.func_177556_c();
         int i = this.func_177078_a(itemstack);
         float f = 0.25F;
         float f1 = MathHelper.func_76126_a(((float)p_177077_1_.func_174872_o() + p_177077_8_) / 10.0F + p_177077_1_.field_70290_d) * 0.1F + 0.1F;
         float f2 = p_177077_9_.func_177552_f().func_181688_b(ItemCameraTransforms.TransformType.GROUND).field_178363_d.y;
         GlStateManager.func_179109_b((float)p_177077_2_, (float)p_177077_4_ + f1 + 0.25F * f2, (float)p_177077_6_);
         if(flag || this.field_76990_c.field_78733_k != null) {
            float f3 = (((float)p_177077_1_.func_174872_o() + p_177077_8_) / 20.0F + p_177077_1_.field_70290_d) * 57.295776F;
            GlStateManager.func_179114_b(f3, 0.0F, 1.0F, 0.0F);
         }

         if(!flag) {
            float f6 = -0.0F * (float)(i - 1) * 0.5F;
            float f4 = -0.0F * (float)(i - 1) * 0.5F;
            float f5 = -0.046875F * (float)(i - 1) * 0.5F;
            GlStateManager.func_179109_b(f6, f4, f5);
         }

         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         return i;
      }
   }

   private int func_177078_a(ItemStack p_177078_1_) {
      int i = 1;
      if(p_177078_1_.field_77994_a > 48) {
         i = 5;
      } else if(p_177078_1_.field_77994_a > 32) {
         i = 4;
      } else if(p_177078_1_.field_77994_a > 16) {
         i = 3;
      } else if(p_177078_1_.field_77994_a > 1) {
         i = 2;
      }

      return i;
   }

   public void func_76986_a(EntityItem p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      ItemStack itemstack = p_76986_1_.func_92059_d();
      this.field_177079_e.setSeed(187L);
      boolean flag = false;
      if(this.func_180548_c(p_76986_1_)) {
         this.field_76990_c.field_78724_e.func_110581_b(this.func_110775_a(p_76986_1_)).func_174936_b(false, false);
         flag = true;
      }

      GlStateManager.func_179091_B();
      GlStateManager.func_179092_a(516, 0.1F);
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179094_E();
      IBakedModel ibakedmodel = this.field_177080_a.func_175037_a().func_178089_a(itemstack);
      int i = this.func_177077_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_9_, ibakedmodel);

      for(int j = 0; j < i; ++j) {
         if(ibakedmodel.func_177556_c()) {
            GlStateManager.func_179094_E();
            if(j > 0) {
               float f = (this.field_177079_e.nextFloat() * 2.0F - 1.0F) * 0.15F;
               float f1 = (this.field_177079_e.nextFloat() * 2.0F - 1.0F) * 0.15F;
               float f2 = (this.field_177079_e.nextFloat() * 2.0F - 1.0F) * 0.15F;
               GlStateManager.func_179109_b(f, f1, f2);
            }

            GlStateManager.func_179152_a(0.5F, 0.5F, 0.5F);
            ibakedmodel.func_177552_f().func_181689_a(ItemCameraTransforms.TransformType.GROUND);
            this.field_177080_a.func_180454_a(itemstack, ibakedmodel);
            GlStateManager.func_179121_F();
         } else {
            GlStateManager.func_179094_E();
            ibakedmodel.func_177552_f().func_181689_a(ItemCameraTransforms.TransformType.GROUND);
            this.field_177080_a.func_180454_a(itemstack, ibakedmodel);
            GlStateManager.func_179121_F();
            float f3 = ibakedmodel.func_177552_f().field_181699_o.field_178363_d.x;
            float f4 = ibakedmodel.func_177552_f().field_181699_o.field_178363_d.y;
            float f5 = ibakedmodel.func_177552_f().field_181699_o.field_178363_d.z;
            GlStateManager.func_179109_b(0.0F * f3, 0.0F * f4, 0.046875F * f5);
         }
      }

      GlStateManager.func_179121_F();
      GlStateManager.func_179101_C();
      GlStateManager.func_179084_k();
      this.func_180548_c(p_76986_1_);
      if(flag) {
         this.field_76990_c.field_78724_e.func_110581_b(this.func_110775_a(p_76986_1_)).func_174935_a();
      }

      super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation func_110775_a(EntityItem p_110775_1_) {
      return TextureMap.field_110575_b;
   }
}

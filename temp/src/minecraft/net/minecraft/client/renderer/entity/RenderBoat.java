package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderBoat extends Render<EntityBoat> {
   private static final ResourceLocation field_110782_f = new ResourceLocation("textures/entity/boat.png");
   protected ModelBase field_76998_a = new ModelBoat();

   public RenderBoat(RenderManager p_i46190_1_) {
      super(p_i46190_1_);
      this.field_76989_e = 0.5F;
   }

   public void func_76986_a(EntityBoat p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b((float)p_76986_2_, (float)p_76986_4_ + 0.25F, (float)p_76986_6_);
      GlStateManager.func_179114_b(180.0F - p_76986_8_, 0.0F, 1.0F, 0.0F);
      float f = (float)p_76986_1_.func_70268_h() - p_76986_9_;
      float f1 = p_76986_1_.func_70271_g() - p_76986_9_;
      if(f1 < 0.0F) {
         f1 = 0.0F;
      }

      if(f > 0.0F) {
         GlStateManager.func_179114_b(MathHelper.func_76126_a(f) * f * f1 / 10.0F * (float)p_76986_1_.func_70267_i(), 1.0F, 0.0F, 0.0F);
      }

      float f2 = 0.75F;
      GlStateManager.func_179152_a(f2, f2, f2);
      GlStateManager.func_179152_a(1.0F / f2, 1.0F / f2, 1.0F / f2);
      this.func_180548_c(p_76986_1_);
      GlStateManager.func_179152_a(-1.0F, -1.0F, 1.0F);
      this.field_76998_a.func_78088_a(p_76986_1_, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      GlStateManager.func_179121_F();
      super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation func_110775_a(EntityBoat p_110775_1_) {
      return field_110782_f;
   }
}

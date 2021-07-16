package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderChicken extends RenderLiving<EntityChicken> {
   private static final ResourceLocation field_110920_a = new ResourceLocation("textures/entity/chicken.png");

   public RenderChicken(RenderManager p_i46188_1_, ModelBase p_i46188_2_, float p_i46188_3_) {
      super(p_i46188_1_, p_i46188_2_, p_i46188_3_);
   }

   protected ResourceLocation func_110775_a(EntityChicken p_110775_1_) {
      return field_110920_a;
   }

   protected float func_77044_a(EntityChicken p_77044_1_, float p_77044_2_) {
      float f = p_77044_1_.field_70888_h + (p_77044_1_.field_70886_e - p_77044_1_.field_70888_h) * p_77044_2_;
      float f1 = p_77044_1_.field_70884_g + (p_77044_1_.field_70883_f - p_77044_1_.field_70884_g) * p_77044_2_;
      return (MathHelper.func_76126_a(f) + 1.0F) * f1;
   }
}

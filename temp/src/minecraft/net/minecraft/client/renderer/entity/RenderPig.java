package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerSaddle;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;

public class RenderPig extends RenderLiving<EntityPig> {
   private static final ResourceLocation field_110887_f = new ResourceLocation("textures/entity/pig/pig.png");

   public RenderPig(RenderManager p_i46149_1_, ModelBase p_i46149_2_, float p_i46149_3_) {
      super(p_i46149_1_, p_i46149_2_, p_i46149_3_);
      this.func_177094_a(new LayerSaddle(this));
   }

   protected ResourceLocation func_110775_a(EntityPig p_110775_1_) {
      return field_110887_f;
   }
}

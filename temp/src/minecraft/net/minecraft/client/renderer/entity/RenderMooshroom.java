package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerMooshroomMushroom;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.util.ResourceLocation;

public class RenderMooshroom extends RenderLiving<EntityMooshroom> {
   private static final ResourceLocation field_110880_a = new ResourceLocation("textures/entity/cow/mooshroom.png");

   public RenderMooshroom(RenderManager p_i46152_1_, ModelBase p_i46152_2_, float p_i46152_3_) {
      super(p_i46152_1_, p_i46152_2_, p_i46152_3_);
      this.func_177094_a(new LayerMooshroomMushroom(this));
   }

   protected ResourceLocation func_110775_a(EntityMooshroom p_110775_1_) {
      return field_110880_a;
   }
}

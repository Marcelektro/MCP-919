package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerSheepWool;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

public class RenderSheep extends RenderLiving<EntitySheep> {
   private static final ResourceLocation field_110884_f = new ResourceLocation("textures/entity/sheep/sheep.png");

   public RenderSheep(RenderManager p_i46145_1_, ModelBase p_i46145_2_, float p_i46145_3_) {
      super(p_i46145_1_, p_i46145_2_, p_i46145_3_);
      this.func_177094_a(new LayerSheepWool(this));
   }

   protected ResourceLocation func_110775_a(EntitySheep p_110775_1_) {
      return field_110884_f;
   }
}

package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderBiped<T extends EntityLiving> extends RenderLiving<T> {
   private static final ResourceLocation field_177118_j = new ResourceLocation("textures/entity/steve.png");
   protected ModelBiped field_77071_a;
   protected float field_77070_b;

   public RenderBiped(RenderManager p_i46168_1_, ModelBiped p_i46168_2_, float p_i46168_3_) {
      this(p_i46168_1_, p_i46168_2_, p_i46168_3_, 1.0F);
      this.func_177094_a(new LayerHeldItem(this));
   }

   public RenderBiped(RenderManager p_i46169_1_, ModelBiped p_i46169_2_, float p_i46169_3_, float p_i46169_4_) {
      super(p_i46169_1_, p_i46169_2_, p_i46169_3_);
      this.field_77071_a = p_i46169_2_;
      this.field_77070_b = p_i46169_4_;
      this.func_177094_a(new LayerCustomHead(p_i46169_2_.field_78116_c));
   }

   protected ResourceLocation func_110775_a(T p_110775_1_) {
      return field_177118_j;
   }

   public void func_82422_c() {
      GlStateManager.func_179109_b(0.0F, 0.1875F, 0.0F);
   }
}

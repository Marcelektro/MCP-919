package net.minecraft.client.renderer.entity;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerEndermanEyes;
import net.minecraft.client.renderer.entity.layers.LayerHeldBlock;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;

public class RenderEnderman extends RenderLiving<EntityEnderman> {
   private static final ResourceLocation field_110839_f = new ResourceLocation("textures/entity/enderman/enderman.png");
   private ModelEnderman field_77078_a;
   private Random field_77077_b = new Random();

   public RenderEnderman(RenderManager p_i46182_1_) {
      super(p_i46182_1_, new ModelEnderman(0.0F), 0.5F);
      this.field_77078_a = (ModelEnderman)super.field_77045_g;
      this.func_177094_a(new LayerEndermanEyes(this));
      this.func_177094_a(new LayerHeldBlock(this));
   }

   public void func_76986_a(EntityEnderman p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.field_77078_a.field_78126_a = p_76986_1_.func_175489_ck().func_177230_c().func_149688_o() != Material.field_151579_a;
      this.field_77078_a.field_78125_b = p_76986_1_.func_70823_r();
      if(p_76986_1_.func_70823_r()) {
         double d0 = 0.02D;
         p_76986_2_ += this.field_77077_b.nextGaussian() * d0;
         p_76986_6_ += this.field_77077_b.nextGaussian() * d0;
      }

      super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation func_110775_a(EntityEnderman p_110775_1_) {
      return field_110839_f;
   }
}

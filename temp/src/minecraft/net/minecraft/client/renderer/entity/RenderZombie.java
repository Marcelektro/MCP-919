package net.minecraft.client.renderer.entity;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerVillagerArmor;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class RenderZombie extends RenderBiped<EntityZombie> {
   private static final ResourceLocation field_110865_p = new ResourceLocation("textures/entity/zombie/zombie.png");
   private static final ResourceLocation field_110864_q = new ResourceLocation("textures/entity/zombie/zombie_villager.png");
   private final ModelBiped field_82434_o;
   private final ModelZombieVillager field_82432_p;
   private final List<LayerRenderer<EntityZombie>> field_177121_n;
   private final List<LayerRenderer<EntityZombie>> field_177122_o;

   public RenderZombie(RenderManager p_i46127_1_) {
      super(p_i46127_1_, new ModelZombie(), 0.5F, 1.0F);
      LayerRenderer layerrenderer = (LayerRenderer)this.field_177097_h.get(0);
      this.field_82434_o = this.field_77071_a;
      this.field_82432_p = new ModelZombieVillager();
      this.func_177094_a(new LayerHeldItem(this));
      LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this) {
         protected void func_177177_a() {
            this.field_177189_c = new ModelZombie(0.5F, true);
            this.field_177186_d = new ModelZombie(1.0F, true);
         }
      };
      this.func_177094_a(layerbipedarmor);
      this.field_177122_o = Lists.newArrayList(this.field_177097_h);
      if(layerrenderer instanceof LayerCustomHead) {
         this.func_177089_b(layerrenderer);
         this.func_177094_a(new LayerCustomHead(this.field_82432_p.field_78116_c));
      }

      this.func_177089_b(layerbipedarmor);
      this.func_177094_a(new LayerVillagerArmor(this));
      this.field_177121_n = Lists.newArrayList(this.field_177097_h);
   }

   public void func_76986_a(EntityZombie p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.func_82427_a(p_76986_1_);
      super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation func_110775_a(EntityZombie p_110775_1_) {
      return p_110775_1_.func_82231_m()?field_110864_q:field_110865_p;
   }

   private void func_82427_a(EntityZombie p_82427_1_) {
      if(p_82427_1_.func_82231_m()) {
         this.field_77045_g = this.field_82432_p;
         this.field_177097_h = this.field_177121_n;
      } else {
         this.field_77045_g = this.field_82434_o;
         this.field_177097_h = this.field_177122_o;
      }

      this.field_77071_a = (ModelBiped)this.field_77045_g;
   }

   protected void func_77043_a(EntityZombie p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
      if(p_77043_1_.func_82230_o()) {
         p_77043_3_ += (float)(Math.cos((double)p_77043_1_.field_70173_aa * 3.25D) * 3.141592653589793D * 0.25D);
      }

      super.func_77043_a(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
   }
}

package net.minecraft.client.renderer.entity;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.ResourceLocation;

public class RenderHorse extends RenderLiving<EntityHorse> {
   private static final Map<String, ResourceLocation> field_110852_a = Maps.<String, ResourceLocation>newHashMap();
   private static final ResourceLocation field_110850_f = new ResourceLocation("textures/entity/horse/horse_white.png");
   private static final ResourceLocation field_110851_g = new ResourceLocation("textures/entity/horse/mule.png");
   private static final ResourceLocation field_110855_h = new ResourceLocation("textures/entity/horse/donkey.png");
   private static final ResourceLocation field_110854_k = new ResourceLocation("textures/entity/horse/horse_zombie.png");
   private static final ResourceLocation field_110853_l = new ResourceLocation("textures/entity/horse/horse_skeleton.png");

   public RenderHorse(RenderManager p_i46170_1_, ModelHorse p_i46170_2_, float p_i46170_3_) {
      super(p_i46170_1_, p_i46170_2_, p_i46170_3_);
   }

   protected void func_77041_b(EntityHorse p_77041_1_, float p_77041_2_) {
      float f = 1.0F;
      int i = p_77041_1_.func_110265_bP();
      if(i == 1) {
         f *= 0.87F;
      } else if(i == 2) {
         f *= 0.92F;
      }

      GlStateManager.func_179152_a(f, f, f);
      super.func_77041_b(p_77041_1_, p_77041_2_);
   }

   protected ResourceLocation func_110775_a(EntityHorse p_110775_1_) {
      if(!p_110775_1_.func_110239_cn()) {
         switch(p_110775_1_.func_110265_bP()) {
         case 0:
         default:
            return field_110850_f;
         case 1:
            return field_110855_h;
         case 2:
            return field_110851_g;
         case 3:
            return field_110854_k;
         case 4:
            return field_110853_l;
         }
      } else {
         return this.func_110848_b(p_110775_1_);
      }
   }

   private ResourceLocation func_110848_b(EntityHorse p_110848_1_) {
      String s = p_110848_1_.func_110264_co();
      if(!p_110848_1_.func_175507_cI()) {
         return null;
      } else {
         ResourceLocation resourcelocation = (ResourceLocation)field_110852_a.get(s);
         if(resourcelocation == null) {
            resourcelocation = new ResourceLocation(s);
            Minecraft.func_71410_x().func_110434_K().func_110579_a(resourcelocation, new LayeredTexture(p_110848_1_.func_110212_cp()));
            field_110852_a.put(s, resourcelocation);
         }

         return resourcelocation;
      }
   }
}

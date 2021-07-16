package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAISwimming extends EntityAIBase {
   private EntityLiving field_75373_a;

   public EntityAISwimming(EntityLiving p_i1624_1_) {
      this.field_75373_a = p_i1624_1_;
      this.func_75248_a(4);
      ((PathNavigateGround)p_i1624_1_.func_70661_as()).func_179693_d(true);
   }

   public boolean func_75250_a() {
      return this.field_75373_a.func_70090_H() || this.field_75373_a.func_180799_ab();
   }

   public void func_75246_d() {
      if(this.field_75373_a.func_70681_au().nextFloat() < 0.8F) {
         this.field_75373_a.func_70683_ar().func_75660_a();
      }

   }
}

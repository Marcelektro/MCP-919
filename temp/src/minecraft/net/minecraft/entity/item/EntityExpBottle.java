package net.minecraft.entity.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityExpBottle extends EntityThrowable {
   public EntityExpBottle(World p_i1785_1_) {
      super(p_i1785_1_);
   }

   public EntityExpBottle(World p_i1786_1_, EntityLivingBase p_i1786_2_) {
      super(p_i1786_1_, p_i1786_2_);
   }

   public EntityExpBottle(World p_i1787_1_, double p_i1787_2_, double p_i1787_4_, double p_i1787_6_) {
      super(p_i1787_1_, p_i1787_2_, p_i1787_4_, p_i1787_6_);
   }

   protected float func_70185_h() {
      return 0.07F;
   }

   protected float func_70182_d() {
      return 0.7F;
   }

   protected float func_70183_g() {
      return -20.0F;
   }

   protected void func_70184_a(MovingObjectPosition p_70184_1_) {
      if(!this.field_70170_p.field_72995_K) {
         this.field_70170_p.func_175718_b(2002, new BlockPos(this), 0);
         int i = 3 + this.field_70170_p.field_73012_v.nextInt(5) + this.field_70170_p.field_73012_v.nextInt(5);

         while(i > 0) {
            int j = EntityXPOrb.func_70527_a(i);
            i -= j;
            this.field_70170_p.func_72838_d(new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
         }

         this.func_70106_y();
      }

   }
}

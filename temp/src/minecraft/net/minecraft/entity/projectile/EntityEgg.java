package net.minecraft.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEgg extends EntityThrowable {
   public EntityEgg(World p_i1779_1_) {
      super(p_i1779_1_);
   }

   public EntityEgg(World p_i1780_1_, EntityLivingBase p_i1780_2_) {
      super(p_i1780_1_, p_i1780_2_);
   }

   public EntityEgg(World p_i1781_1_, double p_i1781_2_, double p_i1781_4_, double p_i1781_6_) {
      super(p_i1781_1_, p_i1781_2_, p_i1781_4_, p_i1781_6_);
   }

   protected void func_70184_a(MovingObjectPosition p_70184_1_) {
      if(p_70184_1_.field_72308_g != null) {
         p_70184_1_.field_72308_g.func_70097_a(DamageSource.func_76356_a(this, this.func_85052_h()), 0.0F);
      }

      if(!this.field_70170_p.field_72995_K && this.field_70146_Z.nextInt(8) == 0) {
         int i = 1;
         if(this.field_70146_Z.nextInt(32) == 0) {
            i = 4;
         }

         for(int j = 0; j < i; ++j) {
            EntityChicken entitychicken = new EntityChicken(this.field_70170_p);
            entitychicken.func_70873_a(-24000);
            entitychicken.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, 0.0F);
            this.field_70170_p.func_72838_d(entitychicken);
         }
      }

      double d0 = 0.08D;

      for(int k = 0; k < 8; ++k) {
         this.field_70170_p.func_175688_a(EnumParticleTypes.ITEM_CRACK, this.field_70165_t, this.field_70163_u, this.field_70161_v, ((double)this.field_70146_Z.nextFloat() - 0.5D) * 0.08D, ((double)this.field_70146_Z.nextFloat() - 0.5D) * 0.08D, ((double)this.field_70146_Z.nextFloat() - 0.5D) * 0.08D, new int[]{Item.func_150891_b(Items.field_151110_aK)});
      }

      if(!this.field_70170_p.field_72995_K) {
         this.func_70106_y();
      }

   }
}

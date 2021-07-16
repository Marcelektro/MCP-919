package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityMinecartEmpty extends EntityMinecart {
   public EntityMinecartEmpty(World p_i1722_1_) {
      super(p_i1722_1_);
   }

   public EntityMinecartEmpty(World p_i1723_1_, double p_i1723_2_, double p_i1723_4_, double p_i1723_6_) {
      super(p_i1723_1_, p_i1723_2_, p_i1723_4_, p_i1723_6_);
   }

   public boolean func_130002_c(EntityPlayer p_130002_1_) {
      if(this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer && this.field_70153_n != p_130002_1_) {
         return true;
      } else if(this.field_70153_n != null && this.field_70153_n != p_130002_1_) {
         return false;
      } else {
         if(!this.field_70170_p.field_72995_K) {
            p_130002_1_.func_70078_a(this);
         }

         return true;
      }
   }

   public void func_96095_a(int p_96095_1_, int p_96095_2_, int p_96095_3_, boolean p_96095_4_) {
      if(p_96095_4_) {
         if(this.field_70153_n != null) {
            this.field_70153_n.func_70078_a((Entity)null);
         }

         if(this.func_70496_j() == 0) {
            this.func_70494_i(-this.func_70493_k());
            this.func_70497_h(10);
            this.func_70492_c(50.0F);
            this.func_70018_K();
         }
      }

   }

   public EntityMinecart.EnumMinecartType func_180456_s() {
      return EntityMinecart.EnumMinecartType.RIDEABLE;
   }
}

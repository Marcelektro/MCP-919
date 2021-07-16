package net.minecraft.entity.passive;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class EntityAmbientCreature extends EntityLiving implements IAnimals {
   public EntityAmbientCreature(World p_i1679_1_) {
      super(p_i1679_1_);
   }

   public boolean func_110164_bC() {
      return false;
   }

   protected boolean func_70085_c(EntityPlayer p_70085_1_) {
      return false;
   }
}

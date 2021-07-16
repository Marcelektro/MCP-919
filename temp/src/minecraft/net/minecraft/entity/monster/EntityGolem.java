package net.minecraft.entity.monster;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.world.World;

public abstract class EntityGolem extends EntityCreature implements IAnimals {
   public EntityGolem(World p_i1686_1_) {
      super(p_i1686_1_);
   }

   public void func_180430_e(float p_180430_1_, float p_180430_2_) {
   }

   protected String func_70639_aQ() {
      return "none";
   }

   protected String func_70621_aR() {
      return "none";
   }

   protected String func_70673_aS() {
      return "none";
   }

   public int func_70627_aG() {
      return 120;
   }

   protected boolean func_70692_ba() {
      return false;
   }
}

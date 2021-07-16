package net.minecraft.inventory;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.IChatComponent;

public class AnimalChest extends InventoryBasic {
   public AnimalChest(String p_i1796_1_, int p_i1796_2_) {
      super(p_i1796_1_, false, p_i1796_2_);
   }

   public AnimalChest(IChatComponent p_i45808_1_, int p_i45808_2_) {
      super(p_i45808_1_, p_i45808_2_);
   }
}

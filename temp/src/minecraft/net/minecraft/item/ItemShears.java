package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemShears extends Item {
   public ItemShears() {
      this.func_77625_d(1);
      this.func_77656_e(238);
      this.func_77637_a(CreativeTabs.field_78040_i);
   }

   public boolean func_179218_a(ItemStack p_179218_1_, World p_179218_2_, Block p_179218_3_, BlockPos p_179218_4_, EntityLivingBase p_179218_5_) {
      if(p_179218_3_.func_149688_o() != Material.field_151584_j && p_179218_3_ != Blocks.field_150321_G && p_179218_3_ != Blocks.field_150329_H && p_179218_3_ != Blocks.field_150395_bd && p_179218_3_ != Blocks.field_150473_bD && p_179218_3_ != Blocks.field_150325_L) {
         return super.func_179218_a(p_179218_1_, p_179218_2_, p_179218_3_, p_179218_4_, p_179218_5_);
      } else {
         p_179218_1_.func_77972_a(1, p_179218_5_);
         return true;
      }
   }

   public boolean func_150897_b(Block p_150897_1_) {
      return p_150897_1_ == Blocks.field_150321_G || p_150897_1_ == Blocks.field_150488_af || p_150897_1_ == Blocks.field_150473_bD;
   }

   public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_) {
      return p_150893_2_ != Blocks.field_150321_G && p_150893_2_.func_149688_o() != Material.field_151584_j?(p_150893_2_ == Blocks.field_150325_L?5.0F:super.func_150893_a(p_150893_1_, p_150893_2_)):15.0F;
   }
}

package net.minecraft.item;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemFlintAndSteel extends Item {
   public ItemFlintAndSteel() {
      this.field_77777_bU = 1;
      this.func_77656_e(64);
      this.func_77637_a(CreativeTabs.field_78040_i);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      p_180614_4_ = p_180614_4_.func_177972_a(p_180614_5_);
      if(!p_180614_2_.func_175151_a(p_180614_4_, p_180614_5_, p_180614_1_)) {
         return false;
      } else {
         if(p_180614_3_.func_180495_p(p_180614_4_).func_177230_c().func_149688_o() == Material.field_151579_a) {
            p_180614_3_.func_72908_a((double)p_180614_4_.func_177958_n() + 0.5D, (double)p_180614_4_.func_177956_o() + 0.5D, (double)p_180614_4_.func_177952_p() + 0.5D, "fire.ignite", 1.0F, field_77697_d.nextFloat() * 0.4F + 0.8F);
            p_180614_3_.func_175656_a(p_180614_4_, Blocks.field_150480_ab.func_176223_P());
         }

         p_180614_1_.func_77972_a(1, p_180614_2_);
         return true;
      }
   }
}

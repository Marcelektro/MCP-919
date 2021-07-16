package net.minecraft.block;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockPotato extends BlockCrops {
   protected Item func_149866_i() {
      return Items.field_151174_bG;
   }

   protected Item func_149865_P() {
      return Items.field_151174_bG;
   }

   public void func_180653_a(World p_180653_1_, BlockPos p_180653_2_, IBlockState p_180653_3_, float p_180653_4_, int p_180653_5_) {
      super.func_180653_a(p_180653_1_, p_180653_2_, p_180653_3_, p_180653_4_, p_180653_5_);
      if(!p_180653_1_.field_72995_K) {
         if(((Integer)p_180653_3_.func_177229_b(field_176488_a)).intValue() >= 7 && p_180653_1_.field_73012_v.nextInt(50) == 0) {
            func_180635_a(p_180653_1_, p_180653_2_, new ItemStack(Items.field_151170_bI));
         }

      }
   }
}

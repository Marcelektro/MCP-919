package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemHoe extends Item {
   protected Item.ToolMaterial field_77843_a;

   public ItemHoe(Item.ToolMaterial p_i45343_1_) {
      this.field_77843_a = p_i45343_1_;
      this.field_77777_bU = 1;
      this.func_77656_e(p_i45343_1_.func_77997_a());
      this.func_77637_a(CreativeTabs.field_78040_i);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if(!p_180614_2_.func_175151_a(p_180614_4_.func_177972_a(p_180614_5_), p_180614_5_, p_180614_1_)) {
         return false;
      } else {
         IBlockState iblockstate = p_180614_3_.func_180495_p(p_180614_4_);
         Block block = iblockstate.func_177230_c();
         if(p_180614_5_ != EnumFacing.DOWN && p_180614_3_.func_180495_p(p_180614_4_.func_177984_a()).func_177230_c().func_149688_o() == Material.field_151579_a) {
            if(block == Blocks.field_150349_c) {
               return this.func_179232_a(p_180614_1_, p_180614_2_, p_180614_3_, p_180614_4_, Blocks.field_150458_ak.func_176223_P());
            }

            if(block == Blocks.field_150346_d) {
               switch((BlockDirt.DirtType)iblockstate.func_177229_b(BlockDirt.field_176386_a)) {
               case DIRT:
                  return this.func_179232_a(p_180614_1_, p_180614_2_, p_180614_3_, p_180614_4_, Blocks.field_150458_ak.func_176223_P());
               case COARSE_DIRT:
                  return this.func_179232_a(p_180614_1_, p_180614_2_, p_180614_3_, p_180614_4_, Blocks.field_150346_d.func_176223_P().func_177226_a(BlockDirt.field_176386_a, BlockDirt.DirtType.DIRT));
               }
            }
         }

         return false;
      }
   }

   protected boolean func_179232_a(ItemStack p_179232_1_, EntityPlayer p_179232_2_, World p_179232_3_, BlockPos p_179232_4_, IBlockState p_179232_5_) {
      p_179232_3_.func_72908_a((double)((float)p_179232_4_.func_177958_n() + 0.5F), (double)((float)p_179232_4_.func_177956_o() + 0.5F), (double)((float)p_179232_4_.func_177952_p() + 0.5F), p_179232_5_.func_177230_c().field_149762_H.func_150498_e(), (p_179232_5_.func_177230_c().field_149762_H.func_150497_c() + 1.0F) / 2.0F, p_179232_5_.func_177230_c().field_149762_H.func_150494_d() * 0.8F);
      if(p_179232_3_.field_72995_K) {
         return true;
      } else {
         p_179232_3_.func_175656_a(p_179232_4_, p_179232_5_);
         p_179232_1_.func_77972_a(1, p_179232_2_);
         return true;
      }
   }

   public boolean func_77662_d() {
      return true;
   }

   public String func_77842_f() {
      return this.field_77843_a.toString();
   }
}

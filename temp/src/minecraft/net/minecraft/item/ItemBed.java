package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemBed extends Item {
   public ItemBed() {
      this.func_77637_a(CreativeTabs.field_78031_c);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if(p_180614_3_.field_72995_K) {
         return true;
      } else if(p_180614_5_ != EnumFacing.UP) {
         return false;
      } else {
         IBlockState iblockstate = p_180614_3_.func_180495_p(p_180614_4_);
         Block block = iblockstate.func_177230_c();
         boolean flag = block.func_176200_f(p_180614_3_, p_180614_4_);
         if(!flag) {
            p_180614_4_ = p_180614_4_.func_177984_a();
         }

         int i = MathHelper.func_76128_c((double)(p_180614_2_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3;
         EnumFacing enumfacing = EnumFacing.func_176731_b(i);
         BlockPos blockpos = p_180614_4_.func_177972_a(enumfacing);
         if(p_180614_2_.func_175151_a(p_180614_4_, p_180614_5_, p_180614_1_) && p_180614_2_.func_175151_a(blockpos, p_180614_5_, p_180614_1_)) {
            boolean flag1 = p_180614_3_.func_180495_p(blockpos).func_177230_c().func_176200_f(p_180614_3_, blockpos);
            boolean flag2 = flag || p_180614_3_.func_175623_d(p_180614_4_);
            boolean flag3 = flag1 || p_180614_3_.func_175623_d(blockpos);
            if(flag2 && flag3 && World.func_175683_a(p_180614_3_, p_180614_4_.func_177977_b()) && World.func_175683_a(p_180614_3_, blockpos.func_177977_b())) {
               IBlockState iblockstate1 = Blocks.field_150324_C.func_176223_P().func_177226_a(BlockBed.field_176471_b, Boolean.valueOf(false)).func_177226_a(BlockBed.field_176387_N, enumfacing).func_177226_a(BlockBed.field_176472_a, BlockBed.EnumPartType.FOOT);
               if(p_180614_3_.func_180501_a(p_180614_4_, iblockstate1, 3)) {
                  IBlockState iblockstate2 = iblockstate1.func_177226_a(BlockBed.field_176472_a, BlockBed.EnumPartType.HEAD);
                  p_180614_3_.func_180501_a(blockpos, iblockstate2, 3);
               }

               --p_180614_1_.field_77994_a;
               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }
}

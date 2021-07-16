package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemDoor extends Item {
   private Block field_179236_a;

   public ItemDoor(Block p_i45788_1_) {
      this.field_179236_a = p_i45788_1_;
      this.func_77637_a(CreativeTabs.field_78028_d);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if(p_180614_5_ != EnumFacing.UP) {
         return false;
      } else {
         IBlockState iblockstate = p_180614_3_.func_180495_p(p_180614_4_);
         Block block = iblockstate.func_177230_c();
         if(!block.func_176200_f(p_180614_3_, p_180614_4_)) {
            p_180614_4_ = p_180614_4_.func_177972_a(p_180614_5_);
         }

         if(!p_180614_2_.func_175151_a(p_180614_4_, p_180614_5_, p_180614_1_)) {
            return false;
         } else if(!this.field_179236_a.func_176196_c(p_180614_3_, p_180614_4_)) {
            return false;
         } else {
            func_179235_a(p_180614_3_, p_180614_4_, EnumFacing.func_176733_a((double)p_180614_2_.field_70177_z), this.field_179236_a);
            --p_180614_1_.field_77994_a;
            return true;
         }
      }
   }

   public static void func_179235_a(World p_179235_0_, BlockPos p_179235_1_, EnumFacing p_179235_2_, Block p_179235_3_) {
      BlockPos blockpos = p_179235_1_.func_177972_a(p_179235_2_.func_176746_e());
      BlockPos blockpos1 = p_179235_1_.func_177972_a(p_179235_2_.func_176735_f());
      int i = (p_179235_0_.func_180495_p(blockpos1).func_177230_c().func_149721_r()?1:0) + (p_179235_0_.func_180495_p(blockpos1.func_177984_a()).func_177230_c().func_149721_r()?1:0);
      int j = (p_179235_0_.func_180495_p(blockpos).func_177230_c().func_149721_r()?1:0) + (p_179235_0_.func_180495_p(blockpos.func_177984_a()).func_177230_c().func_149721_r()?1:0);
      boolean flag = p_179235_0_.func_180495_p(blockpos1).func_177230_c() == p_179235_3_ || p_179235_0_.func_180495_p(blockpos1.func_177984_a()).func_177230_c() == p_179235_3_;
      boolean flag1 = p_179235_0_.func_180495_p(blockpos).func_177230_c() == p_179235_3_ || p_179235_0_.func_180495_p(blockpos.func_177984_a()).func_177230_c() == p_179235_3_;
      boolean flag2 = false;
      if(flag && !flag1 || j > i) {
         flag2 = true;
      }

      BlockPos blockpos2 = p_179235_1_.func_177984_a();
      IBlockState iblockstate = p_179235_3_.func_176223_P().func_177226_a(BlockDoor.field_176520_a, p_179235_2_).func_177226_a(BlockDoor.field_176521_M, flag2?BlockDoor.EnumHingePosition.RIGHT:BlockDoor.EnumHingePosition.LEFT);
      p_179235_0_.func_180501_a(p_179235_1_, iblockstate.func_177226_a(BlockDoor.field_176523_O, BlockDoor.EnumDoorHalf.LOWER), 2);
      p_179235_0_.func_180501_a(blockpos2, iblockstate.func_177226_a(BlockDoor.field_176523_O, BlockDoor.EnumDoorHalf.UPPER), 2);
      p_179235_0_.func_175685_c(p_179235_1_, p_179235_3_);
      p_179235_0_.func_175685_c(blockpos2, p_179235_3_);
   }
}

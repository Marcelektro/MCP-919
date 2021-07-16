package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemReed extends Item {
   private Block field_150935_a;

   public ItemReed(Block p_i45329_1_) {
      this.field_150935_a = p_i45329_1_;
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      IBlockState iblockstate = p_180614_3_.func_180495_p(p_180614_4_);
      Block block = iblockstate.func_177230_c();
      if(block == Blocks.field_150431_aC && ((Integer)iblockstate.func_177229_b(BlockSnow.field_176315_a)).intValue() < 1) {
         p_180614_5_ = EnumFacing.UP;
      } else if(!block.func_176200_f(p_180614_3_, p_180614_4_)) {
         p_180614_4_ = p_180614_4_.func_177972_a(p_180614_5_);
      }

      if(!p_180614_2_.func_175151_a(p_180614_4_, p_180614_5_, p_180614_1_)) {
         return false;
      } else if(p_180614_1_.field_77994_a == 0) {
         return false;
      } else {
         if(p_180614_3_.func_175716_a(this.field_150935_a, p_180614_4_, false, p_180614_5_, (Entity)null, p_180614_1_)) {
            IBlockState iblockstate1 = this.field_150935_a.func_180642_a(p_180614_3_, p_180614_4_, p_180614_5_, p_180614_6_, p_180614_7_, p_180614_8_, 0, p_180614_2_);
            if(p_180614_3_.func_180501_a(p_180614_4_, iblockstate1, 3)) {
               iblockstate1 = p_180614_3_.func_180495_p(p_180614_4_);
               if(iblockstate1.func_177230_c() == this.field_150935_a) {
                  ItemBlock.func_179224_a(p_180614_3_, p_180614_2_, p_180614_4_, p_180614_1_);
                  iblockstate1.func_177230_c().func_180633_a(p_180614_3_, p_180614_4_, iblockstate1, p_180614_2_, p_180614_1_);
               }

               p_180614_3_.func_72908_a((double)((float)p_180614_4_.func_177958_n() + 0.5F), (double)((float)p_180614_4_.func_177956_o() + 0.5F), (double)((float)p_180614_4_.func_177952_p() + 0.5F), this.field_150935_a.field_149762_H.func_150496_b(), (this.field_150935_a.field_149762_H.func_150497_c() + 1.0F) / 2.0F, this.field_150935_a.field_149762_H.func_150494_d() * 0.8F);
               --p_180614_1_.field_77994_a;
               return true;
            }
         }

         return false;
      }
   }
}

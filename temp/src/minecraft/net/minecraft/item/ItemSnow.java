package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemSnow extends ItemBlock {
   public ItemSnow(Block p_i45781_1_) {
      super(p_i45781_1_);
      this.func_77656_e(0);
      this.func_77627_a(true);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if(p_180614_1_.field_77994_a == 0) {
         return false;
      } else if(!p_180614_2_.func_175151_a(p_180614_4_, p_180614_5_, p_180614_1_)) {
         return false;
      } else {
         IBlockState iblockstate = p_180614_3_.func_180495_p(p_180614_4_);
         Block block = iblockstate.func_177230_c();
         BlockPos blockpos = p_180614_4_;
         if((p_180614_5_ != EnumFacing.UP || block != this.field_150939_a) && !block.func_176200_f(p_180614_3_, p_180614_4_)) {
            blockpos = p_180614_4_.func_177972_a(p_180614_5_);
            iblockstate = p_180614_3_.func_180495_p(blockpos);
            block = iblockstate.func_177230_c();
         }

         if(block == this.field_150939_a) {
            int i = ((Integer)iblockstate.func_177229_b(BlockSnow.field_176315_a)).intValue();
            if(i <= 7) {
               IBlockState iblockstate1 = iblockstate.func_177226_a(BlockSnow.field_176315_a, Integer.valueOf(i + 1));
               AxisAlignedBB axisalignedbb = this.field_150939_a.func_180640_a(p_180614_3_, blockpos, iblockstate1);
               if(axisalignedbb != null && p_180614_3_.func_72855_b(axisalignedbb) && p_180614_3_.func_180501_a(blockpos, iblockstate1, 2)) {
                  p_180614_3_.func_72908_a((double)((float)blockpos.func_177958_n() + 0.5F), (double)((float)blockpos.func_177956_o() + 0.5F), (double)((float)blockpos.func_177952_p() + 0.5F), this.field_150939_a.field_149762_H.func_150496_b(), (this.field_150939_a.field_149762_H.func_150497_c() + 1.0F) / 2.0F, this.field_150939_a.field_149762_H.func_150494_d() * 0.8F);
                  --p_180614_1_.field_77994_a;
                  return true;
               }
            }
         }

         return super.func_180614_a(p_180614_1_, p_180614_2_, p_180614_3_, blockpos, p_180614_5_, p_180614_6_, p_180614_7_, p_180614_8_);
      }
   }

   public int func_77647_b(int p_77647_1_) {
      return p_77647_1_;
   }
}

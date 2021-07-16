package net.minecraft.item;

import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemEnderEye extends Item {
   public ItemEnderEye() {
      this.func_77637_a(CreativeTabs.field_78026_f);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      IBlockState iblockstate = p_180614_3_.func_180495_p(p_180614_4_);
      if(p_180614_2_.func_175151_a(p_180614_4_.func_177972_a(p_180614_5_), p_180614_5_, p_180614_1_) && iblockstate.func_177230_c() == Blocks.field_150378_br && !((Boolean)iblockstate.func_177229_b(BlockEndPortalFrame.field_176507_b)).booleanValue()) {
         if(p_180614_3_.field_72995_K) {
            return true;
         } else {
            p_180614_3_.func_180501_a(p_180614_4_, iblockstate.func_177226_a(BlockEndPortalFrame.field_176507_b, Boolean.valueOf(true)), 2);
            p_180614_3_.func_175666_e(p_180614_4_, Blocks.field_150378_br);
            --p_180614_1_.field_77994_a;

            for(int i = 0; i < 16; ++i) {
               double d0 = (double)((float)p_180614_4_.func_177958_n() + (5.0F + field_77697_d.nextFloat() * 6.0F) / 16.0F);
               double d1 = (double)((float)p_180614_4_.func_177956_o() + 0.8125F);
               double d2 = (double)((float)p_180614_4_.func_177952_p() + (5.0F + field_77697_d.nextFloat() * 6.0F) / 16.0F);
               double d3 = 0.0D;
               double d4 = 0.0D;
               double d5 = 0.0D;
               p_180614_3_.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5, new int[0]);
            }

            EnumFacing enumfacing = (EnumFacing)iblockstate.func_177229_b(BlockEndPortalFrame.field_176508_a);
            int l = 0;
            int j = 0;
            boolean flag1 = false;
            boolean flag = true;
            EnumFacing enumfacing1 = enumfacing.func_176746_e();

            for(int k = -2; k <= 2; ++k) {
               BlockPos blockpos1 = p_180614_4_.func_177967_a(enumfacing1, k);
               IBlockState iblockstate1 = p_180614_3_.func_180495_p(blockpos1);
               if(iblockstate1.func_177230_c() == Blocks.field_150378_br) {
                  if(!((Boolean)iblockstate1.func_177229_b(BlockEndPortalFrame.field_176507_b)).booleanValue()) {
                     flag = false;
                     break;
                  }

                  j = k;
                  if(!flag1) {
                     l = k;
                     flag1 = true;
                  }
               }
            }

            if(flag && j == l + 2) {
               BlockPos blockpos = p_180614_4_.func_177967_a(enumfacing, 4);

               for(int i1 = l; i1 <= j; ++i1) {
                  BlockPos blockpos2 = blockpos.func_177967_a(enumfacing1, i1);
                  IBlockState iblockstate3 = p_180614_3_.func_180495_p(blockpos2);
                  if(iblockstate3.func_177230_c() != Blocks.field_150378_br || !((Boolean)iblockstate3.func_177229_b(BlockEndPortalFrame.field_176507_b)).booleanValue()) {
                     flag = false;
                     break;
                  }
               }

               for(int j1 = l - 1; j1 <= j + 1; j1 += 4) {
                  blockpos = p_180614_4_.func_177967_a(enumfacing1, j1);

                  for(int l1 = 1; l1 <= 3; ++l1) {
                     BlockPos blockpos3 = blockpos.func_177967_a(enumfacing, l1);
                     IBlockState iblockstate2 = p_180614_3_.func_180495_p(blockpos3);
                     if(iblockstate2.func_177230_c() != Blocks.field_150378_br || !((Boolean)iblockstate2.func_177229_b(BlockEndPortalFrame.field_176507_b)).booleanValue()) {
                        flag = false;
                        break;
                     }
                  }
               }

               if(flag) {
                  for(int k1 = l; k1 <= j; ++k1) {
                     blockpos = p_180614_4_.func_177967_a(enumfacing1, k1);

                     for(int i2 = 1; i2 <= 3; ++i2) {
                        BlockPos blockpos4 = blockpos.func_177967_a(enumfacing, i2);
                        p_180614_3_.func_180501_a(blockpos4, Blocks.field_150384_bq.func_176223_P(), 2);
                     }
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      MovingObjectPosition movingobjectposition = this.func_77621_a(p_77659_2_, p_77659_3_, false);
      if(movingobjectposition != null && movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && p_77659_2_.func_180495_p(movingobjectposition.func_178782_a()).func_177230_c() == Blocks.field_150378_br) {
         return p_77659_1_;
      } else {
         if(!p_77659_2_.field_72995_K) {
            BlockPos blockpos = p_77659_2_.func_180499_a("Stronghold", new BlockPos(p_77659_3_));
            if(blockpos != null) {
               EntityEnderEye entityendereye = new EntityEnderEye(p_77659_2_, p_77659_3_.field_70165_t, p_77659_3_.field_70163_u, p_77659_3_.field_70161_v);
               entityendereye.func_180465_a(blockpos);
               p_77659_2_.func_72838_d(entityendereye);
               p_77659_2_.func_72956_a(p_77659_3_, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
               p_77659_2_.func_180498_a((EntityPlayer)null, 1002, new BlockPos(p_77659_3_), 0);
               if(!p_77659_3_.field_71075_bZ.field_75098_d) {
                  --p_77659_1_.field_77994_a;
               }

               p_77659_3_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this)]);
            }
         }

         return p_77659_1_;
      }
   }
}

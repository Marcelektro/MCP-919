package net.minecraft.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ItemDye extends Item {
   public static final int[] field_150922_c = new int[]{1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320};

   public ItemDye() {
      this.func_77627_a(true);
      this.func_77656_e(0);
      this.func_77637_a(CreativeTabs.field_78035_l);
   }

   public String func_77667_c(ItemStack p_77667_1_) {
      int i = p_77667_1_.func_77960_j();
      return super.func_77658_a() + "." + EnumDyeColor.func_176766_a(i).func_176762_d();
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if(!p_180614_2_.func_175151_a(p_180614_4_.func_177972_a(p_180614_5_), p_180614_5_, p_180614_1_)) {
         return false;
      } else {
         EnumDyeColor enumdyecolor = EnumDyeColor.func_176766_a(p_180614_1_.func_77960_j());
         if(enumdyecolor == EnumDyeColor.WHITE) {
            if(func_179234_a(p_180614_1_, p_180614_3_, p_180614_4_)) {
               if(!p_180614_3_.field_72995_K) {
                  p_180614_3_.func_175718_b(2005, p_180614_4_, 0);
               }

               return true;
            }
         } else if(enumdyecolor == EnumDyeColor.BROWN) {
            IBlockState iblockstate = p_180614_3_.func_180495_p(p_180614_4_);
            Block block = iblockstate.func_177230_c();
            if(block == Blocks.field_150364_r && iblockstate.func_177229_b(BlockPlanks.field_176383_a) == BlockPlanks.EnumType.JUNGLE) {
               if(p_180614_5_ == EnumFacing.DOWN) {
                  return false;
               }

               if(p_180614_5_ == EnumFacing.UP) {
                  return false;
               }

               p_180614_4_ = p_180614_4_.func_177972_a(p_180614_5_);
               if(p_180614_3_.func_175623_d(p_180614_4_)) {
                  IBlockState iblockstate1 = Blocks.field_150375_by.func_180642_a(p_180614_3_, p_180614_4_, p_180614_5_, p_180614_6_, p_180614_7_, p_180614_8_, 0, p_180614_2_);
                  p_180614_3_.func_180501_a(p_180614_4_, iblockstate1, 2);
                  if(!p_180614_2_.field_71075_bZ.field_75098_d) {
                     --p_180614_1_.field_77994_a;
                  }
               }

               return true;
            }
         }

         return false;
      }
   }

   public static boolean func_179234_a(ItemStack p_179234_0_, World p_179234_1_, BlockPos p_179234_2_) {
      IBlockState iblockstate = p_179234_1_.func_180495_p(p_179234_2_);
      if(iblockstate.func_177230_c() instanceof IGrowable) {
         IGrowable igrowable = (IGrowable)iblockstate.func_177230_c();
         if(igrowable.func_176473_a(p_179234_1_, p_179234_2_, iblockstate, p_179234_1_.field_72995_K)) {
            if(!p_179234_1_.field_72995_K) {
               if(igrowable.func_180670_a(p_179234_1_, p_179234_1_.field_73012_v, p_179234_2_, iblockstate)) {
                  igrowable.func_176474_b(p_179234_1_, p_179234_1_.field_73012_v, p_179234_2_, iblockstate);
               }

               --p_179234_0_.field_77994_a;
            }

            return true;
         }
      }

      return false;
   }

   public static void func_180617_a(World p_180617_0_, BlockPos p_180617_1_, int p_180617_2_) {
      if(p_180617_2_ == 0) {
         p_180617_2_ = 15;
      }

      Block block = p_180617_0_.func_180495_p(p_180617_1_).func_177230_c();
      if(block.func_149688_o() != Material.field_151579_a) {
         block.func_180654_a(p_180617_0_, p_180617_1_);

         for(int i = 0; i < p_180617_2_; ++i) {
            double d0 = field_77697_d.nextGaussian() * 0.02D;
            double d1 = field_77697_d.nextGaussian() * 0.02D;
            double d2 = field_77697_d.nextGaussian() * 0.02D;
            p_180617_0_.func_175688_a(EnumParticleTypes.VILLAGER_HAPPY, (double)((float)p_180617_1_.func_177958_n() + field_77697_d.nextFloat()), (double)p_180617_1_.func_177956_o() + (double)field_77697_d.nextFloat() * block.func_149669_A(), (double)((float)p_180617_1_.func_177952_p() + field_77697_d.nextFloat()), d0, d1, d2, new int[0]);
         }

      }
   }

   public boolean func_111207_a(ItemStack p_111207_1_, EntityPlayer p_111207_2_, EntityLivingBase p_111207_3_) {
      if(p_111207_3_ instanceof EntitySheep) {
         EntitySheep entitysheep = (EntitySheep)p_111207_3_;
         EnumDyeColor enumdyecolor = EnumDyeColor.func_176766_a(p_111207_1_.func_77960_j());
         if(!entitysheep.func_70892_o() && entitysheep.func_175509_cj() != enumdyecolor) {
            entitysheep.func_175512_b(enumdyecolor);
            --p_111207_1_.field_77994_a;
         }

         return true;
      } else {
         return false;
      }
   }

   public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> p_150895_3_) {
      for(int i = 0; i < 16; ++i) {
         p_150895_3_.add(new ItemStack(p_150895_1_, 1, i));
      }

   }
}

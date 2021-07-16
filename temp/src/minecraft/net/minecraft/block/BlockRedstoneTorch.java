package net.minecraft.block;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneTorch extends BlockTorch {
   private static Map<World, List<BlockRedstoneTorch.Toggle>> field_150112_b = Maps.<World, List<BlockRedstoneTorch.Toggle>>newHashMap();
   private final boolean field_150113_a;

   private boolean func_176598_a(World p_176598_1_, BlockPos p_176598_2_, boolean p_176598_3_) {
      if(!field_150112_b.containsKey(p_176598_1_)) {
         field_150112_b.put(p_176598_1_, Lists.<BlockRedstoneTorch.Toggle>newArrayList());
      }

      List<BlockRedstoneTorch.Toggle> list = (List)field_150112_b.get(p_176598_1_);
      if(p_176598_3_) {
         list.add(new BlockRedstoneTorch.Toggle(p_176598_2_, p_176598_1_.func_82737_E()));
      }

      int i = 0;

      for(int j = 0; j < list.size(); ++j) {
         BlockRedstoneTorch.Toggle blockredstonetorch$toggle = (BlockRedstoneTorch.Toggle)list.get(j);
         if(blockredstonetorch$toggle.field_180111_a.equals(p_176598_2_)) {
            ++i;
            if(i >= 8) {
               return true;
            }
         }
      }

      return false;
   }

   protected BlockRedstoneTorch(boolean p_i45423_1_) {
      this.field_150113_a = p_i45423_1_;
      this.func_149675_a(true);
      this.func_149647_a((CreativeTabs)null);
   }

   public int func_149738_a(World p_149738_1_) {
      return 2;
   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      if(this.field_150113_a) {
         for(EnumFacing enumfacing : EnumFacing.values()) {
            p_176213_1_.func_175685_c(p_176213_2_.func_177972_a(enumfacing), this);
         }
      }

   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      if(this.field_150113_a) {
         for(EnumFacing enumfacing : EnumFacing.values()) {
            p_180663_1_.func_175685_c(p_180663_2_.func_177972_a(enumfacing), this);
         }
      }

   }

   public int func_180656_a(IBlockAccess p_180656_1_, BlockPos p_180656_2_, IBlockState p_180656_3_, EnumFacing p_180656_4_) {
      return this.field_150113_a && p_180656_3_.func_177229_b(field_176596_a) != p_180656_4_?15:0;
   }

   private boolean func_176597_g(World p_176597_1_, BlockPos p_176597_2_, IBlockState p_176597_3_) {
      EnumFacing enumfacing = ((EnumFacing)p_176597_3_.func_177229_b(field_176596_a)).func_176734_d();
      return p_176597_1_.func_175709_b(p_176597_2_.func_177972_a(enumfacing), enumfacing);
   }

   public void func_180645_a(World p_180645_1_, BlockPos p_180645_2_, IBlockState p_180645_3_, Random p_180645_4_) {
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      boolean flag = this.func_176597_g(p_180650_1_, p_180650_2_, p_180650_3_);
      List<BlockRedstoneTorch.Toggle> list = (List)field_150112_b.get(p_180650_1_);

      while(list != null && !list.isEmpty() && p_180650_1_.func_82737_E() - ((BlockRedstoneTorch.Toggle)list.get(0)).field_150844_d > 60L) {
         list.remove(0);
      }

      if(this.field_150113_a) {
         if(flag) {
            p_180650_1_.func_180501_a(p_180650_2_, Blocks.field_150437_az.func_176223_P().func_177226_a(field_176596_a, p_180650_3_.func_177229_b(field_176596_a)), 3);
            if(this.func_176598_a(p_180650_1_, p_180650_2_, true)) {
               p_180650_1_.func_72908_a((double)((float)p_180650_2_.func_177958_n() + 0.5F), (double)((float)p_180650_2_.func_177956_o() + 0.5F), (double)((float)p_180650_2_.func_177952_p() + 0.5F), "random.fizz", 0.5F, 2.6F + (p_180650_1_.field_73012_v.nextFloat() - p_180650_1_.field_73012_v.nextFloat()) * 0.8F);

               for(int i = 0; i < 5; ++i) {
                  double d0 = (double)p_180650_2_.func_177958_n() + p_180650_4_.nextDouble() * 0.6D + 0.2D;
                  double d1 = (double)p_180650_2_.func_177956_o() + p_180650_4_.nextDouble() * 0.6D + 0.2D;
                  double d2 = (double)p_180650_2_.func_177952_p() + p_180650_4_.nextDouble() * 0.6D + 0.2D;
                  p_180650_1_.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
               }

               p_180650_1_.func_175684_a(p_180650_2_, p_180650_1_.func_180495_p(p_180650_2_).func_177230_c(), 160);
            }
         }
      } else if(!flag && !this.func_176598_a(p_180650_1_, p_180650_2_, false)) {
         p_180650_1_.func_180501_a(p_180650_2_, Blocks.field_150429_aA.func_176223_P().func_177226_a(field_176596_a, p_180650_3_.func_177229_b(field_176596_a)), 3);
      }

   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if(!this.func_176592_e(p_176204_1_, p_176204_2_, p_176204_3_)) {
         if(this.field_150113_a == this.func_176597_g(p_176204_1_, p_176204_2_, p_176204_3_)) {
            p_176204_1_.func_175684_a(p_176204_2_, this, this.func_149738_a(p_176204_1_));
         }

      }
   }

   public int func_176211_b(IBlockAccess p_176211_1_, BlockPos p_176211_2_, IBlockState p_176211_3_, EnumFacing p_176211_4_) {
      return p_176211_4_ == EnumFacing.DOWN?this.func_180656_a(p_176211_1_, p_176211_2_, p_176211_3_, p_176211_4_):0;
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Item.func_150898_a(Blocks.field_150429_aA);
   }

   public boolean func_149744_f() {
      return true;
   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      if(this.field_150113_a) {
         double d0 = (double)p_180655_2_.func_177958_n() + 0.5D + (p_180655_4_.nextDouble() - 0.5D) * 0.2D;
         double d1 = (double)p_180655_2_.func_177956_o() + 0.7D + (p_180655_4_.nextDouble() - 0.5D) * 0.2D;
         double d2 = (double)p_180655_2_.func_177952_p() + 0.5D + (p_180655_4_.nextDouble() - 0.5D) * 0.2D;
         EnumFacing enumfacing = (EnumFacing)p_180655_3_.func_177229_b(field_176596_a);
         if(enumfacing.func_176740_k().func_176722_c()) {
            EnumFacing enumfacing1 = enumfacing.func_176734_d();
            double d3 = 0.27D;
            d0 += 0.27D * (double)enumfacing1.func_82601_c();
            d1 += 0.22D;
            d2 += 0.27D * (double)enumfacing1.func_82599_e();
         }

         p_180655_1_.func_175688_a(EnumParticleTypes.REDSTONE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
      }
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return Item.func_150898_a(Blocks.field_150429_aA);
   }

   public boolean func_149667_c(Block p_149667_1_) {
      return p_149667_1_ == Blocks.field_150437_az || p_149667_1_ == Blocks.field_150429_aA;
   }

   static class Toggle {
      BlockPos field_180111_a;
      long field_150844_d;

      public Toggle(BlockPos p_i45688_1_, long p_i45688_2_) {
         this.field_180111_a = p_i45688_1_;
         this.field_150844_d = p_i45688_2_;
      }
   }
}

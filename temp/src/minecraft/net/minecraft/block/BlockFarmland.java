package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockStem;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFarmland extends Block {
   public static final PropertyInteger field_176531_a = PropertyInteger.func_177719_a("moisture", 0, 7);

   protected BlockFarmland() {
      super(Material.field_151578_c);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176531_a, Integer.valueOf(0)));
      this.func_149675_a(true);
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
      this.func_149713_g(255);
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      return new AxisAlignedBB((double)p_180640_2_.func_177958_n(), (double)p_180640_2_.func_177956_o(), (double)p_180640_2_.func_177952_p(), (double)(p_180640_2_.func_177958_n() + 1), (double)(p_180640_2_.func_177956_o() + 1), (double)(p_180640_2_.func_177952_p() + 1));
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      int i = ((Integer)p_180650_3_.func_177229_b(field_176531_a)).intValue();
      if(!this.func_176530_e(p_180650_1_, p_180650_2_) && !p_180650_1_.func_175727_C(p_180650_2_.func_177984_a())) {
         if(i > 0) {
            p_180650_1_.func_180501_a(p_180650_2_, p_180650_3_.func_177226_a(field_176531_a, Integer.valueOf(i - 1)), 2);
         } else if(!this.func_176529_d(p_180650_1_, p_180650_2_)) {
            p_180650_1_.func_175656_a(p_180650_2_, Blocks.field_150346_d.func_176223_P());
         }
      } else if(i < 7) {
         p_180650_1_.func_180501_a(p_180650_2_, p_180650_3_.func_177226_a(field_176531_a, Integer.valueOf(7)), 2);
      }

   }

   public void func_180658_a(World p_180658_1_, BlockPos p_180658_2_, Entity p_180658_3_, float p_180658_4_) {
      if(p_180658_3_ instanceof EntityLivingBase) {
         if(!p_180658_1_.field_72995_K && p_180658_1_.field_73012_v.nextFloat() < p_180658_4_ - 0.5F) {
            if(!(p_180658_3_ instanceof EntityPlayer) && !p_180658_1_.func_82736_K().func_82766_b("mobGriefing")) {
               return;
            }

            p_180658_1_.func_175656_a(p_180658_2_, Blocks.field_150346_d.func_176223_P());
         }

         super.func_180658_a(p_180658_1_, p_180658_2_, p_180658_3_, p_180658_4_);
      }
   }

   private boolean func_176529_d(World p_176529_1_, BlockPos p_176529_2_) {
      Block block = p_176529_1_.func_180495_p(p_176529_2_.func_177984_a()).func_177230_c();
      return block instanceof BlockCrops || block instanceof BlockStem;
   }

   private boolean func_176530_e(World p_176530_1_, BlockPos p_176530_2_) {
      for(BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.func_177975_b(p_176530_2_.func_177982_a(-4, 0, -4), p_176530_2_.func_177982_a(4, 1, 4))) {
         if(p_176530_1_.func_180495_p(blockpos$mutableblockpos).func_177230_c().func_149688_o() == Material.field_151586_h) {
            return true;
         }
      }

      return false;
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      super.func_176204_a(p_176204_1_, p_176204_2_, p_176204_3_, p_176204_4_);
      if(p_176204_1_.func_180495_p(p_176204_2_.func_177984_a()).func_177230_c().func_149688_o().func_76220_a()) {
         p_176204_1_.func_175656_a(p_176204_2_, Blocks.field_150346_d.func_176223_P());
      }

   }

   public boolean func_176225_a(IBlockAccess p_176225_1_, BlockPos p_176225_2_, EnumFacing p_176225_3_) {
      switch(p_176225_3_) {
      case UP:
         return true;
      case NORTH:
      case SOUTH:
      case WEST:
      case EAST:
         Block block = p_176225_1_.func_180495_p(p_176225_2_).func_177230_c();
         return !block.func_149662_c() && block != Blocks.field_150458_ak;
      default:
         return super.func_176225_a(p_176225_1_, p_176225_2_, p_176225_3_);
      }
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Blocks.field_150346_d.func_180660_a(Blocks.field_150346_d.func_176223_P().func_177226_a(BlockDirt.field_176386_a, BlockDirt.DirtType.DIRT), p_180660_2_, p_180660_3_);
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return Item.func_150898_a(Blocks.field_150346_d);
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176531_a, Integer.valueOf(p_176203_1_ & 7));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((Integer)p_176201_1_.func_177229_b(field_176531_a)).intValue();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176531_a});
   }
}

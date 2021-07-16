package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneRepeater extends BlockRedstoneDiode {
   public static final PropertyBool field_176411_a = PropertyBool.func_177716_a("locked");
   public static final PropertyInteger field_176410_b = PropertyInteger.func_177719_a("delay", 1, 4);

   protected BlockRedstoneRepeater(boolean p_i45424_1_) {
      super(p_i45424_1_);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176387_N, EnumFacing.NORTH).func_177226_a(field_176410_b, Integer.valueOf(1)).func_177226_a(field_176411_a, Boolean.valueOf(false)));
   }

   public String func_149732_F() {
      return StatCollector.func_74838_a("item.diode.name");
   }

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      return p_176221_1_.func_177226_a(field_176411_a, Boolean.valueOf(this.func_176405_b(p_176221_2_, p_176221_3_, p_176221_1_)));
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if(!p_180639_4_.field_71075_bZ.field_75099_e) {
         return false;
      } else {
         p_180639_1_.func_180501_a(p_180639_2_, p_180639_3_.func_177231_a(field_176410_b), 3);
         return true;
      }
   }

   protected int func_176403_d(IBlockState p_176403_1_) {
      return ((Integer)p_176403_1_.func_177229_b(field_176410_b)).intValue() * 2;
   }

   protected IBlockState func_180674_e(IBlockState p_180674_1_) {
      Integer integer = (Integer)p_180674_1_.func_177229_b(field_176410_b);
      Boolean obool = (Boolean)p_180674_1_.func_177229_b(field_176411_a);
      EnumFacing enumfacing = (EnumFacing)p_180674_1_.func_177229_b(field_176387_N);
      return Blocks.field_150416_aS.func_176223_P().func_177226_a(field_176387_N, enumfacing).func_177226_a(field_176410_b, integer).func_177226_a(field_176411_a, obool);
   }

   protected IBlockState func_180675_k(IBlockState p_180675_1_) {
      Integer integer = (Integer)p_180675_1_.func_177229_b(field_176410_b);
      Boolean obool = (Boolean)p_180675_1_.func_177229_b(field_176411_a);
      EnumFacing enumfacing = (EnumFacing)p_180675_1_.func_177229_b(field_176387_N);
      return Blocks.field_150413_aR.func_176223_P().func_177226_a(field_176387_N, enumfacing).func_177226_a(field_176410_b, integer).func_177226_a(field_176411_a, obool);
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Items.field_151107_aW;
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return Items.field_151107_aW;
   }

   public boolean func_176405_b(IBlockAccess p_176405_1_, BlockPos p_176405_2_, IBlockState p_176405_3_) {
      return this.func_176407_c(p_176405_1_, p_176405_2_, p_176405_3_) > 0;
   }

   protected boolean func_149908_a(Block p_149908_1_) {
      return func_149909_d(p_149908_1_);
   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      if(this.field_149914_a) {
         EnumFacing enumfacing = (EnumFacing)p_180655_3_.func_177229_b(field_176387_N);
         double d0 = (double)((float)p_180655_2_.func_177958_n() + 0.5F) + (double)(p_180655_4_.nextFloat() - 0.5F) * 0.2D;
         double d1 = (double)((float)p_180655_2_.func_177956_o() + 0.4F) + (double)(p_180655_4_.nextFloat() - 0.5F) * 0.2D;
         double d2 = (double)((float)p_180655_2_.func_177952_p() + 0.5F) + (double)(p_180655_4_.nextFloat() - 0.5F) * 0.2D;
         float f = -5.0F;
         if(p_180655_4_.nextBoolean()) {
            f = (float)(((Integer)p_180655_3_.func_177229_b(field_176410_b)).intValue() * 2 - 1);
         }

         f = f / 16.0F;
         double d3 = (double)(f * (float)enumfacing.func_82601_c());
         double d4 = (double)(f * (float)enumfacing.func_82599_e());
         p_180655_1_.func_175688_a(EnumParticleTypes.REDSTONE, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
      }
   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
      this.func_176400_h(p_180663_1_, p_180663_2_, p_180663_3_);
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176387_N, EnumFacing.func_176731_b(p_176203_1_)).func_177226_a(field_176411_a, Boolean.valueOf(false)).func_177226_a(field_176410_b, Integer.valueOf(1 + (p_176203_1_ >> 2)));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int i = 0;
      i = i | ((EnumFacing)p_176201_1_.func_177229_b(field_176387_N)).func_176736_b();
      i = i | ((Integer)p_176201_1_.func_177229_b(field_176410_b)).intValue() - 1 << 2;
      return i;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176387_N, field_176410_b, field_176411_a});
   }
}

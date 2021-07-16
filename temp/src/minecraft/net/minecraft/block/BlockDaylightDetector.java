package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDaylightDetector;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDaylightDetector extends BlockContainer {
   public static final PropertyInteger field_176436_a = PropertyInteger.func_177719_a("power", 0, 15);
   private final boolean field_176435_b;

   public BlockDaylightDetector(boolean p_i45729_1_) {
      super(Material.field_151575_d);
      this.field_176435_b = p_i45729_1_;
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176436_a, Integer.valueOf(0)));
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
      this.func_149647_a(CreativeTabs.field_78028_d);
      this.func_149711_c(0.2F);
      this.func_149672_a(field_149766_f);
      this.func_149663_c("daylightDetector");
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
   }

   public int func_180656_a(IBlockAccess p_180656_1_, BlockPos p_180656_2_, IBlockState p_180656_3_, EnumFacing p_180656_4_) {
      return ((Integer)p_180656_3_.func_177229_b(field_176436_a)).intValue();
   }

   public void func_180677_d(World p_180677_1_, BlockPos p_180677_2_) {
      if(!p_180677_1_.field_73011_w.func_177495_o()) {
         IBlockState iblockstate = p_180677_1_.func_180495_p(p_180677_2_);
         int i = p_180677_1_.func_175642_b(EnumSkyBlock.SKY, p_180677_2_) - p_180677_1_.func_175657_ab();
         float f = p_180677_1_.func_72929_e(1.0F);
         float f1 = f < 3.1415927F?0.0F:6.2831855F;
         f = f + (f1 - f) * 0.2F;
         i = Math.round((float)i * MathHelper.func_76134_b(f));
         i = MathHelper.func_76125_a(i, 0, 15);
         if(this.field_176435_b) {
            i = 15 - i;
         }

         if(((Integer)iblockstate.func_177229_b(field_176436_a)).intValue() != i) {
            p_180677_1_.func_180501_a(p_180677_2_, iblockstate.func_177226_a(field_176436_a, Integer.valueOf(i)), 3);
         }

      }
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if(p_180639_4_.func_175142_cm()) {
         if(p_180639_1_.field_72995_K) {
            return true;
         } else {
            if(this.field_176435_b) {
               p_180639_1_.func_180501_a(p_180639_2_, Blocks.field_150453_bW.func_176223_P().func_177226_a(field_176436_a, p_180639_3_.func_177229_b(field_176436_a)), 4);
               Blocks.field_150453_bW.func_180677_d(p_180639_1_, p_180639_2_);
            } else {
               p_180639_1_.func_180501_a(p_180639_2_, Blocks.field_180402_cm.func_176223_P().func_177226_a(field_176436_a, p_180639_3_.func_177229_b(field_176436_a)), 4);
               Blocks.field_180402_cm.func_180677_d(p_180639_1_, p_180639_2_);
            }

            return true;
         }
      } else {
         return super.func_180639_a(p_180639_1_, p_180639_2_, p_180639_3_, p_180639_4_, p_180639_5_, p_180639_6_, p_180639_7_, p_180639_8_);
      }
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Item.func_150898_a(Blocks.field_150453_bW);
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return Item.func_150898_a(Blocks.field_150453_bW);
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_149662_c() {
      return false;
   }

   public int func_149645_b() {
      return 3;
   }

   public boolean func_149744_f() {
      return true;
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntityDaylightDetector();
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176436_a, Integer.valueOf(p_176203_1_));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((Integer)p_176201_1_.func_177229_b(field_176436_a)).intValue();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176436_a});
   }

   public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List<ItemStack> p_149666_3_) {
      if(!this.field_176435_b) {
         super.func_149666_a(p_149666_1_, p_149666_2_, p_149666_3_);
      }

   }
}
